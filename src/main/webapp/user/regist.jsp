<%--
  Created by IntelliJ IDEA.
  User: oct_sky_out
  Date: 2022/04/29
  Time: 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="../palette/prettyTools.jsp"/>
    <title>회원생성</title>
</head>
<body>
<div class="w-screen">
    <form action="/profile" method="post" enctype="multipart/form-data" class="uploader">
        <div class="w-64 mx-auto my-10 space-y-10">
            <div class="text-xl">
                <div class="is-boxed">
                    <label>
                        아이디
                        <input type="text" name="id">
                    </label>
                </div>
                <div class="is-boxed">
                    <label>
                        사용자명
                        <input type="text" name="name">
                    </label>
                </div>
                <div class="is-boxed">
                    <label>
                        비밀번호
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
                                  파일을 선택하세요.
                                </span>
                            </span>
                            <span class="file-name text-center">이미지 파일만 허용합니다.</span>
                        </label>
                    </div>
                    <div class="w-full mt-2 file-label">
                        <input type="submit" class="button is-success" value="유저등록">
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
