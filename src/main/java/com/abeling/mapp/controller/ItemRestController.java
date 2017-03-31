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
import com.abeling.mapp.model.Item;
import com.abeling.mapp.repository.ItemRepository;


@RestController
@RequestMapping("/items")
public class ItemRestController {

	private final ItemRepository itemRepository;
	
	@Autowired
	ItemRestController(ItemRepository itemRepository){
		this.itemRepository = itemRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	Page<Item> getItems(Pageable pageable){
		return this.itemRepository.findAll(pageable);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{itemId}")
	ResponseEntity<?> getItemById(@PathVariable Long itemId){
		Item item = itemRepository.findOne(itemId);
		if (item == null) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
}
