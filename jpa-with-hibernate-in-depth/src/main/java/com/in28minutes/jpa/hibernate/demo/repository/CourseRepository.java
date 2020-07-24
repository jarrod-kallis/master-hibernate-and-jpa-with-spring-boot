package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class CourseRepository {
    public static final String SELECT_ALL_COURSES = "SELECT_ALL_COURSES";
    public static final String SELECT_ALL_H_COURSES = "SELECT_ALL_H_COURSES";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Autowired
    private StudentRepository studentRepo;

    public Course findById(Long id) {
	return em.find(Course.class, id);
    }

    public Course save(Course c) {
	if (c.getId() == null) {
	    // Insert
	    em.persist(c);
	} else {
	    // Update
	    c = em.merge(c);
	}

	return c;
    }

    public void deleteById(Long id) {
	em.remove(findById(id));
    }

    public void addReviews(Long courseId, Student s, List<Review> reviews) {
	Course c = findById(courseId);

	for (Review review : reviews) {
	    // We need to add the review manually to the course so that we have the link
	    // setup immediately, otherwise we will need to em.flush() at the end of this
	    // method in order to pick up the reviews for the course through
	    // course.getReviews()
	    c.addReview(review);
	    s.addReview(review);

	    review.setCourse(c);
	    review.setStudent(s);

	    em.persist(review);
	}

	// Not needed if we manually add the reviews to the course
	// em.flush();
    }

    public void playWithUpdateTwiceInOneTransaction() {
	Course c = new Course("Afrikaans");
	// Save to the DB
	em.persist(c);

	// EM will keep track of this entity
	c.setName("Afrikaans - Updated");
	// This results in only 1 update SQL (Updates to the latest value)
	c.setName("Afrikaans - Updated Again");
    }

    public void playWithUpdateAndRemoveInOneTransaction() {
	Course c = new Course("Accounting");
	// Save to the DB
	em.persist(c);

	// EM will keep track of this entity
	c.setName("Accounting - Updated");
	// No update is performed on Accounting now, and only the delete
	em.remove(c);
    }

    public void playWithMergeToCreateACourse() {
	Course c = new Course("Home Economics");
	// Results in an id being retrieved immediately
	// em.persist(c);
	// Can also be used to save a new Course, but the id will not be generated
	// immediately
	em.merge(c);
	logger.info("Home Economics Course ID: {}", c.getId()); // 'null' if em.merge is used
    }

    public void playWithDetachAndClear() {
	Course c = new Course("Woodwork");
	em.persist(c);
	em.flush();

	// Detach will stop the EM from tracking changes to it
	// Can't detach a new Entity until it's been flushed to the DB
	em.detach(c);
	// em.clear(); // Will detach all entities tracked by EM

	c.setName("Woodwork - Updated");
	em.merge(c); // Will re-attach the Woodwork entity

	c = em.find(Course.class, c.getId());
	c.setName("Woodwork - Updated Again");
	em.detach(c); // Detaching an existing entity before it's updated to the DB is fine
    }

    public void playWithRefresh() {
	Course c = new Course("Metalwork");
	em.persist(c);
	Course c2 = new Course("Phys Ed");
	em.persist(c2);

	em.flush();

	c.setName("Metalwork - Updated");
	c2.setName("Phys Ed - Updated");

	// Causes a select statement to get the Metalwork row as it is in the DB
	// Can't call refresh on a new entity that isn't in the DB yet: No row with the
	// given identifier exists
	em.refresh(c);
	// Only does 1 update statement, because Metalwork hasn't changed
	em.flush();

    }

    public void playWithCreatedAndUpdatedDateTime() {
	Course c = new Course("C++");
	c.setName("C++ - Updated");

	Course c2 = new Course("Java");

	em.persist(c);
	em.persist(c2);

	em.flush(); // Will fix the Hibernate created_date bug
	c2.setName("Java - Updated"); // Results in the created_date being null - bug in Hibernate

	Course c3 = new Course("Business Economics");
	em.persist(c3);
	c3.setName("Business Economics - Updated");
    }

    public void playground() {
	// Transaction Start
	playWithUpdateTwiceInOneTransaction();

	playWithUpdateAndRemoveInOneTransaction();

	playWithMergeToCreateACourse();

	playWithDetachAndClear();

	playWithRefresh();

	playWithCreatedAndUpdatedDateTime();

	List<Review> reviews = new ArrayList<Review>();
	reviews.add(new Review("1", "That was toilet"));
	reviews.add(new Review("2.5", "I've seen better"));

	Student s = studentRepo.findById(20002L);

	addReviews(10003L, s, reviews);
	logger.info("\n\n\nCourseRepo: Course 10003 Reviews: {}\n\n\n", findById(10003L).getReviews());

	// EM will now flush all changes to the DB, because the transaction has ended
	// Transaction End
    }
}
