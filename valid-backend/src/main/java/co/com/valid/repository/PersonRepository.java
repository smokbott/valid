package co.com.valid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.valid.model.Person;

/**
 * Person Repository interface
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
