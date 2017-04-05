package com.abeling.mapp.web.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.abeling.mapp.web.controller.BoxRestController;
import com.abeling.mapp.web.controller.ItemRestController;
import com.abeling.mapp.web.controller.LocationRestController;
import com.abeling.mapp.web.controller.RoomRestController;

public class RootResource extends ResourceSupport {

	public RootResource() {
		this.add(linkTo(methodOn(LocationRestController.class).findAll()).withRel("locations"));
		this.add(linkTo(methodOn(RoomRestController.class).findAll()).withRel("rooms"));
		this.add(linkTo(methodOn(BoxRestController.class).findAll()).withRel("boxes"));
		this.add(linkTo(methodOn(ItemRestController.class).findAll()).withRel("items"));
	}
}
