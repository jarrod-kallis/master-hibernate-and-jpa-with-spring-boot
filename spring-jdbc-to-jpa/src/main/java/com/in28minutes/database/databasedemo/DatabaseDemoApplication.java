package com.in28minutes.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.databasedemo.jdbc.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonDao;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonDao dao;

    public static void main(String[] args) {
	SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	// Select
	logger.info("\nAll People => {}", dao.findAll());
	logger.info("\nPerson => {}", dao.findById(10001));
	logger.info("\nPerson Like => {}", dao.findByLike("name", "J"));

	// Delete
	logger.info("\nDelete 10002 => No of Rows Deleted: {}", dao.deleteById(10002));
	logger.info("\nAll People => {}", dao.findAll());

	// Insert
	logger.info("\nInsert 10005 => No of Rows Inserted: {}",
		dao.insert(new Person(10005, "Ryan", "Muizenberg", new Date())));
	logger.info("\nAll People => {}", dao.findAll());

	// Update
	logger.info("\nUpdate 10005 => No of Rows Updated: {}",
		dao.update(new Person(10005, "Ryan Johnson", "Houtbay", new Date())));
	logger.info("\nAll People => {}", dao.findAll());
    }

}
