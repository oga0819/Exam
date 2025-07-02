<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="base.jsp" %>
<html>
<head>
    <title>科目更新完了</title>
</head>
<body>
    <h2><%= request.getAttribute("message") %></h2>
    <a href="SubjectListAction">科目一覧へ戻る</a>
</body>
</html>