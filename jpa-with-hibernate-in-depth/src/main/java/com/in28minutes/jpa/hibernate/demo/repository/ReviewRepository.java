package com.in28minutes.jpa.hibernate.demo.repository;

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
public class ReviewRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    StudentRepository studentRepo;

    public Review findById(Long id) {
	return em.find(Review.class, id);
    }

    public Review save(Review r) {
	if (r.getId() == null) {
	    em.persist(r);
	} else {
	    r = em.merge(r);
	}

	return r;
    }

    public Course getCourseForReview(Long reviewId) {
	Review r = findById(reviewId);
	logger.info("\n\n\nCourse Details for Review 50001: {}\n\n\n", r.getCourse());

	return r.getCourse();
    }

    public Student getStudentForReview(Long reviewId) {
	Review r = findById(reviewId);
	logger.info("\n\n\nStudent Details for Review 50001: {}\n\n\n", r.getStudent());

	return r.getStudent();
    }

    private void playWithInsertingAReviewAndLinkToANewCourse() {
	Course c = new Course("Rust");
	courseRepo.save(c);

	Student s = studentRepo.findById(20002L);

	Review r = new Review("3", "Average");
	r.setCourse(c);
	r.setStudent(s);
	save(r);
    }

    private void playWithInsertingMultipleReviewsForANewCourse() {
	Course c = courseRepo.findById(10003L);
	Student s = studentRepo.findById(20001L);

	Review r = new Review("4", "Fantastic!");
	r.setCourse(c);
	r.setStudent(s);
//	c.addReview(r);
//	courseRepo.save(c);
//	s.addReview(r);
	save(r);

	r = new Review("4.5", "Yeah Baby!");
	r.setCourse(c);
	r.setStudent(s);
//	c.addReview(r);
//	courseRepo.save(c);
//	s.addReview(r);
	save(r);

	// Have to flush to the DB before the c.getReviews() below will retrieve the
	// data
	em.flush();

	logger.info("\n\n\nCourse 10003 reviews: {}\n\n\n", c.getReviews());
    }

    public void playground() {
	playWithInsertingAReviewAndLinkToANewCourse();
	playWithInsertingMultipleReviewsForANewCourse();
	getCourseForReview(50001L);
	getStudentForReview(50001L);
    }
}
