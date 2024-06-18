package com.varela.store.orderDetail.service;

import com.varela.store.orderDetail.entity.OrderDetail;
import com.varela.store.orderDetail.entity.Product;
import com.varela.store.orderDetail.repository.OrderDetailServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailServiceRepository orderDetailServiceRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailServiceRepository.findAll();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailServiceRepository.save(orderDetail);
    }

    @Override
    public OrderDetail udpate(OrderDetail orderDetail) {
        OrderDetail orderDetail1 = findById(orderDetail.getId());
        if (orderDetail1 != null) {
            orderDetail1.setProductId(orderDetail1.getProductId());
            orderDetail1.setOrderId(orderDetail1.getOrderId());
            orderDetail1.setQuantity(orderDetail1.getQuantity());
            return orderDetailServiceRepository.save(orderDetail1);
        } else {
            return null;
        }
    }

    @Override
    public OrderDetail delete(Long id) {
        OrderDetail orderDetail = findById(id);
        if (null != orderDetail) {
            orderDetail.setStatus("I");
            return orderDetailServiceRepository.save(orderDetail);
        } else {
            return null;
        }
    }

    @Override
    public OrderDetail findById(Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailServiceRepository.findById(id);
        return orderDetailOptional.orElse(null);
    }

    @Override
    public Product findProductById(Long id) {
        ResponseEntity<Product[]> response =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/"+id,
                        Product[].class
                );
        Product[] products = response.getBody();
        assert products != null;
        //return Arrays.asList(products);
        return null;
    }
}
