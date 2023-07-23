package com.intellekta.demo.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
@Entity
@Table("sale")
@NoArgsConstructor
@Getter
@Setter
public class JPASale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column("amount")
    private double amount;

    @Temporal(TemporalType.DATE)
    @Column("purchase_date")
    private Date purchaseDate;

    @Temporal(TemporalType.DATE)
    @Column("sale_date")
    private Date saleDate;

    @Column("product_id")
    private int productId;

}