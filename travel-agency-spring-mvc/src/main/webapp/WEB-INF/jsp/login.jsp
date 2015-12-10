<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Security Example</title>
    <style>
        body.security-app {
            text-align: center;
            overflow: hidden;
            height: 100%;
            width:100%;
            background:#edecec;
        }


        .lc-block {
            padding: 35px 55px 35px;
            background: #fff;
            box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
            border-radius: 2px;
            width: 30%;
            display: inline-block;
            position: relative;
            animation-name: fadeInUp;
            animation-duration: 300ms;
            animation-fill-mode: both;
            z-index: 10;
            margin-top:5%;
            vertical-align:middle;

        }

        body.security-app:before {
            height: 50%;
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
            background: #D40047;
            content: "";
            z-index: 0;
        }

        body.security-app:after {
            content: "";
            vertical-align: middle;
            display: inline-block;
            width: 1px;
            height: 100%;
        }

        h2 {
            color: white;
        }

        h1{
            color:crimson;
        }

        input[type="text"], input[type="password"]  {
            display: block;
            margin: 0;
            width: 100%;
            font-family: "Open Sans", sans-serif;
            font-size: 18px;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            -webkit-border-radius: none;
            -moz-border-radius: none;
            -ms-border-radius: none;
            -o-border-radius: none;
            border-radius: none;
            padding: 10px;
            border: none;
            border-bottom: solid 2px #c9c9c9;
            transition: border 0.3s;
        }

        .button{
            display:inline-block;
            text-transform:uppercase;
            padding:1em 1.5em 0.75em;
            border-radius:2px;
            font-weight:bold;
            border:none;color:#fff !important;
            text-decoration:none;
            font-family:'Open Sans Condensed',sans-serif;
            font-size:14px;
            line-height:1;
            cursor:pointer;
            margin-right:10px;
            margin-top:20px;
        }
        .button:hover{opacity:0.9}
        .button.green{
            background-color:#a5d16d;
            box-shadow:0 0 5px #a4cf6c inset, 0 1px 1px #eee
        }
        .button.red{
            background-color:#f4836a;
            box-shadow:0 0 5px #ef7f65 inset, 0 1px 1px #eee
        }
        .button.small{font-size:11px}
        .button.big{
            font-size:18px;padding:1.2em 2.5em 1em
        }
        .details{
            position: relative;
            text-align: center;
            width: 40%;
            margin-left: 30%;
            margin-right: 30%;
        }
        .alert-danger {
            padding: 15px;
            font-size: 16px;
            background-color: #f2dede;
            border-color: #f2dede;
            color: #f44336;
            margin: 18px;
            border: 1px solid transparent;
            border-radius: 2px;
        }
        .alert-normal{
            padding: 15px;
            font-size: 16px;
            margin: 18px;
            border: 1px solid transparent;
            border-radius: 2px;
            background-color: #d9edf7;
            border-color: #d9edf7;
            color: #2196f3;
        }
    </style>
    </head>
<body class="security-app">
<div class="details">
    <h2>Spring Security - JDBC Authentication</h2>
    <a href="http://www.programming-free.com/2015/09/spring-security-jdbc-authentication.html" class="button green small">Tutorial</a>
    <a href="https://github.com/priyadb/SpringSecurityJdbcApp/archive/master.zip"
       class="button red small">Download</a>
</div>

<form action="login" method="post">

    <div class="lc-block">
        <div>
            <input type="text" class="style-4" name="username"
                   placeholder="User Name" />
        </div>
        <div>
            <input type="password" class="style-4" name="password"
                   placeholder="Password" />
        </div>
        <div>
            <input type="submit" value="Sign In" class="button red small" />
        </div>
        <c:if test="${param.error ne null}">
            <div class="alert-danger">Invalid username and password.</div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="alert-normal">You have been logged out.</div>
        </c:if>
    </div>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

</body>
</html>