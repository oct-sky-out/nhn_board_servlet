<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/05/01
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../palette/prettyTools.jsp"/>
    <title>게시글 수정</title>
</head>
<body>
<jsp:include page="../components/header.jsp"/>
<div class="w-screen">
    <form action="/post-modify.nhn" method="post">
        <input type="hidden" name="id" value="${post.getId()}">
        <div class="w-1/2 mx-auto my-10 space-y-10">
            <div class="text-xl w-full space-y-10">
                <div class="is-boxed text-2xl">
                    <label class="w-full">
                        제목
                        <input class="w-full input is-success" type="text"
                               placeholder="제목을 입력하세요." name="title" value="${post.getTitle()}">
                    </label>
                </div>
                <div class="is-boxed text-xl">
                    <label>
                        내용
                        <textarea name="content" class="resize-none textarea is-success"
                                  placeholder="내용을 입력하세요">${post.getContent()}</textarea>
                    </label>
                </div>
                <div class="is-boxed text-xl flex justify-end">
                    <input class="button is-success" type="submit" value="수정">
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
