package com.mustafinsa.eshop.Controller;

import com.mustafinsa.eshop.Model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductAllController extends HttpServlet {
    ProductDao productDao = new ProductDaoImpl(ConnectorDB.getDataSource());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = new ArrayList<>();

        productList = productDao.getProducts();

        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }
}
