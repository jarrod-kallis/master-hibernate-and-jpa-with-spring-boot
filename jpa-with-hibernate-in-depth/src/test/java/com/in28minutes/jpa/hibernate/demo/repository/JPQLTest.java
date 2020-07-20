package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

// Java Persistence Query Language
@SpringBootTest
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void findAll() {
	// Query entity, and not table
	List<Course> courseList = em.createNamedQuery(CourseRepository.SELECT_ALL_COURSES, Course.class)
		.getResultList();

	logger.info("\n{}\nCourse List: {}", CourseRepository.SELECT_ALL_COURSES, courseList);
    }

    @Test
    void findWithWhere() {
	List<Course> courseList = em.createNamedQuery(CourseRepository.SELECT_ALL_H_COURSES, Course.class)
		.getResultList();

	logger.info("\n{}\nCourse List: {}", CourseRepository.SELECT_ALL_H_COURSES, courseList);
    }
}
