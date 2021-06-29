package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.resource.CustomerResource;
import com.example.demo.resource.SaveCustomerResource;
import com.example.demo.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private CustomerService customerService;
    @GetMapping("/users/{userId}/customers")
    public List<CustomerResource> getAllCustomersByPostId(@PathVariable(name = "userId") Integer userId) {
        return customerService.getAllCustomersByUserId(userId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}/customers/{customerId}")
    public CustomerResource getCustomerByIdAndPostId(@PathVariable(name = "userId") Integer userId,
                                                   @PathVariable(name = "customerId") Integer customerId) {
        return convertToResource(customerService.getCustomerByIdAndUserId(userId, customerId));
    }

    @PostMapping("/users/{userId}/customers")
    public CustomerResource createCustomer(@PathVariable(name = "userId") Integer userId,
                                         @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.createCustomer(userId, convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/customers/{customerId}")
    public CustomerResource updateCustomer(@PathVariable(name = "userId") Integer userId,
                                         @PathVariable(name = "customerId") Integer customerId,
                                         @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.updateCustomer(userId, customerId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "userId") Integer userId,
                                           @PathVariable(name = "customerId") Integer customerId) {
        return customerService.deleteCustomer(userId, customerId);
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}
