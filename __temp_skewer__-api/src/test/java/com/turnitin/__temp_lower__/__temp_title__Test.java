package com.turnitin.__temp_lower__;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.tests.EventLoader;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class __temp_title__Test {

	@Test
	void testWithNoInput() {
		final APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
		final Context context = new TestContext();
		final _temp_title__Get lambda = new _temp_title__Get();
		final APIGatewayProxyResponseEvent response = lambda.handleRequest(event, context);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	void testWithInput() {
		final APIGatewayProxyRequestEvent event = EventLoader.loadEvent("APIGatewayRequestEvent-hello.json",
				APIGatewayProxyRequestEvent.class);
		final Context context = new TestContext();
		final _temp_title__Get lambda = new _temp_title__Get();
		final APIGatewayProxyResponseEvent response = lambda.handleRequest(event, context);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}
}