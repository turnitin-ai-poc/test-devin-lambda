# FizzBuzz Lambda Function

A Java-based AWS Lambda function that implements the FizzBuzz algorithm.

## Overview

This Lambda function accepts a number as input and returns the corresponding FizzBuzz result based on the following rules:
- If the number is divisible by 3, return "Fizz"
- If the number is divisible by 5, return "Buzz"
- If the number is divisible by both 3 and 5, return "FizzBuzz"
- Otherwise, return the number as a string

## API Usage

### Request Format
```json
{
  "number": 15
}
```

### Response Format
```json
{
  "result": "FizzBuzz"
}
```

## Development Requirements

* Java 11 - [Install Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
* AWS SAM CLI - [Install the SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
* Maven - [Install Maven](https://maven.apache.org/install.html)
* Docker - [Install Docker community edition](https://hub.docker.com/search/?type=edition&offering=community)

## Project Structure

- `src/main/java/.../FizzBuzzHandler.java` - Main Lambda handler implementation
- `src/test/java/.../FizzBuzzHandlerTest.java` - Unit tests
- `events/fizzbuzz-event.json` - Sample event for testing
- `template.yaml` - SAM template defining AWS resources

## Local Development

1. Build the application:
```bash
sam build
```

2. Test locally:
```bash
sam local invoke FizzBuzzFunction -e events/fizzbuzz-event.json
```

3. Run unit tests:
```bash
mvn test
```

## Deployment

Deploy using AWS SAM:
```bash
sam deploy --guided
```

This will:
1. Create an API Gateway endpoint
2. Deploy the Lambda function
3. Set up necessary IAM roles and permissions

To remove the deployed stack:
```bash
aws cloudformation delete-stack --stack-name <stack-name>
```

Note: You need valid AWS credentials configured either as `default` or specified via the `AWS_PROFILE` environment variable.

## Testing the Deployed API

Once deployed, you can test the API endpoint using curl:

```bash
curl -X POST \
  https://your-api-endpoint/prod/fizzbuzz \
  -H 'Content-Type: application/json' \
  -d '{"number": 15}'
```

The response should be:
```json
{
  "result": "FizzBuzz"
}
```
