package com.varela.store.orderDetail.service;

import com.varela.store.orderDetail.entity.OrderDetail;
import com.varela.store.orderDetail.entity.Product;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> getAll();

    public OrderDetail save(OrderDetail orderDetail);

    public OrderDetail udpate(OrderDetail orderDetail);

    public OrderDetail delete(Long id);

    public OrderDetail findById(Long id);

    public Product findProductById(Long id);
}
