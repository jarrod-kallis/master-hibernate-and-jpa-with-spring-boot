package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
    public static final String SELECT_ALL_STUDENTS_AND_THEIR_ASSOCIATED_PASSPORTS = "SELECT_ALL_STUDENTS_AND_THEIR_ASSOCIATED_PASSPORTS";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    public List<Long> getCount() {
	return em.createQuery("select count(s.id) from Student s", Long.class).getResultList();
    }

    public Student findById(Long id) {
	return em.find(Student.class, id);
    }

    public Student save(Student c) {
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

    public void playWithCreatingAStudentWithAPassport() {
	Passport p = new Passport("Z1234567");

	Student s = new Student("Ryan Johnson");
	s.setPassport(p);

	em.persist(s);
	// You have to persist the Passport explicitly, but it doesn't matter at what
	// point - as long as it's within the same transaction
	em.persist(p);
    }

    @SuppressWarnings("rawtypes")
    public void playWithGettingStudentsWithAssociatedPassports() {
	List studentList = em.createNamedQuery(StudentRepository.SELECT_ALL_STUDENTS_AND_THEIR_ASSOCIATED_PASSPORTS)
		.getResultList();

	for (Object student : studentList) {
	    logger.info("{}", student);
	}
    }

    public void playground() {
	// Transaction Start
	playWithCreatingAStudentWithAPassport();
	playWithGettingStudentsWithAssociatedPassports();

	// EM will now flush all changes to the DB, because the transaction has ended
	// Transaction End
    }
}
