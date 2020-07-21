package com.in28minutes.jpa.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.ReviewRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	courseRepo.playground();
	studentRepo.playground();
	reviewRepo.playground();

	// getReviews will throw an exception because they are LAZY loaded and this
	// method is outside a @Transactional
	// logger.info("\n\n\nDemoApplication: Course 10003 Reviews: {}\n\n\n",
	// courseRepo.findById(10003L).getReviews());

//	Course c = new Course("Science");
//	courseRepo.save(c);
//
//	logger.info("\nCourse Details: {}", courseRepo.findById(c.getId()));

    }

}
