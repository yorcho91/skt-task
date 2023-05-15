<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>List Products</title>
    </head>
    <body>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Sku</th>
                    <th>Name</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td><c:out value="${product.id}"/></td>
                        <td><c:out value="${product.sku}"/></td>
                        <td><c:out value="${product.name}"/></td>
                        <td><c:out value="${product.description}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <a href="/product/insert">
            <button>Create Product</button>
        </a>
    </body>
</html>