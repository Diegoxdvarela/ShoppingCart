package com.varela.store.orderDetail.repository;

import com.varela.store.orderDetail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailServiceRepository extends JpaRepository<OrderDetail, Long> {
}
