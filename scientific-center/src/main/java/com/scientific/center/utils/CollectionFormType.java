package com.scientific.center.utils;

import java.util.Collection;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

public class CollectionFormType extends EnumFormType {

	public CollectionFormType(Map<String, String> values) {
		super(values);
	}

	@Override
	public String getName() {
		return "collection";
	}

	@Override
	public TypedValue convertValue(TypedValue propertyValue) {
		Object value = propertyValue.getValue();
		if (value != null && !(value instanceof Collection)) {
			throw new ProcessEngineException(String.format("Value %s is not of type Collection!", value.toString()));
		} else {
			return Variables.objectValue(value, propertyValue.isTransient()).create();
		}
	}

	@Override
	protected void validateValue(Object value) {
		((Collection<?>) value).stream().filter(e -> values != null && !values.containsKey(e)).findFirst().ifPresent(e -> {
			throw new ProcessEngineException(String.format("Invalid value for collection form property: %s", value));
		});
	}

}
