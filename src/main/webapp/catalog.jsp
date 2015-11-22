<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>eShop Catalog</title>
</head>
<body>
<table width="100%" align="center">
    <th><a href="/">Main</a></th>
    <th>eShop Catalog</th>
    <th><a href="itemcart">In cart ${sessionScope.itemCart != null ? sessionScope.itemCart.size() : 0 } items</a></th>
    <th>Login</th>
</table>
<hr>
<br/><br/>
<table align="center" border="1px" cellspacing="0px" cellpadding="0px">
    <tr>
        <th width="40px">№</th>
        <th width="300px">Product</th>
        <th width="50px">Price</th>
        <th width="60px"></th>
    </tr>

    <c:forEach var="product" items="${requestScope.productList}" varStatus="index">
        <tr>
            <td><c:out value="${index.count}"/></td>
            <td><a href="product?id=${product.id}"><c:out value="${product.name}"/></a></td>
            <td><c:out value="${product.price}"/></td>
            <td>
                <form name="Cart" action="itemcart" method="post">
                    <input type="hidden" name="act" value="add"/>
                    <input type="hidden" name="itemId" value="${product.id}"/>
                    <input type="submit" value="Add to cart"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
