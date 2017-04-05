package com.abeling.mapp.web.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.abeling.mapp.model.Room;
import com.abeling.mapp.web.controller.BoxRestController;
import com.abeling.mapp.web.controller.RoomRestController;
import com.fasterxml.jackson.annotation.JsonCreator;

public class RoomResource extends ResourceSupport {
	
	private final Room room;
	
	@JsonCreator
	public RoomResource(@NotNull final Room room) {
		this.room = room;
		this.add(linkTo(methodOn(RoomRestController.class).getRoomById(room.getId())).withSelfRel());
		this.add(linkTo(methodOn(BoxRestController.class).findFromRoom(room.getId())).withRel("boxes"));
	}

	public Room getRoom(){
		return room;
	}
	
}
