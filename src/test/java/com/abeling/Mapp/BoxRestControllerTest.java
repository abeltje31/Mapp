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
import com.abeling.mapp.model.Box;
import com.abeling.mapp.model.Item;
import com.abeling.mapp.model.Room;
import com.abeling.mapp.repository.BoxRepository;
import com.abeling.mapp.repository.ItemRepository;
import com.abeling.mapp.repository.RoomRepository;

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
public class BoxRestControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")); 
		
	private MockMvc mockMvc;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private List<Room> roomList = new ArrayList<>();
	
	private List<Box> boxList = new ArrayList<>();
	
	private List<Item> itemList = new ArrayList<>();
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private BoxRepository boxRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
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
		this.boxRepository.deleteAll();
		
		Room room1 = new Room(1l, "Livingroom", "The beautifull livingroom", 1);
		
		Box box1 = new Box(1l, "MA01", "Maartens box 1", room1);
		Box box2 = new Box(2l, "MA02", "Maartens box 2", room1);
		
		Item item1 = new Item(1l, "Item1", "This is item 1", box1);
		Item item2 = new Item(2l, "Item2", "This is item 2", box1);
		Item item3 = new Item(3l, "Item3", "This is item 3", box2);
		Item item4 = new Item(4l, "Item4", "This is item 4", box2);
		

		this.roomList.add(roomRepository.save(room1));
		
		this.boxList.add(boxRepository.save(box1));
		this.boxList.add(boxRepository.save(box2));
		
		this.itemList.add(itemRepository.save(item1));
		this.itemList.add(itemRepository.save(item2));
		this.itemList.add(itemRepository.save(item3));
		this.itemList.add(itemRepository.save(item4));
		
	}
	
	@Test
	public void getBoxes() throws Exception {
		mockMvc.perform(get("/boxes")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.content", hasSize(2)))
				.andExpect(jsonPath("$.content[0].id", is(this.boxList.get(0).getId().intValue())))
				.andExpect(jsonPath("$.content[0].name", is(this.boxList.get(0).getName())))
				.andExpect(jsonPath("$.content[0].description", is(this.boxList.get(0).getDescription())))
				.andExpect(jsonPath("$.content[0].items", hasSize(2)))
				.andExpect(jsonPath("$.content[1].id", is(this.boxList.get(1).getId().intValue())))
				.andExpect(jsonPath("$.content[1].name", is(this.boxList.get(1).getName())))
				.andExpect(jsonPath("$.content[1].description", is(this.boxList.get(1).getDescription())));
	}
	
}


