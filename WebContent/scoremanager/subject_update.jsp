<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.Subject" %>
<%@include file="base.jsp" %>
<%
    Subject subject = (Subject) request.getAttribute("subject");
%>
<html>
<head>
    <title>科目名変更</title>
</head>
<body>
    <h2>科目情報変更</h2>
    <form action="SubjectUpdateExecuteAction" method="post">
        <p>科目コード: <%= subject.getCd() %></p>
        <input type="hidden" name="cd" value="<%= subject.getCd() %>">
        <p>
            科目名: <input type="text" name="name" value="<%= subject.getName() %>">
        </p>
        <input type="submit" value="変更">
    </form>
</body>
</html>