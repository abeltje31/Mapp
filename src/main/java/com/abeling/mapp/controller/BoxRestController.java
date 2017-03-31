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
import com.abeling.mapp.model.Box;
import com.abeling.mapp.repository.BoxRepository;
import com.abeling.mapp.repository.ItemRepository;


@RestController
@RequestMapping("/boxes")
public class BoxRestController {

	private final BoxRepository boxRepository;
	
	@Autowired
	BoxRestController(BoxRepository boxRepository, ItemRepository itemRepository){
		this.boxRepository = boxRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	Page<Box> getBoxes(Pageable pageable){
		return this.boxRepository.findAll(pageable);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{boxId}")
	ResponseEntity<?> getBoxById(@PathVariable Long boxId){
		Box box = boxRepository.findOne(boxId);
		if (box == null) {
			return new ResponseEntity<Box>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Box>(box, HttpStatus.OK);
	}
	
}
