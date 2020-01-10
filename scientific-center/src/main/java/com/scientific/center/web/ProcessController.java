package com.scientific.center.web;

import com.scientific.center.process.FormField;
import com.scientific.center.process.FormFields;
import com.scientific.center.process.ProcessService;

import java.util.stream.Collectors;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/process")
public class ProcessController {

	private final ProcessService processService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final FormService formService;

	@Autowired
	public ProcessController(final ProcessService processService, final RuntimeService runtimeService, final TaskService taskService,
		final FormService formService) {
		this.processService = processService;
		this.runtimeService = runtimeService;
		this.taskService = taskService;
		this.formService = formService;
	}

	@PostMapping(value = "/start/registration")
	public ResponseEntity<FormFields> startRegistrationProcess() {
		final var processInstance = runtimeService.startProcessInstanceByKey("");
		final var taskId = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().stream().findFirst().orElseThrow().getId();
		final var formFields = formService.getTaskFormData(taskId).getFormFields().stream().map(ff -> FormField.builder().build())
			.collect(Collectors.toList());
		return new ResponseEntity<>(FormFields.builder().processInstanceId(processInstance.getId()).taskId(taskId).formFields(formFields).build(),
			HttpStatus.OK);
	}

}
