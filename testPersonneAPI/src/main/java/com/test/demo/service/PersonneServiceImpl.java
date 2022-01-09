package com.test.demo.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.dao.PersonneRepository;
import com.test.demo.exception.BusinessResourceException;
import com.test.demo.model.Personne;

@Service
public class PersonneServiceImpl implements PersonneService{

	private static final Logger logger = LoggerFactory.getLogger(PersonneServiceImpl.class);
	private PersonneRepository personneRepository;
	
	public PersonneServiceImpl() {
		super();
	}

	@Autowired
	public PersonneServiceImpl(PersonneRepository personneRepository) {
		super();
		this.personneRepository = personneRepository;
	}

	@Override
	public List<Personne> getAllPersonnes() {
		// TODO Auto-generated method stub
		return new ArrayList<Personne>(personneRepository.findAll());
	}
	
	@Transactional(readOnly=false)
	public Personne savePersonne(Personne personne) throws BusinessResourceException{
		try {
				Personne personneToSave = new Personne();
				if(personne.calculateAge(personne.getBirthDate(), LocalDate.now(Clock.systemUTC())) >= 150) {
					throw new BusinessResourceException("AgeError", "la personne a 150 ans ou plus");
				}else {
					personneToSave.setName(personne.getName());
					personneToSave.setFirstName(personne.getFirstName());
					personneToSave.setBirthDate(personne.getBirthDate());
					personneRepository.saveAndFlush(personneToSave);
					return personneToSave;
				}
			
		}catch(DataIntegrityViolationException ex){
			logger.error("La personne n'existe pas", ex);
			throw new BusinessResourceException("DuplicateValueError", "Cette personne existe déjà : "+personne.getName(), HttpStatus.CONFLICT);
		} catch (BusinessResourceException e) {
			logger.error("Utilisateur non existant", e);
			throw new BusinessResourceException("UserNotFound", "Aucune personne avec l'identifiant: "+personne.getId(), HttpStatus.NOT_FOUND);
		} catch(Exception ex){
			logger.error("Erreur technique de création ou de mise à jour de la personne", ex);
			throw new BusinessResourceException("SaveOrUpdateUserError", "Erreur technique de création ou de mise à jour de la personne: "+personne.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
