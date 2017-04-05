package com.abeling.mapp.persistence;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Item;

@Transactional
public interface ItemRepository extends PagingAndSortingRepository<Item, Long>{
	
	List<Item> findByBox(Box box);

}