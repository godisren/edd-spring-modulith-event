package com.example.eventdemo;

import com.example.eventdemo.entity.Order;
import com.example.eventdemo.event.OrderCreatedEvent;
import com.example.eventdemo.listener.OrderEventListener1;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
class SpringEventTest {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @MockBean
    private OrderEventListener1 eventListener1;

    @Test
    void sendOrderCreatedEvent() {
        // When
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(generateOrder()));

        // Assert
        Mockito.verify(eventListener1, times(1)).receiveAndSendEmail(any());
    }

    private Order generateOrder() {

        Order o = new Order();
        o.setProductName("PC");
        o.setPrice(BigDecimal.valueOf(100));
        o.setAmount(20);

        return o;
    }

}
