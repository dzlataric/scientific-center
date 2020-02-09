package com.scientific.center.registration;

import com.scientific.center.repository.AreaOfScienceRepository;
import com.scientific.center.utils.CollectionFormType;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegistrationInitializer implements TaskListener {

	private final FormService formService;
	private final AreaOfScienceRepository areaOfScienceRepository;

	@Override
	public void notify(final DelegateTask delegateTask) {
		log.info("Executing pre-registration listener");
		final var formFields = formService.getTaskFormData(delegateTask.getId()).getFormFields();
		formFields.stream()
			.filter(ff -> ff.getType() instanceof CollectionFormType)
			.forEach(ff -> {
				areaOfScienceRepository.findAll().forEach(aose -> {
					((CollectionFormType) ff.getType()).getValues().put(aose.getKey(), aose.getName());
				});
			});
	}
}
