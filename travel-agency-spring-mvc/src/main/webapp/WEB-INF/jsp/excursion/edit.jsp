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
    
    <c:choose>
    <c:when test="${excursionEdit != null}">
        <form:form method="post" action="${pageContext.request.contextPath}/excursion/update/${id}"
                   modelAttribute="excursionEdit" cssClass="form-horizontal">
            <div class="form-group ${destination_error?'has-error':''}">
                <form:label path="destination" cssClass="col-sm-2 control-label">Destination: </form:label>
                <div class="col-sm-10">
                    <form:input path="destination" cssClass="form-control" value="${excursionEdit.destination}" placeholder="string"/>
                    <form:errors path="destination" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${date_error?'has-error':''}">
                <form:label path="date" cssClass="col-sm-2 control-label">Date: </form:label>
                <div class="col-sm-10">
                    <form:input path="date" cssClass="form-control" value="${excursionEdit.date}" placeholder="YYYY-MM-DD"/>
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
                    <form:input path="price" cssClass="form-control" value="${excursionEdit.price}" placeholder="double (CZK)"/>
                    <form:errors path="price" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Update</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>
        <form method="post" action="${pageContext.request.contextPath}/excursion/delete/${excursionEdit.id}">
            <button type="submit" class="btn btn-danger"> Delete
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
    <a href="/pa165/excursion/list"class="btn btn-primary">Back</a>

</jsp:attribute>
</my:pagetemplate>
