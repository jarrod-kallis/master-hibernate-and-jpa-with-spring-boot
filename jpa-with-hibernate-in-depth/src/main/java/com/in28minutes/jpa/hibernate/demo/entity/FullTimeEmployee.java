package com.in28minutes.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
	super(name);
	this.salary = salary;
    }

    public BigDecimal getSalary() {
	return salary;
    }

    public void setSalary(BigDecimal salary) {
	this.salary = salary;
    }

    @Override
    public String toString() {
	return "\nFullTimeEmployee [" + getName() + ", " + salary + "]";
    }
}
