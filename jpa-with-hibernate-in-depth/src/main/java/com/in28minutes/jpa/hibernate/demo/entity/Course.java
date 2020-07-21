package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;

// An annotation that tells JPA that this class is also a table in the DB
@Entity
// @Table(name = "CourseDetails") // Table will be called 'course_details'
@NamedQueries(value = { @NamedQuery(name = CourseRepository.SELECT_ALL_COURSES, query = "select c from Course c"),
	@NamedQuery(name = CourseRepository.SELECT_ALL_H_COURSES, query = "select c from Course c where upper(c.name) like '%H%'") })
public class Course {
    // Indicates that this field is the primary key
    @Id
    // JPA takes care of the primary key generation
    @GeneratedValue
    private Long id;

    @Column(/* name = "fullname", */ nullable = false, unique = true, length = 200)
    private String name;

    @UpdateTimestamp // Hibernate implementation (JPA doesn't provide this)
    private LocalDateTime lastUpdatedDateTime;

    @CreationTimestamp // Hibernate implementation (JPA doesn't provide this)
    private LocalDateTime createdDateTime;

    // If the relationship is Many then it is usually initialised on creation
    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<Review>();

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<Student>();

    // JPA requires the ability to call a a no-argument constructor
    protected Course() {
	super();
    }

    public Course(String name) {
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

    public List<Review> getReviews() {
	return reviews;
    }

    // We don't people to set the reviews from outside, so we introduce 2 methods to
    // add & remove a review
//    public void setReviews(List<Review> reviews) {
//	this.reviews = reviews;
//    }
    public void addReview(Review review) {
	this.reviews.add(review);
    }

    public void removeReview(Review review) {
	this.reviews.remove(review);
    }

    public List<Student> getStudents() {
	return students;
    }

//    public void setStudents(List<Student> students) {
//	this.students = students;
//    }

    public void addStudent(Student student) {
	this.students.add(student);
    }

    public void removeStudent(Student student) {
	this.students.remove(student);
    }

    @Override
    public String toString() {
	return "\nCourse [id=" + id + ", name=" + name + "]";
    }

}
