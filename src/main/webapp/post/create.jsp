<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/05/01
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:bundle basename="postCreate">
<!DOCTYPE html>
<html lang="${cookie.lang.value}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../palette/prettyTools.jsp"/>
    <title><fmt:message key="post_write"/></title>
</head>
<body>
<jsp:include page="../components/header.jsp"/>
<div class="w-screen">
    <form action="/post.nhn" method="post">
        <div class="w-1/2 mx-auto my-10 space-y-10">
            <div class="text-xl w-full space-y-10">
                <div class="is-boxed text-2xl">
                    <label class="w-full">
                        <fmt:message key="title"/>
                        <input class="w-full input is-success" type="text"
                               placeholder="<fmt:message key="title_placeholder"/>" name="title">
                    </label>
                </div>
                <div class="is-boxed text-xl">
                    <label>
                        <fmt:message key="content"/>
                        <textarea name="content" class="resize-none textarea is-success"
                                  placeholder="<fmt:message key="content_placeholder"/>"></textarea>
                    </label>
                </div>
                <div class="is-boxed text-xl flex justify-end">
                    <input class="button is-success" type="submit" value="<fmt:message key="create"/>">
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../components/footer.jsp"/>
</body>
</html>
</fmt:bundle>
