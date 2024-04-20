package com.example.reactivewebservice;

import co.elastic.apm.attach.ElasticApmAttacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApp {
    private static final Logger log = LoggerFactory.getLogger(ReactiveApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApp.class, args);
        ElasticApmAttacher.attach();
        log.info("Go to http://localhost:8080/api/coins or http://localhost:8080/api/cats");
    }

}
