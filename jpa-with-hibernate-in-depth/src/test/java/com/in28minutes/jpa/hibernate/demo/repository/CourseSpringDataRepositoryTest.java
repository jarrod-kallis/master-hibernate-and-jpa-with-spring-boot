package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@SpringBootTest
class CourseSpringDataRepositoryTest extends BaseTest {

    @Autowired
    private CourseSpringDataRepository repo;

    @Test
    public void courseExists() {
	Optional<Course> course = repo.findById(10001L);
	assertTrue(course.isPresent());
    }

    @Test
    public void courseDoesNotExist() {
	Optional<Course> course = repo.findById(20001L);
	assertFalse(course.isPresent());
    }

    @Test
    @DirtiesContext
    public void insertCourse() {
	Course course = repo.save(new Course("Garmin course"));
	Optional<Course> courseWrapper = repo.findById(course.getId());
	assertTrue(courseWrapper.isPresent());
    }

    @Test
    @DirtiesContext
    public void updateCourse() {
	Course course = repo.findById(10001L).get();
	course.setName("Garmin course - updated");
	course = repo.save(course);

	Optional<Course> courseWrapper = repo.findById(course.getId());
	assertEquals("Garmin course - updated", courseWrapper.get().getName());
    }

    @Test
    @DirtiesContext
    public void deleteCourse() {
	repo.deleteById(10004L);
	Optional<Course> courseWrapper = repo.findById(10004L);

	assertFalse(courseWrapper.isPresent());
    }

    @Test
    public void sortCoursesDescending() {
	Sort sort = Sort.by(Sort.Direction.DESC, new String[] { "name" });
	log("Sorted Courses -> " + repo.findAll(sort));
    }

    @Test
    public void paginateCourses() {
	PageRequest pageRequest = PageRequest.of(0, 3);
	Page<Course> coursePage = repo.findAll(pageRequest);
	log("Courses - First Page: " + coursePage.getContent());

	Pageable nextCoursePage = coursePage.nextPageable();
	coursePage = repo.findAll(nextCoursePage);
	log("Courses - Second Page: " + coursePage.getContent());

	if (coursePage.hasNext() == true) {
	    nextCoursePage = coursePage.nextPageable();
	    coursePage = repo.findAll(nextCoursePage);
	    log("Courses - Third Page: " + coursePage.getContent());
	}
    }
}
