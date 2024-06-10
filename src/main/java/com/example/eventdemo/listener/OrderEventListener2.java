package com.example.eventdemo.listener;

import com.example.eventdemo.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class OrderEventListener2 {

    @EventListener
    public void receiveAndSendEmail(OrderCreatedEvent event) {
        // send email after receive OrderCreatedEvent
        log.info("calculate amount when receiving order created event [{}] ", event.order());
    }

}
