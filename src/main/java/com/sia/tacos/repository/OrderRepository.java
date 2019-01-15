package com.sia.tacos.repository;

import org.springframework.data.repository.CrudRepository;

import com.sia.tacos.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
