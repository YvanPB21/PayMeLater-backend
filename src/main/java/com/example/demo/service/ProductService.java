package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getProduct(Integer id);
    Product save(Product product);
    Optional<Product> findById(Integer id);
    List<Product> listAllProduct();
    Product edit(Product product,Integer id);
    //List<Product>findByCategory(Category category);
    void deleteById(Integer id);
}
