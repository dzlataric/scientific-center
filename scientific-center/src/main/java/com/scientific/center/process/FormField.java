package com.scientific.center.process;

import java.util.List;

import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.camunda.bpm.engine.form.FormType;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FormField {

	private String id;
	private String label;
	private FormType type;
	private Object value;
	private List<FormFieldValidationConstraint> validationConstraints;

}
