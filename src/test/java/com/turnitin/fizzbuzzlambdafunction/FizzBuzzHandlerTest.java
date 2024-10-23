package com.turnitin.fizzbuzzlambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzHandlerTest {

    private FizzBuzzHandler handler;
    private static final Gson GSON = new Gson();

    @Mock
    private Context context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handler = new FizzBuzzHandler();
    }

    @Test
    void testFizzBuzzForMultipleOfThreeAndFive() {
        JsonObject input = new JsonObject();
        input.addProperty("number", 15);

        Object result = handler.handleRequest(input, context);
        JsonObject response = GSON.fromJson(GSON.toJson(result), JsonObject.class);

        assertEquals("FizzBuzz", response.get("result").getAsString());
    }

    @Test
    void testFizzForMultipleOfThree() {
        JsonObject input = new JsonObject();
        input.addProperty("number", 3);

        Object result = handler.handleRequest(input, context);
        JsonObject response = GSON.fromJson(GSON.toJson(result), JsonObject.class);

        assertEquals("Fizz", response.get("result").getAsString());
    }

    @Test
    void testBuzzForMultipleOfFive() {
        JsonObject input = new JsonObject();
        input.addProperty("number", 5);

        Object result = handler.handleRequest(input, context);
        JsonObject response = GSON.fromJson(GSON.toJson(result), JsonObject.class);

        assertEquals("Buzz", response.get("result").getAsString());
    }

    @Test
    void testNumberForNonMultiples() {
        JsonObject input = new JsonObject();
        input.addProperty("number", 7);

        Object result = handler.handleRequest(input, context);
        JsonObject response = GSON.fromJson(GSON.toJson(result), JsonObject.class);

        assertEquals("7", response.get("result").getAsString());
    }

    @Test
    void testInvalidInput() {
        JsonObject input = new JsonObject();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            handler.handleRequest(input, context);
        });

        assertTrue(exception.getMessage().contains("Error processing request"));
    }
}
