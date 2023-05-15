<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Insert Product</title>
    </head>
    <body>
        <c:out value="${result.message}"/>
        <br>
        <c:out value="${result.detailedMessage}"/>
        <br><br>
        <form:form action="/product/insert" method="post" modelAttribute="product">
            <form:label path="sku">Sku:</form:label><br>
            <form:input path="sku" type="text"/><br><br>

            <form:label path="name">Name:</form:label><br>
            <form:input path="name" type="text"/><br><br>

            <form:label path="description">Description:</form:label><br>
            <form:input path="description" type="text"/><br><br>

            <input type="submit" value="Save"/>
        </form:form>
        <br>
        <a href="/product/list">
            <button>List Products</button>
        </a>
    </body>
</html>