package com.abeling.mapp;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Item;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.repository.BoxRepository;
import com.abeling.mapp.repository.ItemRepository;
import com.abeling.mapp.repository.RoomRepository;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	CommandLineRunner init(RoomRepository roomRepository, BoxRepository boxRepository, ItemRepository itemRepository) {
		Room room = roomRepository.save(new Room("livingroom", "description of a living room"));
		return (evt) -> Arrays.asList("slaapkamer,woonkamer,keuken,badkamer".split(","))
				.forEach(
						a -> {
							Box box = boxRepository.save(new Box(a, "description of " + a, room));
							itemRepository.save(new Item("item 1", "description of item 1 in box " + a, box));
							itemRepository.save(new Item("item 2", "description of item 2 in box " + a, box));
							
						});
	}
}