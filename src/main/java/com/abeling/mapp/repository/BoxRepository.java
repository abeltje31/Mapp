package com.abeling.mapp.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.abeling.mapp.model.Box;

@Transactional
public interface BoxRepository extends PagingAndSortingRepository<Box, Long>{
		

}