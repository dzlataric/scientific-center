package com.scientific.center.web;

import com.scientific.center.process.FormFields;
import com.scientific.center.registration.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/registration")
@AllArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormFields> startRegistrationProcess() {
		return new ResponseEntity<>(registrationService.startRegistrationProcess(), HttpStatus.OK);
	}

}
