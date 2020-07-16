package com.in28minutes.spring.basics.springin5steps;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// Indicates that if an interface is autowired, but the concrete class isn't specified, then use this one by default
@Primary
public class QuickSortAlgorithm implements SortAlgorithm {

	public int[] sort(int[] numbers) {
		System.out.println("Quick Sort Algorithm");
		return numbers;
	}
}
