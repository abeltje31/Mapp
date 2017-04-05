package com.abeling.mapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.persistence.BoxRepository;
import com.abeling.mapp.persistence.RoomRepository;
import com.abeling.mapp.web.error.Checks;
import com.abeling.mapp.web.resource.BoxResource;


@RestController
public class BoxRestController {

	private final RoomRepository roomRepository;
	private final BoxRepository boxRepository;
	
	@Autowired
	BoxRestController(RoomRepository roomRepository, BoxRepository boxRepository){
		this.roomRepository = roomRepository;
		this.boxRepository = boxRepository;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/boxes")
	public Resources<BoxResource> findAll(){
		final List<Box> boxes = (List<Box>) boxRepository.findAll();
		final List<BoxResource> boxResources = boxes.stream().map(BoxResource::new).collect(Collectors.toList());
		return new Resources<>(boxResources);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/rooms/{roomId}/boxes")
	public Resources<BoxResource> findFromRoom(@PathVariable Long roomId){
		final Room room = Checks.checkEntityExists(roomRepository.findOne(roomId), "Room not found");
		final List<Box> boxes = (List<Box>) boxRepository.findByRoom(room);
		final List<BoxResource> boxResources = boxes.stream().map(BoxResource::new).collect(Collectors.toList());
		return new Resources<>(boxResources);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/boxes/{boxId}")
	public BoxResource getBoxById(@PathVariable Long boxId){
		final Box box = Checks.checkEntityExists(boxRepository.findOne(boxId), "Room not found");
		final BoxResource boxResource = new BoxResource(box);
		return boxResource;
	}
	
	
}
