package com.abeling.Mapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.abeling.mapp.App;
import com.abeling.mapp.model.Location;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.persistence.LocationRepository;
import com.abeling.mapp.persistence.RoomRepository;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class LocationRestControllerTest {

	private MediaType contentType = new MediaType("application", "hal+json", Charset.forName("utf8")); 
	
	private MockMvc mockMvc;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private List<Location> locationList = new ArrayList<>();
	
	private List<Room> roomList = new ArrayList<>();
			
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	public void setConverts(HttpMessageConverter<?>[] converters){
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().orElse(null);
		assertNotNull("the JSON message converter must not be null", 
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		this.locationRepository.deleteAll();
		
		Location location1 =new Location(1l, "House1", "The nice house");
		Location location2 =new Location(2l, "House2", "The nicer house");
		
		Room room1 = new Room(1l, "Livingroom", "The beautifull livingroom", 1, location1);
		Room room2 = new Room(2l, "Sleepingroom", "The romantic sleepingroom", 2, location1);
		Room room3 = new Room(3l, "Kitchen", "The clean kitchen", 1, location2);
		Room room4 = new Room(4l, "Bathroom", "The clean bathroom", 2, location2);
	
		this.locationList .add(locationRepository.save(location1));
		this.locationList .add(locationRepository.save(location2));

		this.roomList.add(roomRepository.save(room1));
		this.roomList.add(roomRepository.save(room2));
		this.roomList.add(roomRepository.save(room3));
		this.roomList.add(roomRepository.save(room4));

	
	}
	 
	@Test
	public void getLocations() throws Exception {
		mockMvc.perform(get("/locations")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$._embedded.locationResourceList", hasSize(2)))
				.andExpect(jsonPath("$._embedded.locationResourceList[0].location.id", is(this.locationList.get(0).getId().intValue())))
				.andExpect(jsonPath("$._embedded.locationResourceList[0].location.name", is(this.locationList.get(0).getName())))
				.andExpect(jsonPath("$._embedded.locationResourceList[0].location.description", is(this.locationList.get(0).getDescription())))
				.andExpect(jsonPath("$._embedded.locationResourceList[1].location.id", is(this.locationList.get(1).getId().intValue())))
				.andExpect(jsonPath("$._embedded.locationResourceList[1].location.name", is(this.locationList.get(1).getName())))
				.andExpect(jsonPath("$._embedded.locationResourceList[1].location.description", is(this.locationList.get(1).getDescription())));
	} 
	
}
