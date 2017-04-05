package com.abeling.mapp.web.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.abeling.mapp.model.Box;
import com.abeling.mapp.web.controller.BoxRestController;
import com.abeling.mapp.web.controller.ItemRestController;
import com.abeling.mapp.web.controller.RoomRestController;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BoxResource extends ResourceSupport {

	private final Box box;
	
	@JsonCreator
	public BoxResource(@NotNull final Box box){
		this.box = box;
		this.add(linkTo(methodOn(BoxRestController.class).getBoxById(box.getId())).withSelfRel());
		this.add(linkTo(methodOn(ItemRestController.class).findFromBox(box.getId())).withRel("items"));
		this.add(linkTo(methodOn(RoomRestController.class).getRoomById(box.getRoom().getId())).withRel("room"));
	}
	
	public Box getBox(){
		return box;
	}
}
