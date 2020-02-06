package com.scientific.center.registration;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SendRegistrationEmail implements JavaDelegate {

	@Override
	public void execute(final DelegateExecution delegateExecution) throws Exception {
		log.info("Sending registration email");
	}
}
