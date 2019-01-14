package com.sia.tacos.repository;

import com.sia.tacos.model.Order;

public interface OrderRepository {

	Order save(Order order);
}
