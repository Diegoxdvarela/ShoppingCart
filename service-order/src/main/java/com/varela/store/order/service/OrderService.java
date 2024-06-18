package com.varela.store.order.service;

import com.varela.store.order.entity.Order;
import com.varela.store.order.entity.OrderDetail;
import com.varela.store.order.entity.Product;

import java.util.List;

public interface OrderService {

    public List<Order> getAll();

    public Order save(Order order);

    public Order findById(Long id);

    public Order delete(Long id);

    public List<Order> getByCustomerId(Long id);

    public OrderDetail saveOrderDetail(String url,OrderDetail orderDetail);

    public Product findProductById(Long id);

    public Order update(Order order);
}
