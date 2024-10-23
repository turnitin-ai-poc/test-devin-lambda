//TEMP if cookiecutter.include_http is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__.api;

import com.turnitin.__TEMP__project_name_lower_caseTEMP__.api.dto.Hello;
import com.turnitin.__TEMP__project_name_lower_caseTEMP__.core.service.__TEMP__project_name_pascal_caseTEMP__Service;
import com.turnitin.commons.TurnitinContext;
import com.turnitin.commons.http.HttpMethod;
import com.turnitin.commons.lambda.ApiGatewayLambda;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class __TEMP__project_name_pascal_caseTEMP__Get extends ApiGatewayLambda<Hello> {

    private __TEMP__project_name_pascal_caseTEMP__Service __TEMP__project_name_camel_caseTEMP__Service;

    // This constructor is for regular flow
    public __TEMP__project_name_pascal_caseTEMP__Get() {
        this.ctx = TurnitinContext.builder().build();
        this.__TEMP__project_name_camel_caseTEMP__Service = new __TEMP__project_name_pascal_caseTEMP__Service(ctx);
    }

    // This Constructor is use in tests if you want to mock the context or parts there of.
    public __TEMP__project_name_pascal_caseTEMP__Get(TurnitinContext ctx) {
        this.ctx = ctx;
        this.__TEMP__project_name_camel_caseTEMP__Service = new __TEMP__project_name_pascal_caseTEMP__Service(ctx);
    }

    @Override
    protected Hello handleMethod() throws Exception {
        Hello hello = new Hello();
        String who = ctx.getKeyFromPathParams(input, "who");
        hello.setResponse(__TEMP__project_name_camel_caseTEMP__Service.doHello(who));
        return hello;
    }

    @Override
    protected List<String> getSupportedHttpMethods() {
        return Arrays.asList(HttpMethod.GET.name());
    }
}
//TEMP endif