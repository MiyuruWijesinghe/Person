package com.abc.person.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.abc.person.model.Person;

/**
 * Person Repository
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

	public Optional<Person> findById(String id);
	
	public List<Person> findByStatus(String status);

	public Optional<Person> findByName(String name);

	public Optional<Person> findByIdAndStatus(String id, String name);

	public Boolean existsByName(String name);

	public Boolean existsByNameAndIdNotIn(String name, String id);
	
	public void deleteById(String id);
}