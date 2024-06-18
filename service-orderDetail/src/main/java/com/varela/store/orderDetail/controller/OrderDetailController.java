package com.varela.store.orderDetail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varela.store.orderDetail.entity.OrderDetail;
import com.varela.store.orderDetail.service.OrderDetailService;
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
@RequestMapping("/orderDetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;


    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAll() {
        List<OrderDetail> OrderDetailDetails = orderDetailService.getAll();
        if (OrderDetailDetails.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(OrderDetailDetails);
        }
    }
    @PostMapping
    public ResponseEntity<OrderDetail> save(@Valid @RequestBody OrderDetail orderDetail, BindingResult binding) {
        orderDetail.setStatus("A");
        OrderDetail OrderDetail1 = orderDetailService.save(orderDetail);
        if (binding.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(binding));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(OrderDetail1);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setId(id);
        OrderDetail orderDetailFound = orderDetailService.findById(orderDetail.getId());
        if (orderDetailFound != null) {
            orderDetailFound.setQuantity(orderDetail.getQuantity());
            orderDetailFound.setStatus(orderDetail.getStatus());
            orderDetailFound.setProductId(orderDetail.getProductId());
            orderDetailFound.setOrderId(orderDetail.getOrderId());
            orderDetailService.save(orderDetailFound);
            return ResponseEntity.ok(orderDetailFound);
        } else {
            return ResponseEntity.notFound().build();
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
