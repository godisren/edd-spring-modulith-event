package com.example.eventdemo;

import com.example.eventdemo.entity.Order;
import com.example.eventdemo.event.OrderCreatedEvent;
import com.example.eventdemo.listener.OrderEventListener1;
import com.example.eventdemo.repository.OrderRepository;
import com.example.eventdemo.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.events.EventPublication;
import org.springframework.modulith.events.support.PersistentApplicationEventMulticaster;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RecordApplicationEvents
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ApplicationEvents events;

    @Autowired
    PersistentApplicationEventMulticaster multicaster;

    @Test
    void createOrder() {

        Order order = orderService.createOrder(generateOrder());

        Optional<Order> findOrder = orderRepository.findById(order.getId());
        assertTrue(findOrder.isPresent());

        long numEventsOfCreated = events.stream(OrderCreatedEvent.class).count();
        assertEquals(1, numEventsOfCreated);

        waitForInSeconds(3);
    }

    @Test
    @DisplayName("check if producer failed")
    void createOrderIfServiceProducerFailed() {

        OrderService.error = true;

        assertThrows(RuntimeException.class, () -> orderService.createOrder(generateOrder()));


    }

    @Test
    @DisplayName("check if listener failed")
    void createOrderIfListenerFailed() {

        OrderEventListener1.error = true;

        orderService.createOrder(generateOrder());
    }

    @Test
    void resubmitIncompletePublications() {

        OrderEventListener1.error = false;

        Predicate<EventPublication> filter = eventPublication -> true;
        multicaster.resubmitIncompletePublications(filter);
    }

    private static void waitForInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
        }
    }

    private Order generateOrder() {

        Order o = new Order();
        o.setProductName("PC");
        o.setPrice(BigDecimal.valueOf(100));
        o.setAmount(20);

        return o;
    }

}
