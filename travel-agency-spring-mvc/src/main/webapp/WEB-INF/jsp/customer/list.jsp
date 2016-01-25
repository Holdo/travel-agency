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
        <a href="/pa165/customer/edit/${0}" class="btn btn-primary">Create</a><br>
        <table class="table">
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
                        <td><c:out value="${customer.id}"/></td>
                        <td><c:out value="${customer.username}"/></td>
                        <td><c:out value="${customer.email}"/></td>
                        <td><c:out value="${customer.firstName}"/></td>
                        <td><c:out value="${customer.lastName}"/></td>
                        <td><my:a href="/customer/edit/${customer.id}" class="btn btn-primary">Edit</my:a></td>
                            <td>                    
                                <form method="post" action="${pageContext.request.contextPath}/customer/delete/${customer.id}">                
                                <button type="submit" class="btn btn-danger"> Delete
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>