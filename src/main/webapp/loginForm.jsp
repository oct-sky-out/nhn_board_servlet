<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/30
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="palette/prettyTools.jsp"/>
    <title>로그인</title>
</head>
<body>
<div class="w-screen">
    <div class="w-64 mx-auto my-20">
        <form action="/login.nhn" method="post">
            <img src="/imgs/main.svg" class="bg-no-repeat" alt="logo">
            <h1 class="text-center text-2xl">로그인</h1>
            <div class="field">
                <input class="input is-rounded" type="text" placeholder="아이디"
                       name="id">
            </div>
            <div class="field">
                <input class="input is-rounded" type="password" placeholder="Password" name="password">
            </div>
            <div class="flex flex-row-reverse">
                <input class="button is-success" type="submit" value="로그인">
            </div>
        </form>
    </div>
</div>
</body>
</html>
