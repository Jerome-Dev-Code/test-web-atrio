package com.test.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.model.Personne;
import com.test.demo.service.PersonneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/personne/*")
public class PersonneController {

	@Autowired
	private PersonneService personneService;	
	
	@GetMapping(value = "/personnes")
	public ResponseEntity<List<Personne>> getAllPersonnes() {
		List<Personne> personnes = personneService.getAllPersonnes();
		return new ResponseEntity<List<Personne>>(personnes, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/personne")
	@Transactional
	public ResponseEntity<Personne> saveUser(@RequestBody Personne personne) {
		
		Personne personneToSave = personneService.savePersonne(personne);		
 		return new ResponseEntity<Personne>(personneToSave, HttpStatus.CREATED);
 	}
}
