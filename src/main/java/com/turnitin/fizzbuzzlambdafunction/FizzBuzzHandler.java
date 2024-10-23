package com.turnitin.fizzbuzzlambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AWS Lambda handler for FizzBuzz implementation.
 * Takes an integer input and returns the corresponding FizzBuzz result.
 */
public class FizzBuzzHandler implements RequestHandler<FizzBuzzHandler.Request, FizzBuzzHandler.Response> {

    /**
     * Handles the FizzBuzz calculation for a given input number.
     *
     * @param input   The request object containing the input number
     * @param context The Lambda execution context
     * @return Response object containing the FizzBuzz result
     */
    @Override
    public Response handleRequest(Request input, Context context) {
        if (input == null || input.getNumber() == null) {
            return new Response("Error: Input number is required");
        }

        int number = input.getNumber();
        String result = calculateFizzBuzz(number);

        context.getLogger().log("Processed FizzBuzz for input: " + number + ", result: " + result);
        return new Response(result);
    }

    /**
     * Calculates the FizzBuzz result for a given number.
     *
     * @param number The input number
     * @return The FizzBuzz result string
     */
    private String calculateFizzBuzz(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        }
        if (number % 3 == 0) {
            return "Fizz";
        }
        if (number % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    /**
     * Request class for FizzBuzz Lambda input.
     */
    public static class Request {
        private final Integer number;

        @JsonCreator
        public Request(@JsonProperty("number") Integer number) {
            this.number = number;
        }

        public Integer getNumber() {
            return number;
        }
    }

    /**
     * Response class for FizzBuzz Lambda output.
     */
    public static class Response {
        private final String result;

        public Response(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }
}
