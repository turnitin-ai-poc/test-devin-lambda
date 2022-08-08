package com.turnitin.__TEMP__project_name_lower_caseTEMP__;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.tests.EventLoader;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class __TEMP__project_name_pascal_caseTEMP__GetTest {

    @Test
    void testWithNoInput() {
        final APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
        final Context context = new TestContext();
        final __TEMP__project_name_pascal_caseTEMP__Get lambda = new __TEMP__project_name_pascal_caseTEMP__Get();
        final APIGatewayProxyResponseEvent response = lambda.handleRequest(event, context);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    @Test
    void testWithInput() {
        final APIGatewayProxyRequestEvent event = EventLoader.loadEvent("APIGatewayRequestEvent-hello.json",
                                                                        APIGatewayProxyRequestEvent.class);
        final Context context = new TestContext();
        final __TEMP__project_name_pascal_caseTEMP__Get lambda = new __TEMP__project_name_pascal_caseTEMP__Get();
        final APIGatewayProxyResponseEvent response = lambda.handleRequest(event, context);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}