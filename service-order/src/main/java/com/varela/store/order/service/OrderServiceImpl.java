package com.varela.store.order.service;

import com.varela.store.order.entity.Order;
import com.varela.store.order.entity.OrderDetail;
import com.varela.store.order.entity.Product;
import com.varela.store.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        order.setStatus("A");
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public Order delete(Long id) {
        Order order = findById(id);
        if (null != order) {
            order.setStatus("I");
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getByCustomerId(Long id) {
        return orderRepository.findByCustomer_id(id);
    }

    @Override
    public OrderDetail saveOrderDetail(String url, OrderDetail orderDetail) {
        try {
            // Validar y construir la URL
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            String validUrl = uriBuilder.toUriString();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(orderDetail, headers);
            headers.set("Content-Type", "application/json");
            ResponseEntity<OrderDetail> response = restTemplate.exchange(validUrl, HttpMethod.POST, requestEntity, OrderDetail.class);
            return response.getBody();
        } catch (IllegalArgumentException e) {
            // Manejar error de URL no válida
            System.err.println("La URL no es válida: " + e.getMessage());
            return null;
        } catch (Exception e) {
            // Manejar otros errores
            System.err.println("Error al enviar la solicitud: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Product findProductById(Long id) {
        ResponseEntity<Product> response =
                restTemplate.getForEntity(
                        "http://localhost:8083/products/"+id,
                        Product.class
                );
        Product product = response.getBody();
        assert product != null;
        return product;
    }

    @Override
    public Order update(Order order) {
        Order order1 = findById(order.getId());
        if (order1!=null) {
            order1.setTotal(order.getTotal());
            order1.setCustomer_id(order.getCustomer_id());
            order1.setStatus(order.getStatus());
            order1.setDetails(order.getDetails());
            return orderRepository.save(order1);
        } else {
            return null;
        }
    }


}
