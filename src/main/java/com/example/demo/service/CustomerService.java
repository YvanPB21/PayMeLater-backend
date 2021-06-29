package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomersByUserId(Integer userId);
    Customer getCustomerByIdAndUserId(Integer userId, Integer customerId);
    Customer createCustomer(Integer userId, Customer customer);
    Customer updateCustomer(Integer userId, Integer customerId, Customer customerDetails);
    ResponseEntity<?> deleteCustomer(Integer userId, Integer customerId);
}
