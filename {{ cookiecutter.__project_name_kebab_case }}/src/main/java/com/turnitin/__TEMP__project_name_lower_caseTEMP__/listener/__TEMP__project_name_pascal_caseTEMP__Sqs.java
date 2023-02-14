//TEMP if cookiecutter.include_sqs_listener is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__.listener;


import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.turnitin.__TEMP__project_name_lower_caseTEMP__.core.service.__TEMP__project_name_pascal_caseTEMP__Service;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.lambda.SqsLambda;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __TEMP__project_name_pascal_caseTEMP__Sqs extends SqsLambda {
    static final String EXAMPLE_ENV_VARIABLE = "env_key";

    private String example;
    private __TEMP__project_name_pascal_caseTEMP__Service __TEMP__project_name_lower_caseTEMP__service;

    // this one is for regular flow
    public __TEMP__project_name_pascal_caseTEMP__Sqs() {
        ctx = TurnitinContext.builder()
                .defaultDlqHandling()
                .addEnvironmentVariable(EXAMPLE_ENV_VARIABLE)
                .build();
        this.__TEMP__project_name_lower_caseTEMP__service = new __TEMP__project_name_pascal_caseTEMP__Service(ctx);
    }

    // this constructor is used by the tests so they can mock the context
    public __TEMP__project_name_pascal_caseTEMP__Sqs(TurnitinContext ctx) {
        this.ctx = ctx;
        this.__TEMP__project_name_lower_caseTEMP__service = new __TEMP__project_name_pascal_caseTEMP__Service(ctx);
    }

    protected void setup() {
        log.trace("parsed env variable : {}", ctx.getVariable(EXAMPLE_ENV_VARIABLE));
        example = ctx.getVariable(EXAMPLE_ENV_VARIABLE);
    }

    protected void handleMessage(final SQSEvent.SQSMessage sqsMessage) {
        log.trace("Got value {} from environment variable", example);
        __TEMP__project_name_lower_caseTEMP__service.doHello(sqsMessage.getBody());
    }
}
//TEMP endif
