package com.scientific.center.utils;

import org.camunda.bpm.engine.impl.form.type.StringFormType;

public class EmailAddressFormType extends StringFormType {

    @Override
    public String getName() {
        return "email";
    }
}
