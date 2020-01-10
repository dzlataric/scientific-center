package com.scientific.center.process;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FormField {

	private String id;
	private String label;
	private String type;

}
