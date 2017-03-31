package com.abeling.mapp.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.abeling.mapp.model.Item;

@Transactional
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{
		

}