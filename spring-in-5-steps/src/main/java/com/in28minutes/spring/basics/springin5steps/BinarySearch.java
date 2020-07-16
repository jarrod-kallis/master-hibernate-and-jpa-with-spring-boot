package com.in28minutes.spring.basics.springin5steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tells Spring Framework that this class is a bean, and it needs to manage it
@Component
public class BinarySearch {

	// Tells Spring that this is a dependency of BinarySearch
	@Autowired
	private SortAlgorithm sortAlgo;
	// If multiple classes implement an autowired interface, but it's not
	// specified which one to use:
	// Error: Consider marking one of the beans as @Primary, updating the
	// consumer to accept multiple beans, or using @Qualifier to identify the
	// bean that should be consumed

	// Autowiring by type from bean name 'binarySearch' via constructor to bean
	// named 'quickSortAlgorithm'
	// public BinarySearch(SortAlgorithm sortAlgo) {
	// super();
	// this.sortAlgo = sortAlgo;
	// }

	public int binarySearch(int[] numbers, int numberToFind) {
		sortAlgo.sort(numbers);

		return 3;
	}
}
