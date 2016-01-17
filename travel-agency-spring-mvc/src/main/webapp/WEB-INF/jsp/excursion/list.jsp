<%-- 
    Author     : Branislav Bohumel
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Excursion List:">
<jsp:attribute name="body">

    <a href="/pa165/excursion/edit/${0}" class="btn btn-primary">Create</a><br>
    <c:forEach items="${excursions}" var="excursion" varStatus="ic">
        <div class="thumbnail">
            <a href="${pageContext.request.contextPath}/excursion/edit/${excursion.id}"><h2 style="margin-top: 10px">${excursion.id}.
                <c:out value="${excursion.destination}"/></h2></a>
            <div class="caption">
                <span style="font-weight: bold;">Date: </span><fmt:formatDate value="${excursion.date}" pattern="dd-MM-yyyy"/><br>
                <span style="font-weight: bold;">Duration: </span><span style="color: black; font-weight: bold;"><c:out value="${excursion.duration.getSeconds()/3600}"/> hours</span><br>
                <span style="font-weight: bold;">Price: </span><span style="color: green; font-weight: bold;"><c:out value="${excursion.price} CZK"/></span><br>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/excursion/delete/${excursion.id}">
                <my:a href="/excursion/edit/${excursion.id}" class="btn btn-primary">Edit</my:a>
                <button type="submit" class="btn btn-danger"> Delete
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </button>
            </form>
        </div>
    </c:forEach>

</jsp:attribute>
</my:pagetemplate>
