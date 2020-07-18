package com.in28minutes.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
	assertEquals(1, 1);
	assertNotEquals(1, 2);

	assertTrue(true);
	assertFalse(!true);

	assertNull(null);
	assertNotNull("" != null);

	assertArrayEquals(new int[] { 1 }, new int[] { 1 });
    }
}
