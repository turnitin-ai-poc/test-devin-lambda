# DO NOT CLONE DIRECTLY

If you want to make changes to this template, then yes, clone this, make changes, and create a pull request, but please be sure to follow the [Contributing](./CONTRIBUTING.md) instructions.

However, if you're trying to create a new lambda, this repo should be cloned using the `project_generator.py` script found in
[GitHub Enterprise](https://ghe.iparadigms.com/AppOps/project-generator.git) and [BitBucket](https://mromanini@bitbucket.org/examsoft/project-generator.git). Once that's done, the below applies.


# _Temp_ QuickStart

Have working AWS credentials for DEV001, either `default` or env var `AWS_PROFILE` points to them

1. `mvn clean package` to build the Maven multi-module project
2. `sam deploy` to deploy to DEV001
3. `sam local start-api` will run the code locally

To "un-deploy" a stack from the AWS dev environment, run `aws cloudformation delete-stack --stack-name <stackname>`

Note: The local /crud endpoint depends on `sam deploy` being done at least once
to create the required DynamoDB table in DEV001.

# _Temp_ Details

This project contains source code and supporting files for a serverless application that you can deploy with the AWS SAM
CLI. It includes the following files and folders:

- _temp_-api/src/main - Code for two Lambda functions, including log configuration.
- _temp_-core/ - Shared code (DB objects) and resources (log config).
- pom.xml - Defines this multi-module Maven project with the two modules above.
- samconfig.xml - SAM config for deployments.
- template.yaml - SAM template that defines resources for these functions.
- bitbucket-pipelines.yml - Bitbucket pipelines uses this, so it's for DevOps.

This example sets up two ApiGateway Lambdas:

http://127.0.0.1:3000/hello [GET] is a simple Hello, World lambda

http://127.0.0.1:3000/crud/{dbkey}/{value} [POST]
and
http://127.0.0.1:3000/crud/{dbkey} [GET]
are for writing to and reading from DynamoDB 

This code has three entry points configured:

1. `_temp_-api/src/main/java/com/turnitin/_temp_` This entry point is for handling regular HTTP requests.
2. `_temp_-api/src/main/java/com/turnitin/_temp_DynamoGet` This entry point is an example of handling HTTP requests which reads data from DynamoDB.
3. `_temp_-api/src/main/java/com/turnitin/_temp_DynamoPost` This entry point is an example of handling HTTP requests which writes data from DynamoDB.

Based on your use case you should delete the one you don't need/want.  To do so, remove the unwanted entry point from the code and from the `template.yaml`.  

**Note:** The Dynamo entry point uses the schema which is defined in `template.yaml` as ` _Temp_Table01`. Should your use case not need
DynamoDB remove the two entry poins from the code, as well as the package `_temp_-core/src/main/java/com/turnitin/dao`. The remove the entrypoints and the table from the `template.yaml` 
as well as the dependency from the `pom.xml` in order to reduce your package size. 

# Dependencies

The TurnitinContext gives you an easy way to access various services (AWS as well as internal) simply by declaring the need when instanciating the TurnitinContext with the
TurnitinContextCustomizer. However, in order to keep the final package as small possible the dependencies are commented out in the root `pom.xml`.  Therefore, when you
add a dependency make sure you also uncomment it in the `pom.xml`.  Failure to do so will of course result in the runtime exception `ClassNotFoundException`.  For the sake
of load times do not just uncomment all the dependencies or this will bloat your package and will slow down performance of your lambda and cost more to run.