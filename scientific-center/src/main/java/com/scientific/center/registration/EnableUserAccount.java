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
public class EnableUserAccount implements JavaDelegate {

	private final UserRepository userRepository;

	@Override
	public void execute(final DelegateExecution delegateExecution) {
		final var username = delegateExecution.getVariable("username").toString();
		log.info("Setting user with username {} to enabled state", username);
		final var user = userRepository.findByUsername(username).orElseThrow();
		user.setEnabled(true);
	}
}
