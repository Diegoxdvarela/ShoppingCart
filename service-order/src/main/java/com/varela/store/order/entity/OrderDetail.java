package com.varela.store.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderDetail {
    private Long id;
    private Integer quantity;
    private Long productId;
    private Long orderId;
}
