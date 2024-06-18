package com.varela.store.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varela.store.order.entity.Order;
import com.varela.store.order.entity.OrderDetail;
import com.varela.store.order.entity.Product;
import com.varela.store.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orderList = orderService.getAll();
        if (orderList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(orderList);
        }
    }

    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody Order order, BindingResult binding) {
        Order order1 = orderService.save(order);
        double total = 0.00;
        if (binding.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(binding));
        } else {
            String url = "http://localhost:8082/orderDetail";
            for(OrderDetail d : order.getDetails()){
                //add total to order
                Product product = orderService.findProductById(d.getProductId());
                total+=product.getPrice()*d.getQuantity();
                d.setOrderId(order1.getId());
                d=orderService.saveOrderDetail(url,d);
                //order1.getDetails().add(orderService.saveOrderDetail(url,d));
            }
            //update order total
            order1.setTotal(total);
            orderService.update(order1);
            return ResponseEntity.status(HttpStatus.CREATED).body(order1);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
        order.setId(id);
        Order orderFound = orderService.findById(order.getId());
        if (orderFound != null) {
            orderFound.setTotal(order.getTotal());
            orderFound.setStatus(order.getStatus());
            return ResponseEntity.ok(orderFound);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byCustomer/{id}")
    public ResponseEntity<List<Order>> findByCustomerId(@PathVariable("id") Long id){
        List<Order> orderList = orderService.getByCustomerId(id);
        if(orderList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(orderList);
        }
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
