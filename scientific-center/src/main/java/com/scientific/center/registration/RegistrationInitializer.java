package com.scientific.center.registration;

import com.scientific.center.repository.AreaOfScienceRepository;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormType;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
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
		log.info("Preregistration listener called!");
		final var formFields = formService.getTaskFormData(delegateTask.getId()).getFormFields();
		final var all = areaOfScienceRepository.findAll();
		log.info("STOP");
	}
}
