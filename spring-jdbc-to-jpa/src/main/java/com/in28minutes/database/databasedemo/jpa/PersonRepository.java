package com.in28minutes.database.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonRepository {
    public static final String FIND_ALL_PERSONS = "FIND_ALL_PERSONS";

    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findAll() {
	TypedQuery<Person> query = entityManager.createNamedQuery(FIND_ALL_PERSONS, Person.class);
	return query.getResultList();
    }

    public Person findById(int id) {
	return entityManager.find(Person.class, id);
    }

    // The merge method will insert or update based on whether or not the primary
    // key is set, and in the DB
    public Person upsert(Person p) {
	return entityManager.merge(p);
    }

    public void deleteById(int id) {
	Person p = findById(id);
	entityManager.remove(p);
    }
}
