<%-- 
    Author     : Branislav Bohumel
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Excursion:">
<jsp:attribute name="body">

       <div class="thumbnail">
            <a href="${pageContext.request.contextPath}/excursion/${excursion.id}"><h2 style="margin-top: 10px">${excursion.id}.
                <c:out value="${excursion.destination}"/></h2></a>
            <div class="caption">  
                <span style="font-weight: bold;">Date: </span><fmt:formatDate value="${excursion.date}" pattern="dd-MM-yyyy"/><br>
                <span style="font-weight: bold;">Duration: </span><span style="color: black; font-weight: bold;"><c:out value="${excursion.duration.getSeconds()/3600}"/> hours</span><br>
                <span style="font-weight: bold;">Price: </span><span style="color: green; font-weight: bold;"><c:out value="${excursion.price} CZK"/></span><br>
            </div>
           <div class="row">
               <c:choose>
                   <c:when test="${empty excursion.trip}">
                       <div class="col-xs-12">
                           This excursion does not belong to any trip.
                       </div>
                   </c:when>
                   <c:otherwise>
                        <span class="col-xs-12">This excursion belongs to trip:
                            <a href="${pageContext.request.contextPath}/trip/${excursion.trip.id}">
                                <c:out value="${excursion.trip.destination} "/>
                            </a>
                        </span>
                   </c:otherwise>
               </c:choose>
           </div>
        </div>
        <a href="/pa165/"class="btn btn-primary">Back</a>

</jsp:attribute>
</my:pagetemplate>
