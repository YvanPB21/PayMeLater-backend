package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Customer> getAllCustomersByUserId(Integer userId) {
        return customerRepository.findByUserId(userId);
    }

    @Override
    public Customer getCustomerByIdAndUserId(Integer userId, Integer customerId) {
        return customerRepository.findByIdAndUserId(userId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with Id " + customerId +
                                " and UserId " + userId));
    }

    @Override
    public Customer createCustomer(Integer userId, Customer customer) {
        return userRepository.findById(userId).map(user -> {
            customer.setUser(user);
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public Customer updateCustomer(Integer userId, Integer customerId, Customer customerDetails) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);
        return customerRepository.findById(customerId).map(customer -> {
            customer.setTopay(customerDetails.getTopay());
            customer.setTasa(customerDetails.getTasa());
            customer.setName(customerDetails.getName());
            customer.setStock(customerDetails.getStock());
            customer.setLastname(customerDetails.getLastname());
            customer.setInitialdate(customerDetails.getInitialdate());
            customer.setEndingdate(customerDetails.getEndingdate());
            customer.setDni(customerDetails.getDni());
            customer.setCuotas(customerDetails.getCuotas());
            customer.setCelular(customerDetails.getCelular());
            customer.setWallet(customerDetails.getWallet());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));
    }

    @Override
    public ResponseEntity<?> deleteCustomer(Integer userId, Integer customerId) {
        return customerRepository.findByIdAndUserId(customerId, userId).map(customer -> {
            customerRepository.delete(customer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Customer not found with Id " + customerId + " and UserId " + userId));
    }
}
