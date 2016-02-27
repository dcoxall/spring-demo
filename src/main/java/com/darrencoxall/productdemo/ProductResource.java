package com.darrencoxall.productdemo;

import java.math.BigDecimal;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Long productId = Long.parseLong(id);
        Product product = productRepository.findOne(productId);
        if (product != null) {
            return ResponseEntity.ok().body(product);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = new ArrayList<>();
        for(Product product : productRepository.findAll()) {
            products.add(product);
        }

        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public <T> ResponseEntity<T> putProduct(
            @PathVariable String id,
            @RequestBody Product product,
            UriComponentsBuilder uriBuilder) {
        product.setId(Long.parseLong(id));
        productRepository.save(product);
        HttpHeaders headers = new HttpHeaders();
        UriComponents uriComponents = uriBuilder.path("/products/{id}").buildAndExpand(id);
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public <T> ResponseEntity<T> deleteProduct(@PathVariable String id) {
        productRepository.delete(Long.parseLong(id));
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
    }

}
