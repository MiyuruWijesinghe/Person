package com.abc.person.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.abc.person.exception.NoRecordFoundException;
import com.abc.person.exception.ValidateRecordException;
import com.abc.person.model.Person;
import com.abc.person.repository.PersonRepository;
import com.abc.person.resource.PersonResource;
import com.abc.person.service.PersonService;

/**
 * Person Service Implementation
 * 
 ********************************************************************************************************
 *  ###   Date         Author    IT No.        Description
 *-------------------------------------------------------------------------------------------------------
 *    1   10-05-2022   MiyuruW   IT19020990     Created
 *    
 ********************************************************************************************************
 */

@Component
@Transactional(rollbackFor=Exception.class)
public class PersonServiceImpl implements PersonService {

	@Autowired
	private Environment environment;
	
	@Autowired
	private PersonRepository personRepository;
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		return format.format(date);
	}
	
	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Optional<Person> findById(String id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			return Optional.ofNullable(person.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<Person> findByStatus(String status) {
		return personRepository.findByStatus(status);
	}

	@Override
	public Optional<Person> findByName(String name) {
		Optional<Person> person = personRepository.findByName(name);
		if (person.isPresent()) {
			return Optional.ofNullable(person.get());
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Person savePerson(String username, PersonResource personResource) {
		Person person = new Person();
		
        if (personRepository.existsByName(personResource.getName())) {
        	throw new ValidateRecordException(environment.getProperty("name.duplicate"), "message");
		}
		
        person.setName(personResource.getName());
        person.setGender(personResource.getGender());
        person.setNationality(personResource.getNationality());
        person.setStatus(personResource.getStatus());
        person.setCreatedUser(username);
        person.setCreatedDate(formatDate(new Date()));
        personRepository.save(person);
		return person;
	}

	@Override
	public Person updatePerson(String id, String username, PersonResource personResource) {
		Optional<Person> isPresentPerson = personRepository.findById(id);
		if (!isPresentPerson.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		
		if (personRepository.existsByNameAndIdNotIn(personResource.getName(), id)) {
			throw new ValidateRecordException(environment.getProperty("name.duplicate"), "message");
		}
		
		Person person = isPresentPerson.get();
		person.setName(personResource.getName());
        person.setGender(personResource.getGender());
        person.setNationality(personResource.getNationality());
        person.setStatus(personResource.getStatus());
        person.setModifiedUser(username);
        person.setModifiedDate(formatDate(new Date()));
        personRepository.save(person);
		return person;
	}

	@Override
	public String deletePerson(String id) {
		Optional<Person> isPresentPerson = personRepository.findById(id);
		if (!isPresentPerson.isPresent()) {
			throw new NoRecordFoundException(environment.getProperty("common.record-not-found"));
		}
		personRepository.deleteById(id);
		return environment.getProperty("common.deleted");
	}

}
