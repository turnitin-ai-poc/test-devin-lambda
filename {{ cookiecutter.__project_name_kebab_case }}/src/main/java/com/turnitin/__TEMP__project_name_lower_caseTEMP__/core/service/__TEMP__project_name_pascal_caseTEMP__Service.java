//TEMP if cookiecutter.include_http is selected_option or cookiecutter.include_sqs_listener is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__.core.service;

import com.turnitin.commons.TurnitinContext;

// This is the kind of server in which the business logic might go
public class __TEMP__project_name_pascal_caseTEMP__Service {

    TurnitinContext ctx;

    public __TEMP__project_name_pascal_caseTEMP__Service(TurnitinContext ctx) {
        this.ctx = ctx;
    }

    public String doHello(String who) {
        return "Hello " + who;
    }

}
//TEMP endif
