package com.scientific.center.registration;

import com.scientific.center.process.FormField;
import com.scientific.center.process.FormFields;
import com.scientific.center.process.FormSubmission;
import com.scientific.center.utils.ProcessUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;

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
				.map(ff -> FormField.builder()
					.id(ff.getId())
					.label(ff.getLabel())
					.type(ff.getType())
					.value(ff.getValue().getValue())
					.validationConstraints(ff.getValidationConstraints())
					.build())
				.collect(Collectors.toList()))
			.build();
	}

	@Override
	public void submitRegistrationForm(final List<FormSubmission> request, final String taskId) {
		Map<String, Object> fieldsMap = request.stream()
			.collect(HashMap::new, (map, field) -> map.put(field.getFieldId(), field.getFieldValue()), HashMap::putAll);
		formService.submitTaskForm(taskId, fieldsMap);
		log.info("Submitted registration form for task with id: {}", taskId);
	}

	@Override
	public void confirmRegistration(final String id) {
		Optional.ofNullable(taskService.createTaskQuery()
			.processInstanceId(id)
			.active()
			.singleResult()).ifPresentOrElse(t -> taskService.complete(t.getId()), () -> {
			throw new BadRequestException(String.format("No tasks found for process with id %s", id));
		});
	}

}
