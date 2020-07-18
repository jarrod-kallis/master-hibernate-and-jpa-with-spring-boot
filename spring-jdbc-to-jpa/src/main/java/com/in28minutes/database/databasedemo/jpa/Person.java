package com.in28minutes.database.databasedemo.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
//@Table(name = "person")
@NamedQuery(name = PersonRepository.FIND_ALL_PERSONS, query = "select p from Person p")
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String location;

    @Column(name = "dob")
    private Date dateOfBirth;

    public Person() {
	super();
    }

    public Person(String name, String location, Date dateOfBirth) {
	super();
	this.name = name;
	this.location = location;
	this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public Date getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
	return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", dateOfBirth=" + dateOfBirth
		+ "]";
    }

}
