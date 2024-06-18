package com.varela.store.customer.service;

import com.varela.store.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> listAllCustomer();

    public Customer save(Customer customer);

    public Customer update(Customer customer);

    public Customer findById(Long id);
    public Customer deleteCustomer(Long id);
}
