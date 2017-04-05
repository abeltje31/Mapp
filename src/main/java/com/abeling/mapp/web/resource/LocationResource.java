package com.abeling.mapp.web.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.abeling.mapp.model.Location;
import com.abeling.mapp.web.controller.LocationRestController;
import com.abeling.mapp.web.controller.RoomRestController;
import com.fasterxml.jackson.annotation.JsonCreator;


public class LocationResource extends ResourceSupport {

	private final Location location;
	
	@JsonCreator
	public LocationResource(@NotNull final Location location) {
		this.location = location;
		this.add(linkTo(methodOn(LocationRestController.class).getLocationById(location.getId())).withSelfRel());
		this.add(linkTo(methodOn(RoomRestController.class).findFromLocation(location.getId())).withRel("rooms"));
	}
	
	public Location getLocation(){
		return location;
	}
	
}
