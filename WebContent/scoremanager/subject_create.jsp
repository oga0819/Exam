<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.Subject" %>
<%
    Subject subject = (Subject)request.getAttribute("subject");
    String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>科目新規登録</title>
    <link rel="stylesheet" href="../css/subject.css">
</head>
<body>
    <h1>科目情報登録</h1>
    <c:if test="${not empty error}">
        <div style="color:red;"><%= error %></div>
    </c:if>
    <form action="SubjectCreateExecute.action" method="post">
        <table>
            <tr>
                <th>科目コード</th>
                <td><input type="text" name="cd" value="<%= (subject != null && subject.getCd() != null) ? subject.getCd() : "" %>"></td>
            </tr>
            <tr>
                <th>科目名</th>
                <td><input type="text" name="name" value="<%= (subject != null && subject.getName() != null) ? subject.getName() : "" %>"></td>
            </tr>
        </table>
        <input type="submit" value="登録">
        <a href="SubjectList.action">一覧へ戻る</a>
    </form>
</body>
</html>