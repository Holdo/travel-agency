<%--         
    Author     : Julius Stassik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<my:pagetemplate title="List of Reservations:">
    <jsp:attribute name="body">
        
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Surname</th>
                    <th>Destination</th>
                    <th>Date from</th>
                    <th>Date to</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${reservations}" var="res">
                    <tr>
                        <td><c:out value="${res.id}"/></td>
                        <td><c:out value="${res.customer.lastName}"/></td>
                        <td><c:out value="${res.trip.destination}"/></td>
                        <td><fmt:formatDate value="${res.trip.dateFrom}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${res.trip.dateTo}" pattern="dd-MM-yyyy"/></td>
                        <td><c:out value="${res.price}"/>&nbsp;CZK</td>
                        <td>
                            <my:a href="/reservation/delete/${res.id}" class="btn btn-primary"><span style="color: white;" class="glyphicon glyphicon-remove" aria-hidden="true"></span></my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    
    
    </jsp:attribute>
</my:pagetemplate>
