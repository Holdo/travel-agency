<%-- 
    Document   : trip
    Created on : Dec 18, 2015, 2:09:36 PM
    Author     : Diana Vilkolakova
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<fmt:message var="title" key="trip.destination"><fmt:param value="${trip.destination}"/></fmt:message>
<my:pagetemplate title="${trip.destination}">
<jsp:attribute name="body">

    <div class="row">
        <div class="col-xs-6">
            <h3><c:out value="${trip.destination}"/></h3>
            Current price: <span style="color: red; font-weight: bold;">${trip.price}</span><br>
            Start date: <span style="color: black; font-weight: bold;"><fmt:formatDate value="${trip.dateFrom}" pattern="yyyy-MM-dd"/>;<br>
            End date: <span style="color: black; font-weight: bold;"><fmt:formatDate value="${trip.dateTo}" pattern="yyyy-MM-dd"/>;<br>
            Current number of available: <span style="color: red; font-weight: bold;"><c:out value="${trip.numberOfAvailable}"/>;</span><br>
        </div>
    </div>
</jsp:attribute>
</my:pagetemplate>