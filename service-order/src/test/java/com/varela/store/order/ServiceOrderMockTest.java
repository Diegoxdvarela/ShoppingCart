package com.varela.store.order;

import com.varela.store.order.entity.Order;
import com.varela.store.order.repository.OrderRepository;
import com.varela.store.order.service.OrderService;
import com.varela.store.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ServiceOrderMockTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;


    @Test
    public void whenValidListThenReturnList(){
    List<Order> orderList = orderRepository.findAll();
        Assertions.assertEquals(orderList.get(0).getId().toString(),"1");
    }

    @Test
    public void whenFindList(){
        Order order = Order.builder()
                .total(12.0)
                .createdAt(new Date())
                .status("Active").build();
        orderRepository.save(order);
        List<Order> orderList = orderRepository.findAll();

        Assertions.assertEquals(orderList.size(),1);
    }
}
