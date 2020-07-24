package com.in28minutes.jpa.hibernate.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
	super(name);
	this.hourlyWage = hourlyWage;
    }

    public BigDecimal getHourlyWage() {
	return hourlyWage;
    }

    public void setHourlyWage(BigDecimal hourlyWage) {
	this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
	return "\nPartTimeEmployee [" + getName() + ", " + hourlyWage + "]";
    }
}
