package com.abeling.mapp.web.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.abeling.mapp.model.Item;
import com.abeling.mapp.web.controller.BoxRestController;
import com.abeling.mapp.web.controller.ItemRestController;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ItemResource extends ResourceSupport {

	private final Item item;
	
	@JsonCreator
	public ItemResource(@NotNull final Item item){
		this.item = item;
		this.add(linkTo(methodOn(ItemRestController.class).getItemById(item.getId())).withSelfRel());
		this.add(linkTo(methodOn(BoxRestController.class).getBoxById(item.getBox().getId())).withRel("box"));
	}
	
	public Item getItem(){
		return item;
	}
}
