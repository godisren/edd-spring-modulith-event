package com.example.eventdemo.service;

import com.example.eventdemo.entity.Order;
import com.example.eventdemo.event.OrderCreatedEvent;
import com.example.eventdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    public static boolean error = false;

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Order createOrder(Order order) {
        log.info("create order");

        var createdOrder =  orderRepository.save(order);

        applicationEventPublisher.publishEvent(new OrderCreatedEvent(order));

        if (error) throw new RuntimeException("failed in transaction...");

        return createdOrder;
    }

}
