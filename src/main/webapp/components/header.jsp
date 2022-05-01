<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/28
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:bundle basename="header">
<header class="shadow-lg shadow-green-200 shadow-green-200/50">
    <nav class="level px-10 py-5">
        <!-- Left side -->
        <div class="level-left">
            <div class="level-item cursor-pointer">
                <a href="/posts.nhn?pageNo=1&size=10"><img src="../imgs/main.svg" class="bg-no-repeat" alt="logo"></a>
            </div>
        </div>

        <!-- Right side -->
        <div class="level-right space-x-10">
            <p class="level-item lang"><a><fmt:message key="language"/></a></p>
            <c:if test="${sessionScope.get(\"id\").length() > 0}">
                <p class="level-item"><a class="button is-success" href="/logout.nhn"><strong>
                    <fmt:message key="logout"/>
                </strong></a></p>
            </c:if>
            <c:if test="${sessionScope.get(\"id\") eq \"admin\"}">
                <p class="level-item"><a class="button is-success" href="/users.nhn"><strong>
                    <fmt:message key="manage_user"/>
                </strong></a></p>
            </c:if>
            <c:if test="${sessionScope.get(\"id\") eq null}">
                <p class="level-item"><a class="button is-success" href="/loginForm.jsp"><strong>
                    <fmt:message key="login"/>
                </strong></a></p>
            </c:if>
        </div>
    </nav>
</header>
</fmt:bundle>
