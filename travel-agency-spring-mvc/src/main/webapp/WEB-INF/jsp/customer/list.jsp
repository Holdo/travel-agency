<%-- 
    Document   : customers
    Created on : Dec 17, 2015, 11:03:34 PM
    Author     : Diana Vilkolakova
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Customers">
<jsp:attribute name="body">

    <table class="table">
        <caption>Customers</caption>
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>            
            <th>email</th>
            <th>First name</th>
            <th>Surname</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td><c:out value="${customer.username}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.firstName}"/></td>
                <td><c:out value="${customer.lastName}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>