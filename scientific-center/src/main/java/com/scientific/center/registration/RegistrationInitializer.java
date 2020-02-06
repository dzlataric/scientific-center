package com.scientific.center.registration;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationInitializer implements TaskListener {

	@Override
	public void notify(final DelegateTask delegateTask) {
		log.info("Preregistration listener called!");
	}
}
