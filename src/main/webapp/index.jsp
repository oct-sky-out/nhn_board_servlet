<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/28
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <jsp:include page="palette/prettyTools.jsp"/>
        <title>게시글 | 메인</title>
    </head>
    <body>
        <jsp:include page="components/header.jsp"/>
        <div class="flex justify-end mx-5 my-10">
            <button class="text-2xl button is-success"><a href="/post/create.jsp">게시글 작성</a></button>
        </div>
        <c:if test="${postList.size() eq 0}">
            <div class="w-64 mx-auto my-10">
                <h1 class="text-2xl">게시글이 존재하지않습니다.</h1>
            </div>
        </c:if>
        <ul class="w-full my-5">
            <c:forEach var="post" items="${postList}">
                <li class="flex w-full px-5">
                    <div class="w-full">
                        <div class="text-4xl">
                            <a href="/post.nhn?id=${post.getId()}">${post.getTitle()} </a>
                        </div>
                        <div class="text-lg">
                            <span>작성자 아이디 : ${post.getAuthor()}</span>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <jsp:include page="components/footer.jsp"/>
    </body>
</html>
