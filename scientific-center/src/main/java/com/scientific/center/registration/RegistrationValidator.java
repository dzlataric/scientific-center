package com.scientific.center.registration;

import com.scientific.center.model.Role;
import com.scientific.center.model.UserEntity;
import com.scientific.center.repository.AreaOfScienceRepository;
import com.scientific.center.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegistrationValidator implements JavaDelegate {

	private final UserRepository userRepository;
	private final AreaOfScienceRepository areaOfScienceRepository;
	private final IdentityService identityService;

	@Override
	public void execute(final DelegateExecution delegateExecution) {
		log.info("Validating registration data");
		userRepository.findByUsername(delegateExecution.getVariable("username").toString())
			.ifPresentOrElse(u -> {
				throw new RuntimeException("Username already exists!");
			}, () -> {
				log.info("Username is valid");
				final var userEntity = buildUserEntity(delegateExecution);
				userRepository.save(userEntity);
				identityService.saveUser(createCamundaUser(userEntity));
			});
	}

	private UserEntity buildUserEntity(DelegateExecution execution) {
		log.debug("Execution variables: {} ", execution.getVariables().entrySet()
			.stream()
			.map(entry -> "[" + entry.getKey() + ": " + entry.getValue() + "]")
			.collect(Collectors.joining(", ")));
		return UserEntity.builder()
			.username(execution.getVariable("username").toString())
			.password(execution.getVariable("password").toString())
			.emailAddress(execution.getVariable("email").toString())
			.firstName(execution.getVariable("firstName").toString())
			.lastName(execution.getVariable("lastName").toString())
			.country(execution.getVariable("country").toString())
			.city(execution.getVariable("city").toString())
			.role(Role.REGULAR)
			.reviewer((Boolean) execution.getVariable("reviewer"))
			.enabled(false)
			.enabledAsReviewer(false)
			.areasOfScience(
				((List<String>) execution.getVariable("areasOfScience")).stream()
					.map(areaOfScienceRepository::findByKey)
					.filter(Optional::isPresent)
					.map(Optional::get)
					.collect(Collectors.toList()))
			.build();
	}

	private User createCamundaUser(final UserEntity user) {
		final var camundaUser = identityService.newUser(user.getUsername());
		camundaUser.setPassword(user.getPassword());
		camundaUser.setFirstName(user.getFirstName());
		camundaUser.setLastName(user.getLastName());
		camundaUser.setEmail(user.getEmailAddress());
		return camundaUser;
	}

}
