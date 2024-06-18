package com.varela.store.customer.service;

import com.varela.store.customer.entity.Customer;
import com.varela.store.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> listAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Customer customerToUpdate = findById(customer.getId());
        if (customerToUpdate!=null) {
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setSurname(customer.getSurname());
            customerToUpdate.setGender(customer.getGender());
            return customerRepository.save(customerToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customerFound = customerRepository.findById(id);
        return customerFound.orElse(null);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customerFound = findById(id);
        if (null != customerFound) {
            customerFound.setStatus("I");
            return customerRepository.save(customerFound);
        }else{
            return null;
        }
    }


}
