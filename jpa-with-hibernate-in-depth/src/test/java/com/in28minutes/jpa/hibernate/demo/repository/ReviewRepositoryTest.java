package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepo;

    @Autowired
    CourseRepository courseRepo;

    @Test
    @DirtiesContext
    @Transactional
    void insertReview() {
	Course c = new Course("Rust");
	courseRepo.save(c);

	Review r = new Review("3", "Average");
	r.setCourse(c);
	reviewRepo.save(r);

	assertEquals("3", reviewRepo.findById(r.getId()).getRating());
    }
}
