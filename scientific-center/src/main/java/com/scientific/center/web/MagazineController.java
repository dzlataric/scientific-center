package com.scientific.center.web;

import com.scientific.center.magazine.MagazineService;
import com.scientific.center.process.FormFields;
import com.scientific.center.process.FormSubmission;

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
@RequestMapping(value = "/magazine")
@AllArgsConstructor
public class MagazineController {

	private final MagazineService magazineService;

	@GetMapping(value = "/{initiator}")
	public ResponseEntity<FormFields> startMagazineProcess(@PathVariable String initiator) {
		return new ResponseEntity<>(magazineService.startMagazineProcess(initiator), HttpStatus.OK);
	}

	@PostMapping(value = "/submit/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> submitMagazine(@RequestBody List<FormSubmission> magazineRequest, @PathVariable String taskId) {
		log.info("Magazine form submitted");
		magazineService.submitMagazineForm(magazineRequest, taskId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
