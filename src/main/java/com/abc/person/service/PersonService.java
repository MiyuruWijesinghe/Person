package com.abc.person.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.abc.person.model.Person;
import com.abc.person.resource.PersonResource;

/**
 * Person Service
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Service
public interface PersonService {

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Person> findAll();
	
	
	/**
	 * Find by id.
	 *
	 * @param id - the id
	 * @return the optional
	 */
	public Optional<Person> findById(String id);
	
	
	/**
	 * Find by status.
	 *
	 * @param status - the status
	 * @return the list
	 */
	public List<Person> findByStatus(String status);
	
	
	/**
	 * Find by name.
	 *
	 * @param name - the name
	 * @return the optional
	 */
	public Optional<Person> findByName(String name);
	
	
	/**
	 * Save person.
	 *
	 * @param username - the username
	 * @param personResource - the person resource
	 * @return the person
	 */
	public Person savePerson(String username, PersonResource personResource);
	
	
	/**
	 * Update person.
	 *
	 * @param id - the id
	 * @param username - the username
	 * @param personResource - the person resource
	 * @return the person
	 */
	public Person updatePerson(String id, String username, PersonResource personResource);
	
	
	/**
	 * Delete person.
	 *
	 * @param id - the id
	 * @return the string
	 */
	public String deletePerson(String id);

}
