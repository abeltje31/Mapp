package com.abeling.mapp.persistence;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.abeling.mapp.model.Location;

@Transactional
public interface LocationRepository extends CrudRepository<Location, Long>{
		

}