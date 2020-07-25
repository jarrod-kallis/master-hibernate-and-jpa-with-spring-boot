package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@SpringBootTest
class CriteriaQueryTest extends BaseTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void findAllCourses() {
	// "select c from Course c"

	// Use Criteria Builder to create a Query
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// Course.class indicates the class type in the result
	CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

	// Indicate which tables are involved in the query
	Root<Course> courseRoot = createQuery.from(Course.class);

	// Build the query
	List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

	log("\n\n\nCriteria Query - All Courses: " + courses + "\n\n\n");
    }

    @Test
    public void findAllCoursesWithNameLikeH() {
	// select c from Course c where upper(c.name) like '%H%'"

	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

	Root<Course> courseRoot = createQuery.from(Course.class);

	Predicate nameLikeH = criteriaBuilder.like(criteriaBuilder.upper(courseRoot.get("name")), "%H%");
	createQuery.where(nameLikeH);

	List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

	log("\n\n\nCriteria Query - All Courses where name like '%H%': " + courses + "\n\n\n");
    }

    @Test
    public void findAllCoursesWithNoStudents() {
	// "select c from Course c where c.students is empty"

	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);

	Root<Course> courseRoot = createQuery.from(Course.class);

	Predicate noStudents = criteriaBuilder.isEmpty(courseRoot.get("students"));
	createQuery.where(noStudents);

	List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();

	log("\n\n\nCriteria Query - All Courses with no students: " + courses + "\n\n\n");
    }

    @Test
    public void findAllCoursesAndTheirStudents() {
	// "select c, s from Course c JOIN c.students s"

//	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//	CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
//
//	Root<Course> courseRoot = createQuery.from(Course.class);
//	/* Join<Object, Object> join = */ courseRoot.join("students");
//
//	List<Course> courses = em.createQuery(createQuery.select(courseRoot)).getResultList();
//
//	log("\n\n\nCriteria Query - All Courses and students: " + courses + "\n\n\n");

	// -----------------------------------

	// https://stackoverflow.com/questions/41982998/hibernate-criteriabuilder-to-join-multiple-tables

	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Object[]> createQuery = criteriaBuilder.createQuery(Object[].class);

	Root<Course> courseRoot = createQuery.from(Course.class);
	Join<Course, Student> studentJoin = courseRoot.join("students");

	createQuery.multiselect(courseRoot.get("name"), studentJoin.get("name"));

	List<Object[]> coursesAndStudents = em.createQuery(createQuery).getResultList();

	log("\n\n\nCriteria Query - All Courses and students: ");
	String log = "";
	for (Object[] courseAndStudent : coursesAndStudents) {
	    String courseName = courseAndStudent[0].toString();
	    String studentName = courseAndStudent[1].toString();

	    log += "\nCourse: " + courseName + ", Student: " + studentName;
	}
	log(log + "\n\n\n");
    }

    @Test
    public void findAllCoursesEvenWithNoStudents() {
	// "select c, s from Course c LEFT JOIN c.students s"

	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Object[]> createQuery = criteriaBuilder.createQuery(Object[].class);

	Root<Course> courseRoot = createQuery.from(Course.class);
	Join<Object, Object> studentJoin = courseRoot.join("students", JoinType.LEFT);

	List<Object[]> coursesAndStudents = em.createQuery(createQuery.multiselect(courseRoot, studentJoin))
		.getResultList();

	log("\n\n\nCriteria Query - All Courses and students, even with no students:");
	String log = "";
	for (Object[] courseAndStudent : coursesAndStudents) {
	    Course course = ((Course) courseAndStudent[0]);
	    Student student = ((Student) courseAndStudent[1]);

	    log += course + " " + student;
	}
	log(log + "\n\n\n");
    }

}
