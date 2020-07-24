package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String number;

    // We can now look up a Student linked to a Passport
    // mappedBy = prevents a foreign key column to Student in the Passport table,
    // and instead makes Student the owning entity.
    // "passport" is the property on the Student entity
    @OneToOne(/* fetch = FetchType.EAGER, */ mappedBy = "passport")
    private Student student;

    protected Passport() {
	super();
    }

    public Passport(String number) {
	super();
	this.number = number;
    }

    public Long getId() {
	return id;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public Student getStudent() {
	return student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    @Override
    public String toString() {
	return "\nPassport [id=" + id + ", number=" + number + "]";
    }

}
