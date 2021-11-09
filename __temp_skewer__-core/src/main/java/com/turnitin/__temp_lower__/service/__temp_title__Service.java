package com.turnitin.__temp_lower__.service;

import com.turnitin.__temp_lower__.model.Hello;
import com.turnitin.commons.TurnitinContext;

// This is the kind of server in which the business logic might go
public class __temp_title__Service {

	public Hello doHello(TurnitinContext ctx, String who) {
		Hello hello = new Hello();
		hello.setResponse("Hello " + who);
		return hello;
	}

}
