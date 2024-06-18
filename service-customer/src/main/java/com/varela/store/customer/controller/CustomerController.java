package com.varela.store.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varela.store.customer.entity.Customer;
import com.varela.store.customer.service.CustomerService;
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
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(){
        List<Customer> customers = customerService.listAllCustomer();
        if(customers.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(customers);
        }
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Customer customerToCreate = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerToCreate);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        customer.setId(id);
        Customer customerFound = customerService.findById(customer.getId());
        if(customerFound!=null){
            customerFound.setName(customer.getName());
            customerFound.setSurname(customer.getSurname());
            customerFound.setStatus(customer.getStatus());
            customerFound.setGender(customer.getGender());
            return ResponseEntity.ok(customerFound);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        Customer customerToDelete = customerService.deleteCustomer(id);
        if(customerToDelete!=null){
            return ResponseEntity.ok(customerToDelete);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err->{
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
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
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
