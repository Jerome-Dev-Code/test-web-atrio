package com.test.demo.service;

import java.util.List;

import com.test.demo.exception.BusinessResourceException;
import com.test.demo.model.Personne;

public interface PersonneService {

	List<Personne> getAllPersonnes();
	
	Personne savePersonne(Personne p) throws BusinessResourceException;
	
}
