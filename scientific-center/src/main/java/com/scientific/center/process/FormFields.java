package com.scientific.center.process;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FormFields {

	private String taskId;
	private String processInstanceId;
	private List<FormField> formFields;

}
