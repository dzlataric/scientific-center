package com.scientific.center.config;

import com.scientific.center.utils.EmailAddressFormType;
import com.scientific.center.utils.CollectionFormType;
import com.scientific.center.utils.PasswordFormType;

import java.util.ArrayList;
import java.util.HashMap;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFormFieldTypeConfig extends AbstractProcessEnginePlugin {

	private ProcessEngineConfigurationImpl processEngineConfiguration;

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		this.processEngineConfiguration = processEngineConfiguration;
		if (processEngineConfiguration.getCustomFormTypes() == null) {
			processEngineConfiguration.setCustomFormTypes(new ArrayList<>());
		}
		processEngineConfiguration.getCustomFormTypes().add(new PasswordFormType());
		processEngineConfiguration.getCustomFormTypes().add(new EmailAddressFormType());
		processEngineConfiguration.getCustomFormTypes().add(new CollectionFormType(new HashMap<>()));
	}

}
