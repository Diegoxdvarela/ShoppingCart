package com.varela.store.customer;

import com.varela.store.customer.entity.Customer;
import com.varela.store.customer.repository.CustomerRepository;
import com.varela.store.customer.service.CustomerService;
import com.varela.store.customer.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CustomerServiceMockTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
        Customer customer2 = Customer.builder()
                .name("Pedro")
                .surname("Ramirez")
                .gender("M")
                .build();
        Mockito.when(customerRepository.findById(2L)
                )
                .thenReturn(Optional.of(customer2));
    }

    @Test
    public void whenValidGetId_ThenReturnCustomer(){
        Customer found = customerService.findById(2L);
        Assertions.assertEquals(found.getName(),"Pedro");
    }
}
