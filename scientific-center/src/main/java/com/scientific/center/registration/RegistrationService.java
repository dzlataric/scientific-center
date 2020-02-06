package com.scientific.center.registration;

import com.scientific.center.process.FormFields;
import com.scientific.center.process.FormSubmission;

import java.util.List;

public interface RegistrationService {

	FormFields startRegistrationProcess();

	void submitRegistrationForm(List<FormSubmission> request, String taskId);

}
