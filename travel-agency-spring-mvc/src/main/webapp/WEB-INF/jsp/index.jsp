<%--Author: Michal Holic--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Welcome! List of Trips:">
<jsp:attribute name="body">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2>Admin mode initiated</h2>
    </sec:authorize>


    <c:forEach items="${trips}" var="trip" varStatus="ic">
        <%--<div class="col-xs-12 col-sm-4 col-md-3 col-lg-2">--%>
        <div class="thumbnail">
            <a href="${pageContext.request.contextPath}/trip/${trip.id}"><h2 style="margin-top: 10px">${ic.count}.
                <c:out value="${trip.destination}"/></h2></a>
            <div class="caption">
                <span style="font-weight: bold;">Dates: </span>from <fmt:formatDate value="${trip.dateFrom}" pattern="dd-MM-yyyy"/> to <fmt:formatDate value="${trip.dateTo}" pattern="dd-MM-yyyy"/><br>
                <span style="font-weight: bold;">Price: </span><span style="color: green; font-weight: bold;"><c:out value="${trip.price} CZK"/></span><br>
                <span style="font-weight: bold;">Available: </span><span style="color: red; font-weight: bold;"><c:out value="${trip.numberOfAvailable}"/></span><br>
            <div class="row">
                <c:choose>
                    <c:when test="${empty trip2excursions[trip.id]}">
                        <div class="col-xs-12">
                            This trip has no excursions.
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${trip2excursions[trip.id]}" var="excursion">
                        <span class="col-xs-12">Possible excursion:
                            <a href="${pageContext.request.contextPath}/excursion/${excursion.id}">
                                <c:out value="${excursion.destination} "/>
                            </a>
                            <%--<span style="color: red; font-weight: bold;"><c:out value="${excursion.price} CZK"/></span>--%>
                        </span>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
            </div>
        </div>
        <%--</div>--%>
    </c:forEach>

</jsp:attribute>
</my:pagetemplate>