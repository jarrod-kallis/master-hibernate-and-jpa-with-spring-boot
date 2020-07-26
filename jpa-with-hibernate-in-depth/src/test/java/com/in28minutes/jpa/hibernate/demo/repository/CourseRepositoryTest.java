package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

// Required Annotation to make the test run with a Spring Context, so that it's aware of all the Entities, Repos, etc...
@SpringBootTest // (classes = DemoApplication.class) <- Will launch the specified Spring Context
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    StudentRepository studentRepo;

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
    @DirtiesContext // Will allow findTempCourse to pass, because the delete will be rolled back
    void removeTempCourse() {
	courseRepo.deleteById(10004L);
	assertNull(courseRepo.findById(10004L));
    }

    @Test
    void findTempCourse() {
	Course c = courseRepo.findById(10004L);
	assertEquals("Temp Course", c.getName());
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
    @Transactional
    void getCourseAndAssociatedStudents() {
	Course c = courseRepo.findById(10003L);
	List<Student> students = c.getStudents();

	logger.info("\n\n\nCourse {} Students: {}\n\n\n", c.getId(), students);

	assertEquals(students.size(), 2);
	assertEquals("Ben Kallis", students.get(0).getName());
	assertEquals("Tessa Kallis", students.get(1).getName());
    }

    @Test
    void removeCourseThatHasAStudentEnrolled() {
	// An exception should be thrown, because the course has students linked to it
	assertThrows(DataIntegrityViolationException.class, () -> {
	    courseRepo.deleteById(10003L);
	});
    }

    // Cache Testing

    @Test
    // In order to use FLC this method must be in a single transaction
    @Transactional
    void findById_FirstLevelCache() {
	// This should result in an SQL
	logger.info("\n\n\nFirst Level Cache Test - First Call - Course 10001: {}\n\n\n", courseRepo.findById(10001L));
	// This should result in no SQl, the data will be retrieved from the FLC
	logger.info("\n\n\nFirst Level Cache Test - Second Call - Course 10001: {}\n\n\n", courseRepo.findById(10001L));
    }
}