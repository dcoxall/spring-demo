package com.darrencoxall.productdemo;

import org.springframework.boot.actuate.health.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDataHealth extends AbstractHealthIndicator {

    @Autowired
    private ProductRepository productRepository;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        Iterable<Product> products = productRepository.findAll();
        if (products.iterator().hasNext()) {
            builder.up();
        } else {
            builder.down();
        }
    }

}
