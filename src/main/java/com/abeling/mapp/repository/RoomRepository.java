package com.abeling.mapp.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.abeling.mapp.model.Room;

@Transactional
public interface RoomRepository extends PagingAndSortingRepository<Room, Long>{
		

}