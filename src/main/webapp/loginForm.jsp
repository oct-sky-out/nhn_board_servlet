<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/30
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:bundle basename="loginForm">
<!DOCTYPE html>
<html lang="${cookie.lang.value}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="palette/prettyTools.jsp"/>
    <title>
        <fmt:message key="login"/>
    </title>
</head>
<body>
<div class="w-screen">
    <div class="w-64 mx-auto my-20">
        <form action="/login.nhn" method="post">
            <img src="/imgs/main.svg" class="bg-no-repeat" alt="logo">
            <h1 class="text-center text-2xl">
                <fmt:message key="login"/>
            </h1>
            <div class="field">
                <input class="input is-rounded" type="text" placeholder="<fmt:message key="id"/>"
                       name="id">
            </div>
            <div class="field">
                <input class="input is-rounded" type="password" placeholder="<fmt:message key="password"/>" name="password">
            </div>
            <div class="flex flex-row-reverse">
                <input class="button is-success" type="submit" value="<fmt:message key="login"/>">
            </div>
        </form>
    </div>
</div>
</body>
</html>
</fmt:bundle>
