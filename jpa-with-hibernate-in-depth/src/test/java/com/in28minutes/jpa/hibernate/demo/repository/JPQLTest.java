package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

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

    @Test
    void findCoursesWithoutStudents() {
	// select c.* from course c where c.id not in (select distinct sc.course_id from
	// student_course sc)
	List<Course> courseList = em.createQuery("select c from Course c where c.students is empty", Course.class)
		.getResultList();

	logger.info("\n\n\n{}\nCourse List: {}\n\n\n", "findCoursesWithoutStudents()", courseList);
    }

    @Test
    void findCoursesWithAtLeast2Students() {
	// select c.* from course c
	// where (select count(*) from student_course sc where sc.course_id = c.id) >= 2
	List<Course> courseList = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class)
		.getResultList();

	logger.info("\n\n\n{}\nCourse List: {}\n\n\n", "findCoursesWithAtLeast2Students()", courseList);
    }

    @Test
    void orderCoursesByNumberOfStudents() {
	// select c.* from course c
	// order by (select count(*) from student_course sc where sc.course_id = c.id)
	// desc
	List<Course> courseList = em.createQuery("select c from Course c order by size(c.students) desc", Course.class)
		.getResultList();

	logger.info("\n\n\n{}\nCourse List: {}\n\n\n", "orderCoursesByNumberOfStudents()", courseList);
    }

    @Test
    void findStudentsWithPassportContaining567() {
	// select * from student s
	// join passport p on p.id = s.passport_id
	// where p.number like '%567%'
	List<Student> studentList = em
		.createQuery("select s from Student s where s.passport.number like '%567%'", Student.class)
		.getResultList();

	logger.info("\n\n\n{}\nStudent List: {}\n\n\n", "findStudentsWithPassportContaining567()", studentList);
    }

    // left join: select c, s from Course c left join c.students
    // cross join: select c, s from Course c, Student s
    @Test
    @SuppressWarnings("unchecked")
    void join() {
	List<Object[]> list = em.createQuery("select c, s from Course c join c.students s").getResultList();

	logger.info("\n\n\n{}\nList Size: {}", "join()", list.size());
	for (Object[] object : list) {
	    logger.info("{} {}", object[0], object[1]);
	}
	logger.info("\n\n\n");
    }

    @Test
    @SuppressWarnings("unchecked")
    void leftJoin() {
	List<Object[]> list = em.createQuery("select c, s from Course c left join c.students s").getResultList();

	logger.info("\n\n\n{}\nList Size: {}", "leftJoin()", list.size());
	for (Object[] object : list) {
	    logger.info("{} {}", object[0], object[1]);
	}
	logger.info("\n\n\n");
    }

    @Test
    @SuppressWarnings("unchecked")
    void crossJoin() { // Cartesian product
	List<Object[]> list = em.createQuery("select c, s from Course c, Student s").getResultList();

	logger.info("\n\n\n{}\nList Size: {}", "crossJoin()", list.size());
	for (Object[] object : list) {
	    logger.info("{} {}", object[0], object[1]);
	}
	logger.info("\n\n\n");
    }
}
