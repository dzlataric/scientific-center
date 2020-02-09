package com.scientific.center.web;

import java.util.List;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/process")
@AllArgsConstructor
public class ProcessController {

	private final TaskService taskService;

	@GetMapping(path = "/tasks/{processInstanceId}", produces = "application/json")
	public @ResponseBody
	ResponseEntity<List<String>> get(@PathVariable String processInstanceId) {
		return new ResponseEntity<>(taskService.createTaskQuery().processInstanceId(processInstanceId).list().stream()
			.map(t -> "ID: " + t.getId() + ", NAME: " + t.getName() + ", ASSIGNEE: " + t.getAssignee()).collect(Collectors.toList()), HttpStatus.OK);
	}

}
