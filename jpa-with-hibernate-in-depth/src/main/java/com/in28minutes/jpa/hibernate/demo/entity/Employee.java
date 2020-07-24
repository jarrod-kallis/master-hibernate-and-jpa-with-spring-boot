package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Employee() {
	super();
    }

    public Employee(String name) {
	super();
	this.name = name;
    }

    public Long getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "\nEmployee [" + name + "]";
    }
}
