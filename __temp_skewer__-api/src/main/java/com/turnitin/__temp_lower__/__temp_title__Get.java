package com.turnitin.__temp_lower__;

import com.turnitin.__temp_lower__.model.Hello;
import com.turnitin.__temp_lower__.service.__temp_title__Service;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __temp_title__Get extends ApiGatewayLambda<Hello> {

    private __temp_title__Service __temp_lower__service;

    // This constructor is for regular flow
    public __temp_title__Get() {
        this.ctx = TurnitinContext.builder().build();
        this.__temp_lower__service = new __temp_title__Service(ctx);
    }

    // This Constructor is use in tests if you want to mock the context or parts there of.
    public __temp_title__Get(TurnitinContext ctx) {
        this.ctx = ctx;
        this.__temp_lower__service = new __temp_title__Service(ctx);
    }

    @Override
    protected Hello handleMethod() throws Exception {
        String who = ctx.getKeyFromPathParams(input, "who");
        return __temp_lower__service.doHello(who);
    }

    @Override
    protected List<String> getSupportedHttpMethods() {
        return Arrays.asList(HttpMethod.GET);
    }
}
