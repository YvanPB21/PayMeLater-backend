package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
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

    @Override
    public Customer getCustomer(Integer id) {
            return customerRepository.findById(id).orElse(null);

    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer updateCustomer(Integer customerId, Customer customerDetails) {
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
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
