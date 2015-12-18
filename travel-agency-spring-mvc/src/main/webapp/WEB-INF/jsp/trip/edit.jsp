<%--Author: Michal Holic--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New trip">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/trip/edit/${id}"
               modelAttribute="tripEdit" cssClass="form-horizontal">
        <div class="form-group ${id_error?'has-error':''}">
            <form:label path="id" cssClass="col-sm-2 control-label">Id</form:label>
            <div class="col-sm-10">
                <form:input path="id" cssClass="form-control" placeholder="long" value="${id}" disabled="true" />
                <form:errors path="id" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${destination_error?'has-error':''}">
            <form:label path="destination" cssClass="col-sm-2 control-label">Destination</form:label>
            <div class="col-sm-10">
                <form:input path="destination" cssClass="form-control" placeholder="string" value="${destination}" />
                <form:errors path="destination" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${dateFrom_error?'has-error':''}">
            <form:label path="dateFrom" cssClass="col-sm-2 control-label">Date from</form:label>
            <div class="col-sm-10">
                <form:input path="dateFrom" cssClass="form-control" placeholder="YYYY-MM-DD" value="${dateFrom}" />
                <form:errors path="dateFrom" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${dateTo_error?'has-error':''}">
            <form:label path="dateTo" cssClass="col-sm-2 control-label">Date to</form:label>
            <div class="col-sm-10">
                <form:input path="dateTo" cssClass="form-control" placeholder="YYYY-MM-DD" value="${dateTo}" />
                <form:errors path="dateTo" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${numberOfAvailable_error?'has-error':''}">
            <form:label path="numberOfAvailable" cssClass="col-sm-2 control-label">Number of Available</form:label>
            <div class="col-sm-10">
                <form:input path="numberOfAvailable" cssClass="form-control" placeholder="integer" value="${numberOfAvailable}" />
                <form:errors path="numberOfAvailable" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${price_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
            <div class="col-sm-10">
                <form:input path="price" cssClass="form-control" placeholder="double" value="${price}" />
                <form:errors path="price" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Update trip</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</jsp:attribute>
</my:pagetemplate>