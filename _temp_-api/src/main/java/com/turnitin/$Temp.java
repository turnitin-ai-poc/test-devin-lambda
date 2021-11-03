package com.turnitin;

import com.turnitin.commons.TurnitinContextCustomizer;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.ws.rs.HttpMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class $Temp extends ApiGatewayLambda<Map<String,String>> {

    // this one is for regular flow
    public $Temp() {
        this.ctx = TurnitinContextCustomizer.customizeInstance()
                .getInstance();
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
