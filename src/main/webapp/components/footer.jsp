<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/05/01
  Time: 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:bundle basename="footer">
<footer class="flex space-x-5 fixed bottom-0">
    <div class="text-lg"><fmt:message key="now_visitor"/> : ${applicationScope.visitor}</div>
    <div class="text-lg"><fmt:message key="now_logged"/> : ${applicationScope.logged}</div>
</footer>
</fmt:bundle>
<script>
    const lang = document.querySelector(".lang")
    if(!Cookies.get('lang')){
        Cookies.set('lang', 'ko');
    }

    lang.addEventListener('click', (e) => {
        const cookie = Cookies.get("lang");
        if (cookie === 'en') {
            e.target.innerHTML = 'ko';
            Cookies.set('lang', 'ko');
        } else if (cookie === 'ko') {
            e.target.innerHTML = 'en';
            Cookies.set('lang', 'en');
        }
        location.reload();
    })
</script>
