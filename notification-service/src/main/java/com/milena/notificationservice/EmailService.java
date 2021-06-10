package com.milena.notificationservice;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final Configuration configuration;
    private final JavaMailSender javaMailSender;

    public void sendEmail(NotificationData data) throws IOException, TemplateException {
        var template = configuration.getTemplate(data.getTemplate() + ".ftl");
        var content = FreeMarkerTemplateUtils.processTemplateIntoString(template, data.getModel());
        log.info("Preparing message.");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            var messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(data.getToEmail());
            messageHelper.setSubject(data.getSubject());
            messageHelper.setText(content, true);
        };
        log.info("Sending email to: {}", data.getToEmail());
        javaMailSender.send(mimeMessagePreparator);
    }
}
