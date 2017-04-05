package com.abeling.mapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abeling.mapp.model.Location;
import com.abeling.mapp.persistence.LocationRepository;
import com.abeling.mapp.web.resource.LocationResource;
import com.abeling.mapp.web.error.Checks;


@RestController
public class LocationRestController {

	private final LocationRepository locationRepository; 
	
	
	@Autowired
	LocationRestController(LocationRepository locationRepository){
		this.locationRepository  = locationRepository;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/locations")
	public Resources<LocationResource> findAll(){
		final List<Location> locations = (List<Location>) locationRepository.findAll();
		final List<LocationResource> locationResources = locations.stream().map(LocationResource::new).collect(Collectors.toList());
		return new Resources<>(locationResources);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="locations/{locationId}")
	public LocationResource getLocationById(@PathVariable Long locationId){
		final Location location = Checks.checkEntityExists(locationRepository.findOne(locationId), "Location not found");
		final LocationResource locationResource = new LocationResource(location);
		return locationResource;
	}
}
