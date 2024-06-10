package com.example.eventdemo.listener;

import com.example.eventdemo.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class RabbitmqListener {

    private final ApplicationEventPublisher applicationEventPublisher;

    @RabbitListener(queues = "order.queue")
    public void receiveEvent(OrderCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}