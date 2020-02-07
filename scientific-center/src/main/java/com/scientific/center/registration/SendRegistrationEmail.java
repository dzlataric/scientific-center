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

	private final JavaMailSender mailSender;

	@Override
	public void execute(final DelegateExecution delegateExecution) throws Exception {
		log.info("Sending registration email");
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, false, StandardCharsets.UTF_8.name());
			helper.setTo("zlatara.92@gmail.com");
			helper.setFrom("timsedamnaest@gmail.com");
			helper.setSubject("Account Activation");
			String message = "To activate your account " + "<a href=\"" + delegateExecution.getProcessInstanceId() + "\">click here.</a><br><br>";
			helper.setText(message, true);
			mailSender.send(mail);
		} catch (Exception e) {
			log.error("Error sending email \n {}", ExceptionUtils.getStackTrace(e));
			throw e;
		}
	}

}
