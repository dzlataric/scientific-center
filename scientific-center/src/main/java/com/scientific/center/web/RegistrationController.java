package com.scientific.center.web;

import com.scientific.center.process.FormFields;
import com.scientific.center.process.FormSubmission;
import com.scientific.center.registration.RegistrationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/registration")
@AllArgsConstructor
public class RegistrationController {

	private final RegistrationService registrationService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormFields> startRegistrationProcess() {
		log.info("Registration process started");
		return new ResponseEntity<>(registrationService.startRegistrationProcess(), HttpStatus.OK);
	}

	@PostMapping(value = "/submit/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> submitRegistration(@RequestBody List<FormSubmission> registrationRequest, @PathVariable String taskId) {
		log.info("Registration form submitted");
		registrationService.submitRegistrationForm(registrationRequest, taskId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/confirm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> confirmRegistration(@PathVariable String id) {
		log.info("Confirming registration for process with id {}", id);
		registrationService.confirmRegistration(id);
		return ResponseEntity.accepted().build();
	}

}
