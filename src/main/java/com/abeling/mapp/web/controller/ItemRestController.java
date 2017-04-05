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
import com.abeling.mapp.model.Item;
import com.abeling.mapp.persistence.BoxRepository;
import com.abeling.mapp.persistence.ItemRepository;
import com.abeling.mapp.web.error.Checks;
import com.abeling.mapp.web.resource.ItemResource;


@RestController
public class ItemRestController {

	private final BoxRepository boxRepository;
	private final ItemRepository itemRepository;
	
	@Autowired
	ItemRestController(BoxRepository boxRepository, ItemRepository itemRepository){
		this.boxRepository = boxRepository;
		this.itemRepository = itemRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/items")
	public Resources<ItemResource> findAll(){
		final List<Item> items = (List<Item>) itemRepository.findAll();
		final List<ItemResource> itemResources = items.stream().map(ItemResource::new).collect(Collectors.toList());
		return new Resources<>(itemResources);	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/boxes/{boxId}/items")
	public Resources<ItemResource> findFromBox(@PathVariable Long boxId){
		final Box box = Checks.checkEntityExists(boxRepository.findOne(boxId), "Room not found");
		final List<Item> items = (List<Item>) itemRepository.findByBox(box);
		final List<ItemResource> itemResources = items.stream().map(ItemResource::new).collect(Collectors.toList());
		return new Resources<>(itemResources);	
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/items/{itemId}")
	public ItemResource getItemById(@PathVariable Long itemId){
		final Item item = Checks.checkEntityExists(itemRepository.findOne(itemId), "Item not found");
		final ItemResource itemResource = new ItemResource(item);
		return itemResource;
	}
	
}
