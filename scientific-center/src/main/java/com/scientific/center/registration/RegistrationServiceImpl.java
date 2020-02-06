package com.scientific.center.registration;

import com.scientific.center.process.FormField;
import com.scientific.center.process.FormFields;
import com.scientific.center.utils.ProcessUtils;

import java.util.stream.Collectors;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final FormService formService;
	private final IdentityService identityService;

	@Override
	public FormFields startRegistrationProcess() {
		identityService.setAuthenticatedUserId(null);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ProcessUtils.USER_REGISTRATION_PROCESS);
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().stream().findFirst().orElseThrow();
		TaskFormData taskFormData = formService.getTaskFormData(task.getId());
		return FormFields.builder()
			.processInstanceId(processInstance.getId())
			.taskId(task.getId())
			.formFields(taskFormData.getFormFields()
				.stream()
				.map(tfd -> FormField.builder()
					.id(tfd.getId())
					.label(tfd.getLabel())
					.type(tfd.getType().getName())
					.build())
				.collect(Collectors.toList()))
			.build();
	}

}
