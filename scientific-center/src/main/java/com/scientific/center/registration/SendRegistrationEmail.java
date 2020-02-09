package com.scientific.center.registration;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SendRegistrationEmail implements JavaDelegate {

	private static final String BASE_URL = "http://localhost:8090/registration/confirm/";

	private final JavaMailSender mailSender;

	@Override
	public void execute(final DelegateExecution delegateExecution) throws Exception {
		final var emailAddress = delegateExecution.getVariable("email").toString();
		log.info("Sending registration email to {}", emailAddress);
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, false, StandardCharsets.UTF_8.name());
			helper.setTo(emailAddress);
			helper.setFrom("timsedamnaest@gmail.com");
			helper.setSubject("Account Activation");
			String message =
				"To activate your account " + "<a href=\"" + BASE_URL.concat(delegateExecution.getProcessInstanceId()) + "\">click here.</a><br><br>";
			helper.setText(message, true);
			mailSender.send(mail);
		} catch (Exception e) {
			log.error("Error sending email to {} \n {}", emailAddress, ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}

}
