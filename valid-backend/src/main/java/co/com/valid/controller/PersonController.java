package co.com.valid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.valid.Constants;
import co.com.valid.dto.PersonDTO;
import co.com.valid.exception.ResourceNotFoundException;
import co.com.valid.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Person Controller class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
@RestController
@RequestMapping(path = Constants.PERSONS_PATH)
@Api(value = "person rest service", description = "This API has a CRUD for person valid company")
public class PersonController {

	@Autowired
	private PersonService service;

	/**
	 * Get all persons
	 * 
	 * @return list of persons
	 */
	@CrossOrigin
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find all persons", notes = "Return all persons", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved persons"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<PersonDTO> findAll() {
		List<PersonDTO> result = service.findAll();
		if (result.size() > 0)
			return result;
		throw new ResourceNotFoundException("find not found");
	}


	/**
	 * Create one persons
	 * 
	 * @param person
	 * @return person created
	 */
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create one or more persons", notes = "Return persons created", response = PersonDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved person"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 409, message = "The person is already registered")})
	public PersonDTO add(@RequestBody PersonDTO persons) {
		return service.add(persons);
	}
	
	/**
	 * Get a person by id
	 * 
	 * @param id
	 * @return person
	 */
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Find a book", notes = "Return a person by Id", response = PersonDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved person"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public PersonDTO getBookById(@PathVariable(value = "id") long id) {
		return service.findById(id);
	}

	/**
	 * Edit person
	 * 
	 * @param newPerson
	 * @param personId
	 * @return edited person
	 */
	@CrossOrigin
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Edit a persons", notes = "Return number persons modified", response = Integer.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved person"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public int edit(@RequestBody List<PersonDTO> persons) {
		return service.update(persons);
	}
}
