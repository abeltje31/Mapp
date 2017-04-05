package com.abeling.mapp.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abeling.mapp.web.resource.RootResource;

@RestController
public class RootRestController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public RootResource root(){
		return new RootResource();
	}
	
}
