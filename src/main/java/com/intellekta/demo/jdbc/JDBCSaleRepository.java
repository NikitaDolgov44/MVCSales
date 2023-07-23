package com.intellekta.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCSaleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCSaleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countSales() {
        String query = "SELECT COUNT(*) FROM sales WHERE amount > 100";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public Sale findById(int saleId) {
        String query = "SELECT * FROM sales WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{saleId}, (rs, rowNum) -> {
            Sale sale = new Sale();
            sale.setId(rs.getInt("id"));
            sale.setAmount(rs.getDouble("amount"));
            sale.setPurchaseDate(rs.getDate("purchase_date"));
            sale.setSaleDate(rs.getDate("sale_date"));
            sale.setProductId(rs.getInt("product_id"));
            return sale;
        });
    }

    public List<Sale> getSalesWithAmountGreaterThan100() {
        String selectQuery = "SELECT * FROM sales WHERE amount > 100";
        return jdbcTemplate.query(selectQuery, (rs, rowNum) -> {
            Sale sale = new Sale();
            sale.setId(rs.getInt("id"));
            sale.setAmount(rs.getDouble("amount"));
            sale.setPurchaseDate(rs.getDate("purchase_date"));
            sale.setSaleDate(rs.getDate("sale_date"));
            sale.setProductId(rs.getInt("product_id"));
            return sale;
        });
    }
    public void addSale(Sale sale) {
        String insertQuery = "INSERT INTO sales (id, amount, purchase_date, sale_date, product_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, sale.getId(), sale.getAmount(), sale.getPurchaseDate(), sale.getSaleDate(), sale.getProductId());
    }
}