package com.milena.notificationservice;

import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
@Component
public class NotificationEventConsumer {

    private final EmailService emailService;

    @Bean
    public Consumer<UserDto> consumer() {
        return user -> {
            log.info("Received new message from Kafka topic");
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            var data = new NotificationData.NotificationDataBuilder()
                    .subject("Account confirmation")
                    .toEmail(user.getEmail())
                    .model(model)
                    .template("account-confirmation")
                    .build();

            try {
                emailService.sendEmail(data);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        };
    }
}
