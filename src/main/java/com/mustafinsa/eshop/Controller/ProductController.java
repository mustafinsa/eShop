package com.mustafinsa.eshop.Controller;

import com.mustafinsa.eshop.Model.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProductController extends HttpServlet{
    ProductDao productDao = new ProductDaoImpl(ConnectorDB.getDataSource());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Product product = productDao.getProduct(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }
}
