package com.mustafinsa.eshop.Model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    DataSource dataSource;

    public ProductDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Product product) {
        String insertQuery = "INSERT INTO Products (name, price, quantity) values (?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getQuantity());

            int out = ps.executeUpdate();

            if (out != 0) {
                System.out.println("Product saved with id " /*+ product.getId()*/);
            } else {
                System.out.println("Product save failed with id"/* + product.getId()*/);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProduct(int id) {
        String selectByIdQuery = "SELECT id, name, price, quantity FROM Products WHERE id = ?";

        ResultSet rs = null;
        Product product = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(selectByIdQuery)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setId(id);
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public boolean update(Product product) {
        String updateQuery = "UPDATE Products SET name=?, price=?, quantity=? WHERE id=?";

        int out = 0;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getId());

            out = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out != 0;
    }

    @Override
    public boolean delete(int id) {
        String insertQuery = "DELETE  FROM Products WHERE id=?";

        int out = 0;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {

            ps.setInt(1, id);
            out = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out != 0;
    }

    @Override
    public List<Product> getProducts() {
        String query = "SELECT id, name, price, quantity FROM Products";
        List<Product> productList = new ArrayList<>();

        ResultSet rs = null;
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}
