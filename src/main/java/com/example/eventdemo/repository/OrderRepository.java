package com.example.eventdemo.repository;

import com.example.eventdemo.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
