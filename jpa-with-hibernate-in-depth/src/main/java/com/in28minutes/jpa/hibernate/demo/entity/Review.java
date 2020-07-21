package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rating;

    private String description;

    // @Column annotation is not allowed alongside @ManyToOne, so use the optional
    // property to indicate a mandatory column
    // Fetch Type: By default is EAGER
    @ManyToOne(optional = false)
    private Course course;

    protected Review() {
	super();
    }

    public Review(String rating, String description) {
	super();
	this.rating = rating;
	this.description = description;
    }

    public Long getId() {
	return id;
    }

    public String getRating() {
	return rating;
    }

    public void setRating(String rating) {
	this.rating = rating;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    @Override
    public String toString() {
	return String.format("\nReview[%s %s]", rating, description);
    }

}
