package com.darrencoxall.productdemo;

import com.fasterxml.jackson.databind.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
       ObjectMapper mapper = new ObjectMapper();
       mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
       mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

       return mapper;
    }

}
