package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@SpringBootTest
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    @SuppressWarnings("unchecked")
    void findAll() {
	List<Course> courseList = em.createNativeQuery("select * from course", Course.class).getResultList();

	logger.info("\nselect * from course\nCourse List: {}", courseList);
    }

    @Test
    @SuppressWarnings("unchecked")
    void findWithWhere() {
	List<Course> courseList = em
		.createNativeQuery("select * from course where upper(name) like '%H%'", Course.class).getResultList();

	logger.info("\nselect * from course where upper(name) like '%H%'\nCourse List: {}", courseList);
    }

    @Test
    @SuppressWarnings("unchecked")
    void findWithPositionalParameter() {
	Query query = em.createNativeQuery("select * from course where upper(name) like ?", Course.class);
	query.setParameter(1, "%A%");

	List<Course> courseList = query.getResultList();

	logger.info("\nselect * from course where upper(name) like '%A%'\nCourse List: {}", courseList);
    }

    @Test
    @SuppressWarnings("unchecked")
    void findWithNamedParameter() {
	Query query = em.createNativeQuery("select * from course where upper(name) like :name", Course.class);
	query.setParameter("name", "%S%");

	List<Course> courseList = query.getResultList();

	logger.info("\nselect * from course where upper(name) like '%S%'\nCourse List: {}", courseList);
    }

    @Test
    @DirtiesContext
    @Transactional // This is necessary because the 'update' isn't going through the repo, which
		   // has the annotation
    public void updateAllRows() {
	int rowsUpdated = em.createNativeQuery("update course set last_updated_date_time = sysdate()", Course.class)
		.executeUpdate();

	logger.info("No. of rows updated: {}", rowsUpdated);
    }
}
