package com.intellekta.demo.jdbc;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Sale {
    private int id;
    private double amount;
    private Date purchaseDate;
    private Date saleDate;
    private int productId;
}
