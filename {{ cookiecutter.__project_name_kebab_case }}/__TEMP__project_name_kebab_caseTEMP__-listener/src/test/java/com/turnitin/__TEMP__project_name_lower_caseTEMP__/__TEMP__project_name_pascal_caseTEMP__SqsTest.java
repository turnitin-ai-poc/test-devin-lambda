package com.turnitin.__TEMP__project_name_lower_caseTEMP__;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.tests.EventLoader;
import com.turnitin.commons.TurnitinContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.turnitin.__TEMP__project_name_lower_caseTEMP__.__TEMP__project_name_pascal_caseTEMP__Sqs.EXAMPLE_ENV_VARIABLE;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class __TEMP__project_name_pascal_caseTEMP__SqsTest {

    @Mock
    Context lambdaContext;

    private TurnitinContext ctx;

    @Before
    public void setUp() {
        ctx = TurnitinContext.builder()
                .addVariable(EXAMPLE_ENV_VARIABLE, "env_key")
                .build();
    }

    @Test
    public void handleBadRequest() {
        __TEMP__project_name_pascal_caseTEMP__Sqs lambda = new __TEMP__project_name_pascal_caseTEMP__Sqs(ctx);
        SQSEvent badEvent = ctx.deserialize("{}", SQSEvent.class);
        assertThrows(NullPointerException.class, () -> {
            lambda.handleRequest(badEvent, lambdaContext);
        });
    }

    @Test
    public void handleRequest() {
        __TEMP__project_name_pascal_caseTEMP__Sqs lambda = new __TEMP__project_name_pascal_caseTEMP__Sqs(ctx);
        SQSEvent sqsEvent = EventLoader.loadEvent("sqs.json", SQSEvent.class);
        lambda.handleRequest(sqsEvent, lambdaContext);
    }

    @Ignore
    @Test
    public void handleS3Request() {

    }
}
