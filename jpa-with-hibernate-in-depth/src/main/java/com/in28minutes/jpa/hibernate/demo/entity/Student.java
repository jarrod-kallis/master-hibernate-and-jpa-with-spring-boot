package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@Entity
@NamedQueries(value = {
	@NamedQuery(name = StudentRepository.SELECT_ALL_STUDENTS_AND_THEIR_ASSOCIATED_PASSPORTS, query = "select s.name, p.number from Student s left outer join Passport p on p.id = s.passport.id") })
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "fullname", nullable = false, unique = true, length = 200)
    private String name;

    // This fields is used in the "mappedBy" property on Passport
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    protected Student() {
	super();
    }

    public Student(String name) {
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

    public Passport getPassport() {
	return passport;
    }

    public void setPassport(Passport passport) {
	this.passport = passport;
    }

    @Override
    public String toString() {
	return "\nStudent [id=" + id + ", name=" + name + "]";
    }

}
