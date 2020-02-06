package com.scientific.center.registration;

import com.scientific.center.repository.UserRepository;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegistrationValidator implements JavaDelegate {

	private final UserRepository userRepository;

	@Override
	public void execute(final DelegateExecution delegateExecution) {
		log.info("Validating registration data");
		userRepository.findByUsername(delegateExecution.getVariable("username").toString())
			.ifPresentOrElse(u -> {
				delegateExecution.setVariable("valid", false);
				throw new RuntimeException("Username already exists!");
			}, () -> {
				delegateExecution.setVariable("valid", true);
				log.info("Username is valid");
			});
	}
}
