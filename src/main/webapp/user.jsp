<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/30
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="user">
<!DOCTYPE html>
<html lang="${cookie.lang.value}">
<fmt:setLocale value="${cookie.lang.value}" />
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="palette/prettyTools.jsp"/>
    <title>
        <fmt:message key="user_list"/>
    </title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="w-screen">
    <div class="w-screen">
        <div class="w-64 mx-auto my-10 space-y-10">
            <img src="/profile?image=${user.getProfileName()}" alt="userImage">
            <div class="text-2xl">
                <fmt:message key="id"/> : ${user.getId()}
            </div>
            <div class="text-2xl">
                <fmt:message key="name"/> : ${user.getName()}
            </div>
            <c:if test="${isAdmin eq true}">
            <form action="/user-modify.nhn" method="post" class="space-y-5">
                <input type="hidden" name="id" value="${user.getId()}">
                <div class="text-2xl">
                    <span>
                        <fmt:message key="change_name"/> :
                    </span>
                    <input class="input is-success" type="text" name="name">
                </div>
                <div class="text-2xl">
                    <fmt:message key="change_password"/> :
                    <input class="input is-success" type="pasword" name="password">
                </div>
                </c:if>
                <div>
                    <c:if test="${isAdmin eq true}">
                        <fmt:message key="change"/>
                        <button type="submit" class="button is-success">
                            정보 변경하기
                        </button>
                    </c:if>
                    <button type="submit" class="button is-success">
                        <a href="/users.nhn"><fmt:message key="go_back"/></a>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
</fmt:bundle>
