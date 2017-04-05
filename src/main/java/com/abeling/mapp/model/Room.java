package com.abeling.mapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Room")
public class Room {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	private int floor;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Box> boxes;

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Location location;

	public Room() {
	}

	public Room(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Room(Long id, String name, String description) {
		this.Id = id;
		this.name = name;
		this.description = description;
	}
	
	public Room(Long id, String name, String description, Location location) {
		this.Id = id;
		this.name = name;
		this.description = description;
		this.location = location;
	}

	public Room(Long id, String name, String description, int floor) {
		this.Id = id;
		this.name = name;
		this.description = description;
		this.floor = floor;
	}

	public Room(Long id, String name, String description, int floor, Location location) {
		this.Id = id;
		this.name = name;
		this.description = description;
		this.floor = floor;
		this.location = location;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Set<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(Set<Box> boxes) {
		this.boxes = boxes;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
