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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="palette/prettyTools.jsp"/>
    <title>유저 목록</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="w-screen">
    <c:if test="${userList.size() eq 0}">
        <div class="w-64 mx-auto my-10">
            <h1 class="text-2xl">유저가 존재하지않습니다.</h1>
        </div>
    </c:if>
    <button class="button is-success ml-5 mt-5">
        <a href="/user/regist.jsp">유저 추가하기</a>
    </button>
    <ul class="w-full my-5">
        <c:forEach var="user" items="${userList}">
            <li class="flex w-full px-5">
                <div class="w-full">
                    <div class="text-xl">
                        <span>유저명 : ${user.getName()} </span>
                    </div>
                    <div class="text-xl">
                        <span>아이디 : ${user.getId()}</span>
                    </div>
                </div>
                <div class="w-1/3 space-x-3 flex justify-end">
                    <div class="w-42">
                        <button type="submit" class="button is-success"><a
                                href="/user.nhn?id=${user.getId()}">정보확인/변경하기</a></button>
                    </div>
                    <c:if test="${!fn:contains(user.getId(), \"admin\")}">
                        <form class="w-42" action="/user-delete.nhn" method="post">
                            <input type="hidden" name="id" value="${user.getId()}"/>
                                <button class="button is-danger submit" type="submit">삭제</button>
                        </form>
                    </c:if>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
