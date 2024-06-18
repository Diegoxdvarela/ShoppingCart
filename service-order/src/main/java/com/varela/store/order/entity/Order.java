package com.varela.store.order.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message = "Total must be possitive")
    @Min(value = 1,message = "Total must be > 0")
    @NonNull
    private Double total;
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    private String status;
    private Long customer_id;
    @Transient
    private List<OrderDetail> details;
}
