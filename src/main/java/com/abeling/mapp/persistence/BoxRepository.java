package com.abeling.mapp.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Room;

@Transactional
public interface BoxRepository extends CrudRepository<Box, Long>{

	List<Box> findByRoom(Room room);
		

}