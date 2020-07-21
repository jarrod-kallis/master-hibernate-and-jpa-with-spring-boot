package com.in28minutes.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE", // Name of the Joim Table
	    joinColumns = { @JoinColumn(name = "STUDENT_ID") }, // Name of the foreign key column for this entity
	    inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") }) // Name of the foreign key column for other entity
    private List<Course> courses = new ArrayList<Course>();

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

    public List<Course> getCourses() {
	return courses;
    }

//    public void setCourses(List<Course> courses) {
//	this.courses = courses;
//    }

    public void addCourse(Course course) {
	this.courses.add(course);
    }

    public void removeCourse(Course course) {
	this.courses.remove(course);
    }

    @Override
    public String toString() {
	return "\nStudent [id=" + id + ", name=" + name + "]";
    }

}
