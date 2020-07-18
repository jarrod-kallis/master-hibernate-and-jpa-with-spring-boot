package com.in28minutes.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {

    @BeforeClass
    public static void beforeClass() {
	System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() {
	System.out.println("After Class");
    }

    @Before
    public void before() {
	System.out.println("Before");
    }

    @After
    public void after() {
	System.out.println("After");
    }

    @Test
    public void sumWith2Numbers() {
	System.out.println("sumWith2Numbers");
	int result = new MyMath().sum(new int[] { 3, 2 });

	assertEquals(result, 5);
    }

    @Test
    public void sumWith1Number() {
	System.out.println("sumWith1Number");
	int result = new MyMath().sum(new int[] { 3 });

	assertEquals(result, 3);
    }

}
