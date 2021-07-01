package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
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
    public List<CustomerResource> getAllCustomersByUserId(@PathVariable(name = "userId") Integer userId) {
        return customerService.getAllCustomersByUserId(userId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer>getById(@PathVariable Integer id){
        Customer customer=customerService.getCustomer(id);
        if(customer==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(customer));
    }

    @PostMapping("/customers")
    public CustomerResource createCustomer(
                                         @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.createCustomer(convertToEntity(resource)));
    }

    @PutMapping("/customers/{customerId}")
    public CustomerResource updateCustomer(
                                         @PathVariable(name = "customerId") Integer customerId,
                                         @Valid @RequestBody SaveCustomerResource resource) {
        return convertToResource(customerService.updateCustomer(customerId, convertToEntity(resource)));
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(
                                           @PathVariable(name = "customerId") Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    private Customer convertToEntity(SaveCustomerResource resource) {
        return mapper.map(resource, Customer.class);
    }

    private CustomerResource convertToResource(Customer entity) {
        return mapper.map(entity, CustomerResource.class);
    }
}
