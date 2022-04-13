package com.example.assignment;

import static org.junit.Assert.assertNull;

import com.example.assignment.model.Data;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void NullTest() {

        Data data = null;
        assertNull("Verify that object is null", data);
    }
}