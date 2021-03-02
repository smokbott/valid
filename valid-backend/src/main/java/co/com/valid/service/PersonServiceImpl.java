package co.com.valid.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.valid.dto.PersonDTO;
import co.com.valid.exception.ResourceConflicException;
import co.com.valid.exception.ResourceNotFoundException;
import co.com.valid.mapper.PersonMapper;
import co.com.valid.model.Person;
import co.com.valid.repository.PersonRepository;

/**
 * Person Service implementation
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	final String PERSON_NOT_FOUND = "person not found with id :";
	final String PERSON_ALREADY = "The person is already registered";

	@Autowired
	private PersonRepository repository;

	@Override
	public List<PersonDTO> findAll() {
		List<Person> persons = this.repository.findAll();
		List<PersonDTO> result = new ArrayList<>();
		for (int i = 0; i < persons.size(); i++) {
			PersonDTO dto = new PersonDTO();
			BeanUtils.copyProperties(persons.get(i), dto);
			result.add(dto);
		}
		return result;
	}

	@Override
	public PersonDTO add(PersonDTO person) {
		try {
			if (person == null) {
				return null;
			}
			PersonDTO result = new PersonDTO();
			Person newPerson = PersonMapper.personDTOToPerson(person);
			newPerson.setCreateDate(new Date());
			result = PersonMapper.personToPersonDTO(this.repository.save(newPerson));
			return result;
		} catch (DataIntegrityViolationException e) {
			throw new ResourceConflicException(PERSON_ALREADY);
		} 
	}
	
	@Override
	public PersonDTO findById(long id) {
		Optional<Person> person = this.repository.findById(id);
		if (!person.isPresent()) {
			throw new ResourceNotFoundException(PERSON_NOT_FOUND + id);
		}
		return PersonMapper.personToPersonDTO(person.get());
	}

	@Override
	public int update(List<PersonDTO> persons) {
		int result = 0;
		for (int i = 0; i < persons.size(); i++) {
			try {
				PersonDTO personDTO = persons.get(i);
				Optional<Person> person = this.repository.findById(personDTO.getId());
				if (!person.isPresent()) {
					continue;
				}
				person.get().setProcessed(personDTO.isProcessed());
				person.get().setUpdateDate(new Date());
				this.repository.save(person.get());
				result++;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
