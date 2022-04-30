<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/05/01
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="palette/prettyTools.jsp"/>
    <title>${post.getTitle()}</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="w-screen">
    <div class="w-2/3 mx-auto my-10">
        <div class="flex w-full">
            <h1 class="w-full title is-2">${post.getTitle()}</h1>
            <div class="w-64">
                <h3><a href="/user.nhn?id=${post.getAuthor()}">작성자 : ${post.getAuthor()}</a></h3>
                <h3><javatime:format value="${post.getWriteTime()}" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
                <c:if test="${sessionScope.get(\"id\") eq post.getAuthor()}">
                    <div class="flex justify-center space-x-3">
                        <a href="/post.nhn?id=${post.getId()}&modify=true">
                            <button class="button is-success">수정</button>
                        </a>
                        <form action="/post-delete.nhn" method="post">
                            <input type="hidden" name="id" value="${post.getId()}">
                            <input class="button is-success" type="submit" value="삭제">
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
        <p class="text-xl">${post.getContent()}</p>
        <div class="is-boxed text-xl flex justify-end">
            <a href="/posts.nhn">
                <button class="button is-success">뒤로가기</button>
            </a>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
</body>
</html>
