package com.turnitin.fizzbuzzlambdafunction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for FizzBuzz Lambda handler.
 */
public class FizzBuzzHandlerTest {
    private FizzBuzzHandler handler;
    private TestContext context;

    @Before
    public void setUp() {
        handler = new FizzBuzzHandler();
        context = new TestContext();
    }

    @Test
    public void testHandleRequestWithNull() {
        FizzBuzzHandler.Response response = handler.handleRequest(null, context);
        assertEquals("Error: Input number is required", response.getResult());
    }

    @Test
    public void testHandleRequestWithNullNumber() {
        FizzBuzzHandler.Request request = new FizzBuzzHandler.Request(null);
        FizzBuzzHandler.Response response = handler.handleRequest(request, context);
        assertEquals("Error: Input number is required", response.getResult());
    }

    @Test
    public void testFizzBuzzForNumberDivisibleByThree() {
        FizzBuzzHandler.Request request = new FizzBuzzHandler.Request(6);
        FizzBuzzHandler.Response response = handler.handleRequest(request, context);
        assertEquals("Fizz", response.getResult());
    }

    @Test
    public void testFizzBuzzForNumberDivisibleByFive() {
        FizzBuzzHandler.Request request = new FizzBuzzHandler.Request(10);
        FizzBuzzHandler.Response response = handler.handleRequest(request, context);
        assertEquals("Buzz", response.getResult());
    }

    @Test
    public void testFizzBuzzForNumberDivisibleByBothThreeAndFive() {
        FizzBuzzHandler.Request request = new FizzBuzzHandler.Request(15);
        FizzBuzzHandler.Response response = handler.handleRequest(request, context);
        assertEquals("FizzBuzz", response.getResult());
    }

    @Test
    public void testFizzBuzzForNumberNotDivisibleByThreeOrFive() {
        FizzBuzzHandler.Request request = new FizzBuzzHandler.Request(7);
        FizzBuzzHandler.Response response = handler.handleRequest(request, context);
        assertEquals("7", response.getResult());
    }
}
