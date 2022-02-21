<%--
  Created by IntelliJ IDEA.
  User: malak_000
  Date: 12/19/2021
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.webcustomertracker.sortUtilis" %>
<html>
<head>
    <title>customer list</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/styles.css"/>

</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">
            <input type="button" value="Add Customer" class="add-button"
                   onclick="window.location.href='showFromForCustomer'">
            <form:form method="get" action="search">
                Search Customer<input type="text" name="searchInput">
                <input type="submit" value="search" class="add-button">
            </form:form>
            <c:url var="sortFirtName" value="/customer/list">
                <c:param name="sort" value="<%= Integer.toString(sortUtilis.FIRST_NAME) %>"/>
            </c:url>
            <c:url  var="sortLastName" value="/customer/list">
                <c:param name="sort" value="<%=Integer.toString(sortUtilis.LAST_NAEM)%>"/>
            </c:url>
            <c:url  var="sortEmail" value="/customer/list">
                <c:param name="sort" value="<%=Integer.toString(sortUtilis.EMAIL)%>"/>
            </c:url>
            <table>
                <tr>
                    <th> <a href="${sortFirtName}">First Name</a> </th>
                    <th><a href="${sortLastName}">Last Name</a> </th>
                    <th><a href="${sortEmail}">Email</a> </th>
                    <th>Action</th>
                </tr>
                <c:forEach var="tempCustomer" items="${customers}">
                    <c:url  var="urlUpdate" value="/customer/updateForm">
                        <c:param name="customerID" value="${tempCustomer.id}"></c:param>
                    </c:url>
                    <c:url var="urlDelete" value="/customer/delete">
                        <c:param name="customerID" value="${tempCustomer.id}"></c:param>
                    </c:url>
                        <tr>
                            <td>${tempCustomer.firstName}</td>
                            <td>${tempCustomer.lastName}</td>
                            <td>${tempCustomer.email}</td>
                            <td><a href="${urlUpdate}">update</a> |
                                <a href="${urlDelete}" onclick="if (!(confirm('Are yor sure to delete ${tempCustomer.firstName}?')))return false;">delete</a>
                            </td    >
                        </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
<script>
    var  x= "${customers}";
    console.log(x);
</script>
</html>
