package com.scientific.center.magazine;

import com.scientific.center.process.FormFields;
import com.scientific.center.process.FormSubmission;

import java.util.List;

public interface MagazineService {

	FormFields startMagazineProcess(String initiator);

	void submitMagazineForm(final List<FormSubmission> request, final String taskId);

}
