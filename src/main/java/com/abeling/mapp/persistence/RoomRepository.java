package com.abeling.mapp.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.abeling.mapp.model.Location;
import com.abeling.mapp.model.Room;

@Transactional
public interface RoomRepository extends CrudRepository<Room, Long>{
		
	List<Room> findByLocation(Location location);
}