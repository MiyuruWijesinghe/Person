package com.abc.person.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abc.person.model.Person;
import com.abc.person.resource.PersonResource;
import com.abc.person.resource.SuccessAndErrorDetailsResource;
import com.abc.person.service.PersonService;

/**
 * Person Controller
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@RestController
@RequestMapping(value = "/person")
@CrossOrigin(origins = "*")
public class PersonController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private PersonService personService;
	
	
	/**
	 * Gets the all persons.
	 *
	 * @return the all persons
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<Object> getAllPersons() {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Person> person = personService.findAll();
		if (!person.isEmpty()) {
			return new ResponseEntity<>((Collection<Person>) person, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the person by id.
	 *
	 * @param id - the id
	 * @return the person by id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getPersonById(@PathVariable(value = "id", required = true) String id) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Person> isPresentPerson = personService.findById(id);
		if (isPresentPerson.isPresent()) {
			return new ResponseEntity<>(isPresentPerson, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Gets the persons by status.
	 *
	 * @param status - the status
	 * @return the persons by status
	 */
	@GetMapping(value = "/status/{status}")
	public ResponseEntity<Object> getPersonsByStatus(@PathVariable(value = "status", required = true) String status) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		List<Person> person = personService.findByStatus(status);
		if (!person.isEmpty()) {
			return new ResponseEntity<>((Collection<Person>) person, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
		
	
	/**
	 * Gets the persons by name.
	 *
	 * @param name - the name
	 * @return the persons by name
	 */
	@GetMapping(value = "/name/{name}")
	public ResponseEntity<Object> getPersonsByName(@PathVariable(value = "name", required = true) String name) {
		SuccessAndErrorDetailsResource responseMessage = new SuccessAndErrorDetailsResource();
		Optional<Person> isPresentPerson = personService.findByName(name);
		if (isPresentPerson.isPresent()) {
			return new ResponseEntity<>(isPresentPerson, HttpStatus.OK);
		} else {
			responseMessage.setMessages(environment.getProperty("common.record-not-found"));
			return new ResponseEntity<>(responseMessage, HttpStatus.NO_CONTENT);
		}
	}
	
	
	/**
	 * Adds the person.
	 *
	 * @param username - the username
	 * @param personResource - the person resource
	 * @return the response entity
	 */
	@PostMapping(value = "/{username}/save")
	public ResponseEntity<Object> addPerson(@PathVariable(value = "username", required = true) String username,
											@Valid @RequestBody PersonResource personResource) {
		Person person = personService.savePerson(username, personResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.saved"), person);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
	
	/**
	 * Update person.
	 *
	 * @param username - the username
	 * @param id - the id
	 * @param personResource - the person resource
	 * @return the response entity
	 */
	@PutMapping(value = "/{username}/{id}")
	public ResponseEntity<Object> updatePerson(@PathVariable(value = "username", required = true) String username,
											   @PathVariable(value = "id", required = true) String id,
											   @Valid @RequestBody PersonResource personResource) {
		Person person = personService.updatePerson(id, username, personResource);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(environment.getProperty("common.updated"), person);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.OK);
	}
	
	
	/**
	 * Delete person.
	 *
	 * @param id - the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deletePerson(@PathVariable(value = "id", required = true) String id) {
		String message = personService.deletePerson(id);
		SuccessAndErrorDetailsResource successDetailsDto = new SuccessAndErrorDetailsResource(message);
		return new ResponseEntity<>(successDetailsDto, HttpStatus.CREATED);
	}
	
}