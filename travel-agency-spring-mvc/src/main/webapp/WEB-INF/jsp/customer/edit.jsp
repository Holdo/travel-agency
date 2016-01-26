<%-- 
    Document   : edit
    Created on : Jan 25, 2016, 9:07:07 AM
    Author     : Skylar
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Customer:">
    <jsp:attribute name="body">

        <c:choose>
            <c:when test="${customerEdit != null}">
                <form:form method="post" action="${pageContext.request.contextPath}/customer/update/${id}"
                           modelAttribute="customerEdit" cssClass="form-horizontal">            
                    <div class="form-group ${username_error?'has-error':''}">
                        <form:label path="username" cssClass="col-sm-2 control-label">Username: </form:label>
                            <div class="col-sm-10">
                            <form:input path="username" cssClass="form-control" placeholder="string"/>
                            <form:errors path="username" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <div class="form-group ${email_error?'has-error':''}">
                        <form:label path="email" cssClass="col-sm-2 control-label">Email: </form:label>
                            <div class="col-sm-10">
                            <form:input path="email" cssClass="form-control" value="${customerEdit.email}" placeholder="string"/>
                            <form:errors path="email" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="form-group ${firstName_error?'has-error':''}">
                        <form:label path="firstName" cssClass="col-sm-2 control-label">First name: </form:label>
                            <div class="col-sm-10">
                            <form:input path="firstName" cssClass="form-control" placeholder="string"/>
                            <form:errors path="firstName" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <div class="form-group ${lastName_error?'has-error':''}">
                        <form:label path="lastName" cssClass="col-sm-2 control-label">Last name: </form:label>
                            <div class="col-sm-10">
                            <form:input path="lastName" cssClass="form-control" placeholder="string"/>
                            <form:errors path="lastName" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <div>
                        <form:hidden path="password" />
                        <form:hidden path="role" />
                    </div>
                    <button class="btn btn-primary" type="submit">Update</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form:form>
                <form method="post" action="${pageContext.request.contextPath}/customer/delete/${customerEdit.id}">
                    <button type="submit" class="btn btn-danger"> Delete
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </button>
                </form>
            </c:when>    
            <c:otherwise>
                <form:form method="post" action="${pageContext.request.contextPath}/customer/new"
                           modelAttribute="customerCreate" cssClass="form-horizontal">

                    <div class="form-group ${username_error?'has-error':''}">
                        <form:label path="username" cssClass="col-sm-2 control-label">Username: </form:label>
                            <div class="col-sm-10">
                            <form:input path="username" cssClass="form-control" placeholder="string"/>
                            <form:errors path="username" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="form-group ${email_error?'has-error':''}">
                        <form:label path="email" cssClass="col-sm-2 control-label">Email: </form:label>
                            <div class="col-sm-10">
                            <form:input path="email" cssClass="form-control" placeholder="string"/>
                            <form:errors path="email" cssClass="help-block"/>
                        </div>
                    </div>
                    <div class="form-group ${firstName_error?'has-error':''}">
                        <form:label path="firstName" cssClass="col-sm-2 control-label">First name: </form:label>
                            <div class="col-sm-10">
                            <form:input path="firstName" cssClass="form-control" placeholder="string"/>
                            <form:errors path="firstName" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <div class="form-group ${lastName_error?'has-error':''}">
                        <form:label path="lastName" cssClass="col-sm-2 control-label">Last name: </form:label>
                            <div class="col-sm-10">
                            <form:input path="lastName" cssClass="form-control" placeholder="string"/>
                            <form:errors path="lastName" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <div class="form-group ${password_error?'has-error':''}">
                        <form:label path="password" cssClass="col-sm-2 control-label">Password: </form:label>
                            <div class="col-sm-10">
                            <form:input path="password" cssClass="form-control" placeholder="string"/>
                            <form:errors path="password" cssClass="help-block" placeholder="string"/>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">Create</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form:form>
            </c:otherwise>
        </c:choose>
        <a href="/pa165/customer/list"class="btn btn-primary">Back</a>

    </jsp:attribute>
</my:pagetemplate>

