package com.example.eventdemo.event;

import com.example.eventdemo.entity.Order;
import org.springframework.modulith.events.Externalized;

//@Externalized(target = "order.created")
public record OrderCreatedEvent(Order order) {}
