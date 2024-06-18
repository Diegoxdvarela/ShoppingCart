package com.varela.store.customer;

import com.varela.store.customer.entity.Customer;
import com.varela.store.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomerRepositoryMockTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByAll_thenReturnAllCustomer(){
        Customer customer1 = Customer.builder()
                .name("Pedro")
                .surname("Ramirez")
                .gender("M")
                .build();
        customerRepository.save(customer1);

        List<Customer> founds = customerRepository.findAll();

        Assertions.assertEquals("Pedro", founds.get(1).getName());
    }
}
