package com.scientific.center.process;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FormSubmission {

	private String fieldId;
	private String fieldValue;

}
