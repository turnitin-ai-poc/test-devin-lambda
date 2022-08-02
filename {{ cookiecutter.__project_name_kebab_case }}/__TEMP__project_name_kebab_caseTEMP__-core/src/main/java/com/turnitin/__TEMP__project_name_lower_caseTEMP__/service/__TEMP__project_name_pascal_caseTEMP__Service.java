package com.turnitin.__TEMP__project_name_lower_caseTEMP__.service;

import com.turnitin.__TEMP__project_name_lower_caseTEMP__.model.Hello;
import com.turnitin.commons.TurnitinContext;

// This is the kind of server in which the business logic might go
public class __TEMP__project_name_pascal_caseTEMP__Service {

    TurnitinContext ctx;

    public __TEMP__project_name_pascal_caseTEMP__Service(TurnitinContext ctx) {
        this.ctx = ctx;
    }

    public Hello doHello(String who) {
        Hello hello = new Hello();
        hello.setResponse("Hello " + who);
        return hello;
    }

}
