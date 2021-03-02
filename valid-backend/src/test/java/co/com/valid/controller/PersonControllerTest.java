package co.com.valid.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.valid.Constants;
import co.com.valid.dto.PersonDTO;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;
	private List<PersonDTO> persons;

	@BeforeEach
	void setUp() {
		persons = new ArrayList<>();
		PersonDTO person = new PersonDTO();
		person.setName("diego");
		person.setLastname("ibañez");
		person.setProcessed(true);
		persons.add(person);

		person = new PersonDTO();
		person.setName("alexander");
		person.setLastname("palacios");
		person.setProcessed(true);
		persons.add(person);
	}

	@Test
	@Order(1)
	void createPersons() throws Exception {
		mockMvc.perform(post(Constants.PERSONS_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(persons))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}

	@Test
	@Order(2)
	void findExistPersonById() throws Exception {
		mockMvc.perform(get(Constants.PERSONS_PATH + "/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@Order(3)
	void countPersons() throws Exception {
		mockMvc.perform(get(Constants.PERSONS_PATH)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.size()", is(persons.size())));
	}

	@Test
	void findNotExistPersonById() throws Exception {
		mockMvc.perform(get(Constants.PERSONS_PATH + "/99")).andExpect(status().is4xxClientError());
	}
}
