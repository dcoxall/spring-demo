package com.darrencoxall.productdemo;

import java.math.BigDecimal;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
public class ProductResource {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Product product = new Product();
        product.setName("Apples");
        product.setDescription("The red/green fruit thing.");
        product.setStock(23L);
        product.setPrice(new BigDecimal("1.03"));
        product.setCurrencyCode("GBP");

        return ResponseEntity
            .ok()
            .body(product);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ArrayList<>();

        return ResponseEntity
            .ok()
            .body(products);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public <T> ResponseEntity<T> putProduct(@PathVariable String id, Product product) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public <T> ResponseEntity<T> deleteProduct(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

}
