package com.mustafinsa.eshop.Controller;

import com.mustafinsa.eshop.Model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ItemCartController extends HttpServlet {
    ProductDAO productDao = new ProductDAOSpringJdbcImpl(ConnectorDB.getDataSource());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // map для хранения корзины порядковый номер - количество
        Map<Integer, Integer> shoppingCart = (LinkedHashMap<Integer, Integer>) session.getAttribute("itemCart");
        if (shoppingCart == null) {
            shoppingCart = new LinkedHashMap<>();
            session.setAttribute("itemCart", shoppingCart);
        }
        String action = req.getParameter("act");
        if ("add".equals(action)) {
            int itemId = Integer.parseInt(req.getParameter("itemId"));

            if (shoppingCart.containsKey(itemId)) {
                shoppingCart.put(itemId, shoppingCart.get(itemId) + 1);
            } else {
                shoppingCart.put(itemId, 1);
            }
        } else if ("delete".equals(action)){
            int itemId = Integer.parseInt(req.getParameter("deleteId"));

            if (shoppingCart.get(itemId) > 1) {
                shoppingCart.put(itemId, shoppingCart.get(itemId) - 1);
            } else {
                shoppingCart.remove(itemId);
            }
        }
        session.setAttribute("itemCart", shoppingCart);

        Map<Product, Integer> productMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> item : shoppingCart.entrySet()) {
            productMap.put(productDao.getById(item.getKey()), item.getValue());
        }

        req.setAttribute("productMap", productMap);

        req.getRequestDispatcher("cart.jsp").forward(req, resp);


    }
}
