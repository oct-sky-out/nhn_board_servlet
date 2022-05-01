<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/29
  Time: 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:bundle basename="userRegist">
<!DOCTYPE html>
<html lang="${cookie.lang.value}}">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../palette/prettyTools.jsp"/>
    <title><fmt:message key="regist_title"/></title>
</head>
<body>
<div class="w-screen">
    <form action="/profile.nhn" method="post" enctype="multipart/form-data" class="uploader">
        <div class="w-64 mx-auto my-10 space-y-10">
            <div class="text-xl">
                <div class="is-boxed">
                    <label>
                        <fmt:message key="id"/>
                        <input type="text" name="id">
                    </label>
                </div>
                <div class="is-boxed">
                    <label>
                        <fmt:message key="user_name"/>
                        <input type="text" name="name">
                    </label>
                </div>
                <div class="is-boxed">
                    <label>
                        <fmt:message key="password"/>
                        <input type="password" name="password">
                    </label>
                </div>
                <div class="file is-centered is-boxed is-success has-name flex-wrap">
                    <div>
                        <label class="file-label">
                            <input class="file-input" type="file" name="image"
                                   accept="image/*">
                            <span class="file-cta">
                                <span class="file-label">
                                    <fmt:message key="file_title"/>
                                </span>
                            </span>
                            <span class="file-name text-center"><fmt:message key="file_info"/></span>
                        </label>
                    </div>
                    <div class="w-full mt-2 file-label">
                        <input type="submit" class="button is-success" value="<fmt:message key="register"/>">
                    </div>
                </div>
            </div>
        </div>
    </form>
    <jsp:include page="../components/footer.jsp"/>
</div>
<script src="upload.js"></script>
</body>
</html>
</fmt:bundle>
