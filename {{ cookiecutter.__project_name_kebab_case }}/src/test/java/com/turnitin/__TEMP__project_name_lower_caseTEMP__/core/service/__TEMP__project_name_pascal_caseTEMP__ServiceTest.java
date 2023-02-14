//TEMP if cookiecutter.include_http is selected_option or cookiecutter.include_sqs_listener is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__.core.service;

import org.junit.jupiter.api.Test;
import com.turnitin.commons.TurnitinContext;

import static org.assertj.core.api.Assertions.assertThat;

class __TEMP__project_name_pascal_caseTEMP__ServiceTest {
	@Test
	void testSayingHello() {
		TurnitinContext context = TurnitinContext.builder().build();
		__TEMP__project_name_pascal_caseTEMP__Service service = new __TEMP__project_name_pascal_caseTEMP__Service(context);
		assertThat(service.doHello("abc")).isEqualTo("Hello abc");
	}
}
//TEMP endif
