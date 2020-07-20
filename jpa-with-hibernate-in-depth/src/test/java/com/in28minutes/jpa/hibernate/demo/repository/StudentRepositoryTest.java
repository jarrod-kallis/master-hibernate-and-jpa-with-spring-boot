package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepo;

    // To run this test, and see only 1 SQL firing, make sure that the Passport in
    // Student is of EAGER FetchType
    @Disabled
    @Test
    void findStudentWithPassportRelationshipIsEagerFetch() {
	// Because the student/passport relationship is OneToOne, the passport details
	// are fetched automatically whenever the student is retrieved. This is called
	// EAGER fetching.

	// This will result in an SQL that retrieves both the Student & Passport
	// details, because Passport is marked as EAGER
	Student s = studentRepo.findById(20001L);

	// No Session error will be thrown here if Passport is set to LAZY, because this
	// method is not annotated with @Transactional, so the transaction ends
	// immediately after studentRepo.findById has completed
	assertEquals("A1234567", s.getPassport().getNumber());
    }

    // To run this test, and see the 2 SQLs firing, make sure that the Passport in
    // Student is of LAZY FetchType
    @Test
    @Transactional // This puts this method in 1 transaction (which allows the passport to be
		   // retrieved using an extra SQL)
    void findStudentWithPassportRelationshipIsLazyFetch() {
	// This will result in an SQL that just retrieves the Student info
	Student s = studentRepo.findById(20001L);

	// This will result in another SQL firing to retrieve the Passport info
	Passport p = s.getPassport();

	assertEquals("A1234567", p.getNumber());
    }
}