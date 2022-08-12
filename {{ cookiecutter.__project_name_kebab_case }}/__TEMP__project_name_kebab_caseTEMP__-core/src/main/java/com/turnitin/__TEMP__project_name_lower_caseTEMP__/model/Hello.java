//TEMP if cookiecutter.include_http is selected_option or cookiecutter.include_sqs_listener is selected_option:
package com.turnitin.__TEMP__project_name_lower_caseTEMP__.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hello {
    private String response;
}
//TEMP endif
