package com.mustafinsa.eshop.Model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAOSpringJdbcImpl implements ProductDAO {

    private DataSource dataSource;

    public ProductDAOSpringJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Product product) {
        String insertQuery = "INSERT INTO Products (name, price, quantity) values (?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        Object[] args = new Object[]{product.getName(), product.getPrice(), product.getQuantity()};

        int out = jdbcTemplate.update(insertQuery, args);

        if (out != 0) {
            System.out.println("Product saved with id " /*+ product.getId()*/);
        } else {
            System.out.println("Product save failed with id"/* + product.getId()*/);
        }

    }

    @Override
    public Product getById(int id) {
        String selectByIdQuery = "SELECT id, name, price, quantity FROM Products WHERE id = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Product product = jdbcTemplate.queryForObject(selectByIdQuery, new Object[]{id}, new RowMapper<Product>(){

            @Override
            public Product mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                return product;
            }});

        return product;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Product> getAll() {
        String query = "select id, name, price, quantity from Products";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Product> productList = new ArrayList<>();

        List<Map<String, Object>> productRows = jdbcTemplate.queryForList(query);

        for(Map<String,Object> productRow : productRows){
            Product product = new Product();
            product.setId(Integer.parseInt(String.valueOf(productRow.get("id"))));
            product.setName(String.valueOf(productRow.get("name")));
            product.setPrice(Integer.parseInt(String.valueOf(productRow.get("price"))));
            product.setQuantity(Integer.parseInt(String.valueOf(productRow.get("quantity"))));

            productList.add(product);
        }
        return productList;
    }
}
