# __TEMP__project_name_pascal_caseTEMP__ QuickStart

Have working AWS credentials for DEV001, either `default` or env var `AWS_PROFILE` points to them

1. `mvn clean package` to build the Maven multi-module project
2. `sam local start-api` will run the code locally
3. `sam deploy` to deploy to DEV001

To "un-deploy" a stack from the AWS dev environment, run `aws cloudformation delete-stack --stack-name <stackname>`

#TEMP if cookiecutter.include_dynamodb is selected_option:
Note: The local /crud endpoint depends on `sam deploy` being done at least once
to create the required DynamoDB table in DEV001.
#TEMP endif


# __TEMP__project_name_pascal_caseTEMP__ Details

This project contains source code and supporting files for a serverless application that you can deploy with the AWS SAM
CLI. It includes the following files and folders:

#TEMP if cookiecutter.include_dynamodb is selected_option or cookiecutter.include_http is selected_option:
- __TEMP__project_name_kebab_caseTEMP__-api/src/main - Code for three Lambda functions, including log configuration.
#TEMP endif
#TEMP if cookiecutter.include_sqs_listener is selected_option or cookiecutter.include_s3_listener:
- __TEMP__project_name_kebab_caseTEMP__-listener/src/main - Code for the Lambda function.
#TEMP endif
#TEMP if cookiecutter.include_http is selected_option or cookiecutter.include_dynamodb is selected_option or cookiecutter.include_sqs_listener is selected_option:
- __TEMP__project_name_kebab_caseTEMP__-core/ - Shared code (DB objects) and resources (log config).
#TEMP endif
- events/sqs.json - An example SQSEvent for local testing.
- pom.xml - Defines this multi-module Maven project with the three modules above.
- samconfig.xml - SAM config for deployments.
- template.yaml - SAM template that defines resources for these functions.
- bitbucket-pipelines.yml - Bitbucket pipelines uses this, so it's for DevOps.


#TEMP if cookiecutter.include_dynamodb is selected_option or cookiecutter.include_http is selected_option:
This example sets up the following ApiGateway Lambdas:

#TEMP if cookiecutter.include_http is selected_option:
http://127.0.0.1:3000/hello/{name} [GET] is a simple Hello, World lambda
#TEMP endif

#TEMP if cookiecutter.include_dynamodb is selected_option:
http://127.0.0.1:3000/dynamo/{dbkey}/{value} [POST]
and
http://127.0.0.1:3000/dynamo/{dbkey} [GET]
are for writing to and reading from DynamoDB
#TEMP endif
#TEMP endif

This code has the following entry points configured:

#TEMP if cookiecutter.include_http is selected_option:
1. `__TEMP__project_name_kebab_caseTEMP__-api/src/main/java/com/turnitin/__TEMP__project_name_lower_caseTEMP__/__TEMP__project_name_pascal_caseTEMP__Get` This entry point is for handling regular HTTP requests.
#TEMP endif
#TEMP if cookiecutter.include_dynamodb is selected_option:
1. `__TEMP__project_name_kebab_caseTEMP__-api/src/main/java/com/turnitin/__TEMP__project_name_lower_caseTEMP__/__TEMP__project_name_pascal_caseTEMP__DynamoGet` This entry point is an example of handling HTTP requests which reads data from DynamoDB.
1. `__TEMP__project_name_kebab_caseTEMP__-api/src/main/java/com/turnitin/__TEMP__project_name_lower_caseTEMP__/__TEMP__project_name_pascal_caseTEMP__DynamoPost` This entry point is an example of handling HTTP requests which writes data from DynamoDB.
#TEMP endif
#TEMP if cookiecutter.include_sqs_listener is selected_option:
1. `_-temp_skewer-_-listener/src/main/java/com/turnitin/_-temp_lower-_/_-temp_title-_Sqs` This listener is for handling straight SQS messages some other application may have published.
#TEMP endif
#TEMP if cookiecutter.include_s3_listener is selected_option:
1. `_-temp_skewer-_-listener/src/main/java/com/turnitin/_-temp_lower-_/_-temp_title-_S3` This listener is for handling S3 events from an SQS queue.
#TEMP endif


# Dependencies

The TurnitinContext gives you an easy way to access various services (AWS as well as internal) simply by declaring the need when instanciating the TurnitinContext with the
TurnitinContextCustomizer. However, in order to keep the final package as small possible the dependencies are commented out in the root `pom.xml`.  Therefore, when you
add a dependency make sure you also uncomment it in the `pom.xml`.  Failure to do so will of course result in the runtime exception `ClassNotFoundException`.  For the sake
of load times do not just uncomment all the dependencies or this will bloat your package and will slow down performance of your lambda and cost more to run.

