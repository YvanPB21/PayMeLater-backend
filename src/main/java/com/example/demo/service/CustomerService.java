package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomersByUserId(Integer userId);
    Customer getCustomer(Integer id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Integer customerId, Customer customerDetails);
    void deleteCustomer(Integer customerId);
}
