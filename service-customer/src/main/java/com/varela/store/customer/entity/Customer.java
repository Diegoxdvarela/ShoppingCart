package com.varela.store.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "Surname can't be empty")
    private String surname;
    @NotEmpty(message = "Gender can't be empty")
    private String gender;
    @NotEmpty(message = "Status must be filled")
    private String status;
}
