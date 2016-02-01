package com.mustafinsa.eshop.Model;

import java.util.List;

public interface ProductDao {

    void save(Product product);

    Product getProduct(int id);

    boolean update(Product product);

    boolean delete(int id);

    List<Product> getProducts();
}
