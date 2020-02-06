package com.scientific.center.utils;

import org.camunda.bpm.engine.impl.form.type.StringFormType;

public class PasswordFormType extends StringFormType {

	@Override
	public String getName() {
		return "password";
	}

}
