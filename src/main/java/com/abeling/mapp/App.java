package com.abeling.mapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Item;
import com.abeling.mapp.model.Location;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.persistence.BoxRepository;
import com.abeling.mapp.persistence.ItemRepository;
import com.abeling.mapp.persistence.LocationRepository;
import com.abeling.mapp.persistence.RoomRepository;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	CommandLineRunner init(LocationRepository locationRepository, RoomRepository roomRepository, BoxRepository boxRepository, ItemRepository itemRepository) {
		
		Location location1 = new Location(1l, "sesamestreet", "Sesame Street");
		locationRepository.save(location1);
		
		Room room1 = new Room(1l, "living room","description of a living room", location1);
		Room room2 = new Room(2l, "sleeping room", "description of a sleeping room", location1);
		roomRepository.save(room1);
		roomRepository.save(room2);
		
		Box box1 = new Box(1l, "E01", "Ernies box 1", room1);
		Box box2 = new Box(2l, "E02", "Ernies box 2", room1);
		Box box3 = new Box(3l, "E03", "Ernies box 3", room2);
		Box box4 = new Box(4l, "E04", "Ernies box 4", room2);
		Box box5 = new Box(5l, "B01", "Berts box 1", room1);
		Box box6 = new Box(6l, "B02", "Berts box 2", room1);
		Box box7 = new Box(7l, "B03", "Berts box 3", room2);
		Box box8 = new Box(8l, "B04", "Berts box 4", room2);
		boxRepository.save(box1);
		boxRepository.save(box2);
		boxRepository.save(box3);
		boxRepository.save(box4);
		boxRepository.save(box5);
		boxRepository.save(box6);
		boxRepository.save(box7);
		boxRepository.save(box8);
		
		Item item1 = new Item(1l, "Item1", "This is item 1", box1);
		Item item2 = new Item(2l, "Item2", "This is item 2", box1);
		Item item3 = new Item(3l, "Item3", "This is item 3", box2);
		Item item4 = new Item(4l, "Item4", "This is item 4", box2);
		Item item5 = new Item(5l, "Item5", "This is item 5", box3);
		Item item6 = new Item(6l, "Item6", "This is item 6", box3);
		Item item7 = new Item(7l, "Item7", "This is item 7", box4);
		Item item8 = new Item(8l, "Item8", "This is item 8", box4);
		Item item9 = new Item(9l, "Item9", "This is item 9", box5);
		Item item10 = new Item(10l, "Item10", "This is item 10", box5);
		Item item11 = new Item(11l, "Item11", "This is item 11", box6);
		Item item12 = new Item(12l, "Item12", "This is item 12", box6);
		Item item13 = new Item(13l, "Item13", "This is item 13", box7);
		Item item14 = new Item(14l, "Item14", "This is item 14", box7);
		Item item15 = new Item(15l, "Item15", "This is item 15", box8);
		Item item16 = new Item(16l, "Item16", "This is item 16", box8);
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
		itemRepository.save(item4);
		itemRepository.save(item5);
		itemRepository.save(item6);
		itemRepository.save(item7);
		itemRepository.save(item8);
		itemRepository.save(item9);
		itemRepository.save(item10);
		itemRepository.save(item11);
		itemRepository.save(item12);
		itemRepository.save(item13);
		itemRepository.save(item14);
		itemRepository.save(item15);
		itemRepository.save(item16);
		return null;

	}
}
