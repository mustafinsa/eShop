<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Item Cart</title>
</head>
<body>

<table align="right">
    <th width="30"><a href="/">Main</a></th>
    <th width="50%">Item cart</th>
    <th><a href="itemcart">In cart ${sessionScope.itemCart != null ? sessionScope.itemCart.size() : 0 } items</a></th>
    <th>Login</th>
</table>
<br/><br/>
<hr>

<table border="1px" align="center">
    <th>#</th> <th>Name</th> <th>Quantity</th><th>Delete</th>
    <c:forEach var="product" items="${requestScope.productMap}" varStatus="index">
        <tr>
            <td>${index.count}</td>
            <td>${product.key.name}</td>
            <td>${product.value}</td>
            <td height="15px">
                <form action="itemcart" method="post">
                    <input type="hidden" name="act" value="delete" />
                    <input type="hidden" name="deleteId" value="${index.count}" />
                    <input type="submit" value="Delete"/>
                </form> </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
