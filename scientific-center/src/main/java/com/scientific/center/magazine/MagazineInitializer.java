package com.scientific.center.magazine;

import com.scientific.center.repository.AreaOfScienceRepository;
import com.scientific.center.repository.PaymentTypeRepository;
import com.scientific.center.utils.CollectionFormType;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MagazineInitializer implements TaskListener {

	private final FormService formService;
	private final AreaOfScienceRepository areaOfScienceRepository;
	private final PaymentTypeRepository paymentTypeRepository;

	@Override
	public void notify(final DelegateTask delegateTask) {
		log.info("Executing magazine-registration listener");
		final var formFields = formService.getTaskFormData(delegateTask.getId()).getFormFields();
		formFields.stream()
			.filter(ff -> ff.getType() instanceof CollectionFormType)
			.forEach(ff -> {
				if (ff.getId().equals("selectedAreas")) {
					areaOfScienceRepository.findAll().forEach(aose -> {
						((CollectionFormType) ff.getType()).getValues().put(aose.getKey(), aose.getName());
					});
				}
				if (ff.getId().equals("paymentTypes")) {
					final var values = ((CollectionFormType) ff.getType()).getValues();
					paymentTypeRepository.findAll().forEach(pt -> {
						((CollectionFormType) ff.getType()).getValues().put(pt.getName(), pt.getName());
					});
				}
			});
	}
}
