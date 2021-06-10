package com.milena.notificationservice;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class NotificationData {
    String toEmail;
    String subject;
    String template;
    Map<String, Object> model;
}
