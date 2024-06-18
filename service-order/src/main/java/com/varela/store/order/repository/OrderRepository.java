package com.varela.store.order.repository;

import com.varela.store.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o WHERE o.customer_id = 1")
    List<Order> findByCustomer_id(Long id);
}
