<%--    
    Author     : Julius Stassik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<my:pagetemplate title="Detail of reservation ${reservation.id}:">
    <jsp:attribute name="body">
        <h2>Customer:</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>First name</th>
                    <th>Surname</th>
                    <th>Username</th>
                    <th>Email</th>                    
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${reservation.customer.id}</td>
                    <td>${reservation.customer.firstName}</td>
                    <td>${reservation.customer.lastName}</td>
                    <td>${reservation.customer.username}</td>
                    <td>${reservation.customer.email}</td>                    
                </tr>
            </tbody>
        </table>
        <h2>Trip:</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Destination</th>
                    <th>Date from</th>
                    <th>Date to</th>
                    <th>Price</th>                    
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${reservation.trip.id}</td>
                    <td>${reservation.trip.destination}</td>
                    <td><fmt:formatDate value="${reservation.trip.dateFrom}" pattern="dd-MM-yyyy"/></td>
                    <td><fmt:formatDate value="${reservation.trip.dateTo}" pattern="dd-MM-yyyy"/></td>
                    <td><c:out value="${reservation.price}"/>&nbsp;CZK</td>                  
                </tr>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>
