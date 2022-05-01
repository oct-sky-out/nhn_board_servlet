<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/28
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page isErrorPage="true" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:bundle basename="error">
    <!DOCTYPE html>
    <html lang="${cookie.lang.value}">
    <head>
        <jsp:include page="palette/prettyTools.jsp"/>
        <title><fmt:message key="error"/></title>
    </head>
    <body>
    <div class="w-full">
        <div class="w-2/3 mx-auto my-10">
            <div>
                <h1 class="title is-1"><fmt:message key="error_title"/> :</h1>
                <span class="text-xl">
                        ${requestScope.erorrMsg}
                </span>
            </div>
            <div class="flex justify-end">
                <a href="/posts.nhn">
                    <button class="button is-success"><fmt:message key="main_page"/></button>
                </a>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
