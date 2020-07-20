package com.in28minutes.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Passport;

@Repository
public class PassportRepository {

    @Autowired
    private EntityManager em;

    public Passport findById(Long id) {
	return em.find(Passport.class, id);
    }
}
