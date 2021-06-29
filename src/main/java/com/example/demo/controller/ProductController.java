package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(){
        List<Product> products = new ArrayList<>();
        products=productService.listAllProduct();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product>getById(@PathVariable Integer id){
        Product product=productService.getProduct(id);
        if(product==null)
            return ResponseEntity.notFound().build();
        else
            return (ResponseEntity.ok(product));
    }

    @PostMapping
    public ResponseEntity<Product> newProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.edit(product,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

