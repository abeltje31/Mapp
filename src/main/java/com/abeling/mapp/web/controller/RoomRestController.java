package com.abeling.mapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abeling.mapp.model.Location;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.persistence.LocationRepository;
import com.abeling.mapp.persistence.RoomRepository;
import com.abeling.mapp.web.resource.RoomResource;
import com.abeling.mapp.web.error.Checks;


@RestController
public class RoomRestController {

	private final LocationRepository locationRepository; 
	private final RoomRepository roomRepository;
	
	@Autowired
	RoomRestController(LocationRepository locationRepository, RoomRepository roomRepository){
		this.locationRepository  = locationRepository;
		this.roomRepository = roomRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/rooms")
	public Resources<RoomResource> findAll(){
		final List<Room> rooms = (List<Room>) roomRepository.findAll();
		final List<RoomResource> roomResources = rooms.stream().map(RoomResource::new).collect(Collectors.toList());
		return new Resources<>(roomResources);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/locations/{locationId}/rooms")
	public Resources<RoomResource> findFromLocation(@PathVariable Long locationId){
		final Location location= Checks.checkEntityExists(locationRepository.findOne(locationId), "Location not found");
		final List<Room> rooms = (List<Room>) roomRepository.findByLocation(location);
		final List<RoomResource> roomResources = rooms.stream().map(RoomResource::new).collect(Collectors.toList());
		return new Resources<>(roomResources);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="rooms/{roomId}")
	public RoomResource getRoomById(@PathVariable Long roomId){
		final Room room = Checks.checkEntityExists(roomRepository.findOne(roomId), "Room not found");
		final RoomResource roomResource = new RoomResource(room);
		return roomResource;
	}
	
}
