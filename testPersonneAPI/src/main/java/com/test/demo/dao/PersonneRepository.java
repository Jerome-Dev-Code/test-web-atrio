package com.test.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

	List<Personne> findAll();
	
	@SuppressWarnings("unchecked")
	Personne saveAndFlush(Personne p);
}
