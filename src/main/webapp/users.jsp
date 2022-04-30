<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/30
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="users"/>
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
    <c:if test="${userList.size() eq 0}">
        <div class="w-64 mx-auto my-10">
            <h1 class="text-2xl"><fmt:message key="user_empty"/></h1>
        </div>
    </c:if>
    <button class="button is-success ml-5 mt-5">
        <a href="/user/regist.jsp"><fmt:message key="add_user"/></a>
    </button>
    <ul class="w-full my-5">
        <c:forEach var="user" items="${userList}">
            <li class="flex w-full px-5">
                <div class="w-full">
                    <div class="text-xl">
                        <span><fmt:message key="user_name"/> : ${user.getName()} </span>
                    </div>
                    <div class="text-xl">
                        <span><fmt:message key="id"/> : ${user.getId()}</span>
                    </div>
                </div>
                <div class="w-1/3 space-x-3 flex justify-end">
                    <div class="w-42">
                        <button type="submit" class="button is-success"><a
                                href="/user.nhn?id=${user.getId()}"><fmt:message key="modify_or_check"/></a></button>
                    </div>
                    <c:if test="${!fn:contains(user.getId(), \"admin\")}">
                        <form class="w-42" action="/user-delete.nhn" method="post">
                            <input type="hidden" name="id" value="${user.getId()}"/>
                            <button class="button is-danger submit" type="submit"><fmt:message key="delete"/></button>
                        </form>
                    </c:if>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
