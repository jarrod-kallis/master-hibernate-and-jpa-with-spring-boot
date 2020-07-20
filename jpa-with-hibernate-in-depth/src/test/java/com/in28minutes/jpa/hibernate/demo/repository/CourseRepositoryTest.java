package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

// Required Annotation to make the test run with a Spring Context, so that it's aware of all the Entities, Repos, etc...
@SpringBootTest // (classes = DemoApplication.class) <- Will launch the specified Spring Context
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepo;

    @Test
    void findById() {
	Course c = courseRepo.findById(10001L);
	assertEquals("History", c.getName());
    }

    @Test
    // Tells spring that we are modifying data within this test.
    // Spring will roll back all modifications made in this test.
    @DirtiesContext
    void createAndFindGeographyCourse() {
	Course c = courseRepo.save(new Course("Geography"));
	assertEquals("Geography", c.getName());

	c = courseRepo.findById(c.getId());
	assertEquals("Geography", c.getName());
    }

    @Test
    @DirtiesContext // Will allow findEnglishCourse to pass, because the delete will be rolled back
    void removeEnglishCourse() {
	courseRepo.deleteById(10002L);
	assertNull(courseRepo.findById(10002L));
    }

    @Test
    void findEnglishCourse() {
	Course c = courseRepo.findById(10002L);
	assertEquals("English", c.getName());
    }

    @Test
    @DirtiesContext
    void createAndUpdateTEFLCourse() {
	Course c = courseRepo.save(new Course("TEFL"));
	assertEquals("TEFL", c.getName());

	c.setName("Teaching English as a Foreign Language");
	courseRepo.save(c);

	c = courseRepo.findById(c.getId());
	assertEquals("Teaching English as a Foreign Language", c.getName());
    }

    @Test
    @DirtiesContext
    void playground() {
	courseRepo.playground();
    }
}