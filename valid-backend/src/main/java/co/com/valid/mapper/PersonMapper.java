package co.com.valid.mapper;

import org.springframework.beans.BeanUtils;

import co.com.valid.dto.PersonDTO;
import co.com.valid.model.Person;

/**
 * Person Mapper class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
public class PersonMapper {

	private PersonMapper() {
	}

	public static PersonDTO personToPersonDTO(Person person) {
		PersonDTO result = new PersonDTO();
		BeanUtils.copyProperties(person, result);
		return result;
	}

	public static Person personDTOToPerson(PersonDTO person) {
		Person result = new Person();
		BeanUtils.copyProperties(person, result);
		return result;
	}
	
}
