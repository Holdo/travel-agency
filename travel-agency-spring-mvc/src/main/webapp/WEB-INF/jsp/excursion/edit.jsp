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
    }
    .button:hover{opacity:0.9}
    .button.blue{
        background-color:#6666ff;
        box-shadow:0 0 5px #8080ff inset, 0 1px 1px #eee            
    }
    .button.red{
        background-color:#ff6666;
        box-shadow:0 0 5px #ff8080 inset, 0 1px 1px #eee            
    }
    .button.darkred{
        background-color:#ff1a1a;
        box-shadow:0 0 5px #ff3333 inset, 0 1px 1px #eee            
    }
</style>

<my:pagetemplate title="Excursion:">
<jsp:attribute name="body">
    
    <c:choose>
    <c:when test="${excursion != null}">
        <form:form method="post" action="${pageContext.request.contextPath}/excursion/update/${id}"
                   modelAttribute="excursion" cssClass="form-horizontal">
            <div class="form-group ${destination_error?'has-error':''}">
                <form:label path="destination" cssClass="col-sm-2 control-label">Destination: </form:label>
                <div class="col-sm-10">
                    <form:input path="destination" cssClass="form-control" placeholder="${excursion.destination}"/>
                    <form:errors path="destination" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${date_error?'has-error':''}">
                <form:label path="date" cssClass="col-sm-2 control-label">Date: </form:label>
                <div class="col-sm-10">
                    <form:input path="date" cssClass="form-control" placeholder="${excursion.date}"/>
                    <form:errors path="date" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${duration_error?'has-error':''}">
                <form:label path="duration" cssClass="col-sm-2 control-label">Duration: </form:label>
                <div class="col-sm-10">
                    <form:input path="duration" cssClass="form-control" placeholder="${excursion.duration}"/>
                    <form:errors path="duration" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${price_error?'has-error':''}">
                <form:label path="price" cssClass="col-sm-2 control-label">Price: </form:label>
                <div class="col-sm-10">
                    <form:input path="price" cssClass="form-control" placeholder="${excursion.price}"/>
                    <form:errors path="price" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Update</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>
        <form method="post" action="${pageContext.request.contextPath}/excursion/delete/${excursion.id}">
            <button type="submit" class="button darkred"> Delete
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </button>
        </form>
    </c:when>    
    <c:otherwise>
    <form:form method="post" action="${pageContext.request.contextPath}/excursion/new"
           modelAttribute="excursionCreate" cssClass="form-horizontal">
        <div class="form-group ${destination_error?'has-error':''}">
            <form:label path="destination" cssClass="col-sm-2 control-label">Destination: </form:label>
            <div class="col-sm-10">
                <form:input path="destination" cssClass="form-control" placeholder="string"/>
                <form:errors path="destination" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${date_error?'has-error':''}">
            <form:label path="date" cssClass="col-sm-2 control-label">Date: </form:label>
            <div class="col-sm-10">
                <form:input path="date" cssClass="form-control" placeholder="YYYY-MM-DD"/>
                <form:errors path="date" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${duration_error?'has-error':''}">
            <form:label path="duration" cssClass="col-sm-2 control-label">Duration: </form:label>
            <div class="col-sm-10">
                <form:input path="duration" cssClass="form-control" placeholder="integer (hours)"/>
                <form:errors path="duration" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${price_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label">Price: </form:label>
            <div class="col-sm-10">
                <form:input path="price" cssClass="form-control" placeholder="double (CZK)"/>
                <form:errors path="price" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Create</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
    </c:otherwise>
    </c:choose>
    <a href="/pa165/excursion/list"class="button blue">Back</a>

</jsp:attribute>
</my:pagetemplate>
