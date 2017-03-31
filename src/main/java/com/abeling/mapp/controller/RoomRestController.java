package com.abeling.mapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.repository.RoomRepository;


@RestController
@RequestMapping("/rooms")
public class RoomRestController {

	private final RoomRepository roomRepository;
	
	@Autowired
	RoomRestController(RoomRepository roomRepository){
		this.roomRepository = roomRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	Page<Room> getRooms(Pageable pageable){
		return this.roomRepository.findAll(pageable);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{roomId}")
	ResponseEntity<?> getRoomById(@PathVariable Long roomId){
		Room room = roomRepository.findOne(roomId);
		if (room == null) {
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
}
