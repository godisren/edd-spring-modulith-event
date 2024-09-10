package com.example.eventdemo.listener;

import com.example.eventdemo.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class OrderEventListener1 {

    public static boolean error = false;

    @EventListener
    public void receiveAndSendEmail(OrderCreatedEvent event) {
        // send email after receive OrderCreatedEvent
        log.info("send email when receiving order created event [{}] ", event);
    }

//    @EventListener
//    @Async
//    public void receiveAndSendEmail(OrderCreatedEvent event) {
//        // send email after receive OrderCreatedEvent
//        log.info("[async] send email when receiving order created event [{}] ", event);
//    }
//
//    @Async
//    @TransactionalEventListener
//    public void receiveAndSendEmail(OrderCreatedEvent event) {
//        // send email after receive OrderCreatedEvent
//        log.info("[TransactionalEventListener] send email when receiving order created event [{}] ", event);
//    }
//
//    @ApplicationModuleListener
//    public void receiveAndSendEmail(OrderCreatedEvent event) {
//        // send email after receive OrderCreatedEvent
//        log.info("[ApplicationModuleListener] send email when receiving order created event [{}] ", event);
//
//        if (error) {
//            log.error("run into unexpected error.");
//            throw new RuntimeException("listener failed...");
//        }
//    }
}
