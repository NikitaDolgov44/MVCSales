package com.intellekta.demo.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import java.util.Date;

@SpringBootApplication
@EntityScan
public class JpaExample {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JpaExample.class, args);
        JPASaleRepository saleRepository = context.getBean(JPASaleRepository.class);
        saleRepository.deleteAll();

        JPASale sale = new JPASale();
        sale.setAmount(150.0);
        sale.setPurchaseDate(new Date());
        sale.setSaleDate(new Date());
        sale.setProductId(1);

        saleRepository.save(sale);
    }
}
