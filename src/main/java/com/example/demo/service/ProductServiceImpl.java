package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product edit(Product product, Integer id) {
        return productRepository.findById(id).map(p->{
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setUrl(product.getUrl());
            p.setPosition(product.getPosition());
            return productRepository.save(p);
        }).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
