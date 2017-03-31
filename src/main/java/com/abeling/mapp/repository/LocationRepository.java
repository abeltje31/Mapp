package com.abeling.mapp.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.abeling.mapp.model.Location;

@Transactional
public interface LocationRepository extends PagingAndSortingRepository<Location, Long>{
		

}