package com.turnitin.fizzbuzzlambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FizzBuzzHandler implements RequestHandler<Object, Object> {
    private static final Gson GSON = new Gson();

    @Override
    public Object handleRequest(Object input, Context context) {
        try {
            JsonObject inputJson = GSON.fromJson(GSON.toJson(input), JsonObject.class);

            if (!inputJson.has("number")) {
                throw new IllegalArgumentException("Input must contain 'number' field");
            }

            int number = inputJson.get("number").getAsInt();
            String result = calculateFizzBuzz(number);

            JsonObject response = new JsonObject();
            response.addProperty("result", result);
            return response;
        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            throw new RuntimeException("Error processing request", e);
        }
    }

    private String calculateFizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }
}
