package com.scientific.center.registration;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class SendRegistrationEmail implements JavaDelegate {

	@Override
	public void execute(final DelegateExecution delegateExecution) throws Exception {
		//TODO send registration email notification
	}
}
