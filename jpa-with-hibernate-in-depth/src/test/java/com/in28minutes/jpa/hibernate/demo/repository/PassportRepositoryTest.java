package com.in28minutes.jpa.hibernate.demo.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@SpringBootTest
class PassportRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PassportRepository passportRepo;

    @Test
    // Don't need @Transactional here, because when querying the "child" passport
    // table, Hibernate automatically queries the "parent" student table as well
    void getStudentUsingPassport() {
	// If LAZY on the Passport side: This line results in 2 SQLs firing: 1 to get
	// the passport, and another to get the student details
	// If EAGER on the Passport side: This line results in 1 SQL to get both
	// passport and student
	Passport p = passportRepo.findById(40002L);

	Student s = p.getStudent();

	logger.info("\nPassport 40002: {}", p);
	logger.info("\nStudent for Passport 40002: {}", s);
    }
}