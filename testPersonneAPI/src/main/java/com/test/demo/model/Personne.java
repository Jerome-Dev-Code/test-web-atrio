package com.test.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "PERSONNE")
public class Personne implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", updatable = false, nullable = false)
    private Long id;

	@Column(name = "NAME", unique=true, insertable=true, updatable=true, nullable=false)
    private String name;
	
	@Column(name = "FIRSTNAME", insertable=true, updatable=true, nullable=false)
    private String firstName;
	
	@Column(name = "BIRTHDATE", insertable=true, updatable=true, nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
	
	 public Personne() {
	    	super();
	 }
	 
	 public Personne(String name, String firstName, LocalDate birthDate) {
	        this.name = name;
	        this.firstName = firstName;
	        this.birthDate = birthDate;
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
	    if ((birthDate != null) && (currentDate != null)) {
	            return Period.between(birthDate, currentDate).getYears();
	    } else {
	            return 0;
	    }
	}
	 
	@Override
	public String toString() {
		return "Personne [id= " + id + ", nom= " + name + ", prenom= " + firstName + ", date de naissance= "
				+ birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		return result;
	}
}
