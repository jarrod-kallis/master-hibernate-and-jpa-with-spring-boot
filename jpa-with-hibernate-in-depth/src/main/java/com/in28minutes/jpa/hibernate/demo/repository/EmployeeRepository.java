package com.in28minutes.jpa.hibernate.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Employee;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public Employee save(Employee e) {
	if (e.getId() == null) {
	    em.persist(e);
	} else {
	    e = em.merge(e);
	}

	return e;
    }

    public List<Employee> findAll() {
	return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    public List<Employee> findAllPartTime() {
	return em.createQuery("select e from PartTimeEmployee e", Employee.class).getResultList();
    }

    public List<Employee> findAllFullTime() {
	return em.createQuery("select e from FullTimeEmployee e", Employee.class).getResultList();
    }

    public void playground() {
	save(new PartTimeEmployee("Jarrod", BigDecimal.valueOf(50)));
	save(new FullTimeEmployee("Tessa", BigDecimal.valueOf(10000)));

	logger.info("\n\n\nAll Employees: {}\n\n\n", findAll());
	logger.info("\n\n\nAll Part Time Employees: {}\n\n\n", findAllPartTime());
	logger.info("\n\n\nAll Full Time Employees: {}\n\n\n", findAllFullTime());
    }
}
