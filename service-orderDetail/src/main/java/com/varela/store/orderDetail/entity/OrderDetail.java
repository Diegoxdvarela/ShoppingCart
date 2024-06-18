package com.varela.store.orderDetail.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @Transient
    @JsonIgnore
    private Product product;
    private Long productId;
    private Integer quantity;
    private String status;

    private Double getTotal() {
        return product.getPrice() * quantity.doubleValue();
    }
}
