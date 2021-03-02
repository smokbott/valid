package co.com.valid.service;

import java.util.List;

import co.com.valid.dto.PersonDTO;

/**
 * Person Service interface
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
public interface PersonService {

	public List<PersonDTO> findAll();

	public PersonDTO add(PersonDTO person);
	
	public PersonDTO findById(long bookId);
	
	public int update(List<PersonDTO> persons);

}