package com.intellekta.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JdbcExample {
    private final JDBCSaleRepository saleRepository;

    @Autowired
    public JdbcExample(JDBCSaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void fetchSalesData() {
        int saleId = 1;

        int salesCount = saleRepository.countSales();
        System.out.println("Total Sales Count: " + salesCount);

        List<Sale> sales = saleRepository.getSalesWithAmountGreaterThan100();
        if (!sales.isEmpty()) {
            System.out.println("Sales with Amount > 100:");
            for (Sale sale : sales) {
                System.out.println("ID: " + sale.getId());
                System.out.println("Amount: " + sale.getAmount());
                System.out.println("Purchase Date: " + sale.getPurchaseDate());
                System.out.println("Sale Date: " + sale.getSaleDate());
                System.out.println("Product ID: " + sale.getProductId());
                System.out.println();
            }
        } else {
            System.out.println("No sales found with Amount > 100.");
        }

        Sale sale = saleRepository.findById(saleId);
        if (sale != null) {
            System.out.println("Sale Information:");
            System.out.println("ID: " + sale.getId());
            System.out.println("Amount: " + sale.getAmount());
            System.out.println("Purchase Date: " + sale.getPurchaseDate());
            System.out.println("Sale Date: " + sale.getSaleDate());
            System.out.println("Product ID: " + sale.getProductId());
        } else {
            System.out.println("Sale not found with ID: " + saleId);
        }
    }
    public void addSaleData() {
        Sale sale = new Sale();
        sale.setAmount(150.0);
        sale.setPurchaseDate(new Date());
        sale.setSaleDate(new Date());
        sale.setProductId(1); // Установите значение productId

        saleRepository.addSale(sale);
    }

}