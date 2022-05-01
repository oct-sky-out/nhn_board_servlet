<%@ page import="com.nhnacademy.post.Page" %>
<%@ page import="com.nhnacademy.post.Post" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/28
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:bundle basename="index">
    <!DOCTYPE html>
    <html lang="${cookie.lang.value}">
    <head>
        <jsp:include page="palette/prettyTools.jsp"/>
        <title>
            <fmt:message key="index_title"/>
        </title>
    </head>
    <body>
    <jsp:include page="/components/header.jsp"/>
    <div class="flex justify-end mx-5 my-10">
        <button class="text-2xl button is-success"><a href="/post/create.jsp">
            <fmt:message key="write_post"/>
        </a></button>
    </div>
    <c:if test="${postList.getList().size() eq 0}">
        <div class="w-64 mx-auto my-10">
            <h1 class="text-2xl">
                <fmt:message key="post_empty"/>
            </h1>
        </div>
    </c:if>
    <ul class="w-full my-5">
        <c:forEach var="post" items="${postList.getList()}">
            <li class="flex w-full px-5">
                <div class="w-full">
                    <div class="text-4xl">
                        <a href="/post.nhn?id=${post.getId()}">${post.getTitle()} </a>
                    </div>
                    <div class="text-lg">
                        <span><fmt:message key="author"/> : ${post.getAuthor()}</span>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <nav class="fixed w-full bottom-20 px-20 pagination is-centered" role="navigation">
        <button <c:if test="${postList.getCurrentPageNumber() == 1}">disabled</c:if>>
            <a href="/posts.nhn?pageNo=${postList.getCurrentPageNumber() - 1}&size=10" class="pagination-previous">
                <fmt:message key="previous_page"/>
            </a>
        </button>
        <c:if test="${postList.getTotalPageCount() != 0}">
            ${postList.getCurrentPageNumber()} / ${postList.getTotalPageCount()}
        </c:if>
        <button
                <% Page<Post> pagenation = (Page<Post>)request.getAttribute("postList");
                    if(pagenation.getTotalPageCount() == 0
                            || pagenation.getCurrentPageNumber() == pagenation.getTotalPageCount()
                    ){%>
                    disabled
               <%  } %>
        >
            <a href="/posts.nhn?pageNo=${postList.getCurrentPageNumber() + 1}&size=10" class="pagination-next">
                <fmt:message key="next_page"/>
            </a>
        </button>
    </nav>
    <jsp:include page="components/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>
