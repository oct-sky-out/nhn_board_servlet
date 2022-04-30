<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/28
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<header class="shadow-lg shadow-green-200 shadow-green-200/50">
    <nav class="level px-10 py-5">
        <!-- Left side -->
        <div class="level-left">
            <div class="level-item cursor-pointer">
                <a href="/posts.nhn"><img src="../imgs/main.svg" class="bg-no-repeat" alt="logo"></a>
            </div>
        </div>

        <!-- Right side -->
        <div class="level-right space-x-10">
            <p class="level-item"><a>한국어</a></p>
            <%
                HttpSession userSession = request.getSession(false);
                if(Objects.equals(userSession.getAttribute("id"), "admin")) {%>
             <p class="level-item"><a class="button is-success" href="/users.nhn"><strong>유저 관리</strong></a></p>
            <%} else {%>
            <p class="level-item"><a class="button is-success" href="/loginForm.jsp"><strong>로그인</strong></a></p>
            <%}%>
        </div>
    </nav>
</header>
