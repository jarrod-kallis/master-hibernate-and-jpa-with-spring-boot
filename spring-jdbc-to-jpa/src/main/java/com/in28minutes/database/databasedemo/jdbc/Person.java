package com.in28minutes.database.databasedemo.jdbc;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private String location;
    private Date dob;

    public Person() {
	super();
    }

    public Person(int id, String name, String location, Date dob) {
	super();
	this.id = id;
	this.name = name;
	this.location = location;
	this.dob = dob;
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

    public Date getDob() {
	return dob;
    }

    public void setDob(Date dob) {
	this.dob = dob;
    }

    @Override
    public String toString() {
	return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", dob=" + dob + "]";
    }

}
