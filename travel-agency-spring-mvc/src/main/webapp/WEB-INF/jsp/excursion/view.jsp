<%-- 
    Author     : Branislav Bohumel
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .button{
        display:inline-block;
        text-transform:uppercase;
        padding:1em 1.5em 0.75em;
        border-radius:2px;
        font-weight:bold;
        border:none;color:#fff !important;
        text-decoration:none;
        font-family:'Open Sans Condensed',sans-serif;
        font-size:11px;
        line-height:1;
        cursor:pointer;
        margin-right:10px;
        margin-top:20px;
        background-color:#6666ff;
        box-shadow:0 0 5px #8080ff inset, 0 1px 1px #eee  
    }
    .button:hover{opacity:0.9}
</style>

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
        
        </div>
        <a href="/pa165/"class="button blue">Back</a>

</jsp:attribute>
</my:pagetemplate>
