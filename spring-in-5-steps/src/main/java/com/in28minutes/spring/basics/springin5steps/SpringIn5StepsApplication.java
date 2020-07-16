package com.in28minutes.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// Spring Boot will automatically scan this package, and sub packages, for beans
@SpringBootApplication
public class SpringIn5StepsApplication {

	// What are the beans? @Component
	// What are the dependencies of a bean? @Autowired
	// Where to search for beans? Component scan

	public static void main(String[] args) {
		// Spring's Application Context will manage all the beans
		ApplicationContext applicationContext = SpringApplication.run(SpringIn5StepsApplication.class, args);

		// BinarySearch search = new BinarySearch(new BubbleSortAlgorithm());
		BinarySearch search = applicationContext.getBean(BinarySearch.class);

		int idx = search.binarySearch(new int[] { 12, 4, 1 }, 3);
		System.out.println(idx);
	}

}
