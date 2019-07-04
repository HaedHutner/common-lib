package dev.mvvasilev.common.service;

import dev.mvvasilev.common.config.RabbitMQProperties;
import dev.mvvasilev.common.dto.EventLog;
import dev.mvvasilev.common.enums.EventType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventLogService {

    private RabbitTemplate rabbitTemplate;

    private RabbitMQProperties rabbitMQProperties;

    public EventLogService(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    public <T> void submitEvent(EventType eventType, String source, LocalDateTime submitDateTime, int version, T data) {
        EventLog<T> eventLog = new EventLog<>();
        eventLog.setEventType(eventType);
        eventLog.setSource(source);
        eventLog.setSubmittedAt(submitDateTime);
        eventLog.setVersion(version);
        eventLog.setData(data);

        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchange(),
                rabbitMQProperties.getSubmitEventRoutingKey(),
                eventLog
        );
    }
}
