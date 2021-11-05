package com.turnitin.__temp_lower__;

import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.TurnitinContextCustomizer;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class _temp_title__Get extends ApiGatewayLambda<Map<String,String>> {

    // This constructor is for regular flow
    public _temp_title__Get() {
        this.ctx = TurnitinContextCustomizer.customizeInstance()
                .getInstance();
    }

    // This Constructor is use in tests if you want to mock the context or parts there of.
    public _temp_title__Get(TurnitinContext ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Map<String, String> handleMethod() throws Exception {
        return Collections.singletonMap("response", "Hello, World!");
    }

    @Override
    protected List<String> getSupportedHttpMethods() {
        return Arrays.asList(HttpMethod.GET);
    }
}
