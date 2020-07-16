package com.in28minutes.spring.basics.springin5steps;

import org.springframework.stereotype.Component;

@Component
public class BubbleSortAlgorithm implements SortAlgorithm {

	public int[] sort(int[] numbers) {
		System.out.println("Bubble Sort Algorithm");
		return numbers;
	}
}
