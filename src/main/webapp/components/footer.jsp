<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/05/01
  Time: 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="flex space-x-5 fixed bottom-0">
    <div class="text-lg">현재 접속한 방문자 수 :${applicationScope.visitor}</div>
    <div class="text-lg">현재 로그인한 사용자 수 : ${applicationScope.logged}</div>
</footer>
