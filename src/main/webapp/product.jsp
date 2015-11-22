<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product <c:out value="${product.name}"/></title>
</head>
<body>

<table width="100%" align="center">
    <th><a href="/">Main</a></th>
    <th><c:out value="${requestScope.product.name}"/></th>
    <th><a href="itemcart">In cart ${sessionScope.itemCart != null ? sessionScope.itemCart.size() : 0 } items</a></th>
    <th>Login</th>
</table>
<hr>
<br/><br/>
<table align="center" border="1px" cellspacing="0px" cellpadding="0px">
    <tr>
        <th width="100px">Name</th>
        <th width="100px">Items in stock</th>
        <th width="50px">Price</th>
        <th width="50px"></th>
    </tr>
    <tr>
        <td><c:out value="${requestScope.product.name}"/></td>
        <td><c:out value="${requestScope.product.quantity}"/></td>
        <td><c:out value="${requestScope.product.price}"/></td>
        <td>
            <form name="Cart" action="itemcart" method="post">
                <input type="hidden" name="act" value="add"/>
                <input type="hidden" name="itemId" value="${product.id}"/>
                <input type="submit" value="Add to cart"/>
            </form>
        </td>
    </tr>
</table>

</body>
</html>
