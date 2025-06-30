<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生新規登録</title>
</head>
<body>
    <h2>学生新規登録</h2>

    <c:if test="${not empty error}">
        <div style="color:red">${error}</div>
    </c:if>

    <form action="StudentCreateExecute.action" method="post">
        <table>

            <tr>
                <th>入学年度</th>
                <td>
                    <select name="ent_year" required>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${param.ent_year == year}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <th>学生番号</th>
                <td><input type="text" name="no" value="${param.no}" required></td>
            </tr>
            <tr>
                <th>氏名</th>
                <td><input type="text" name="name" value="${param.name}" required></td>
            </tr>

            <tr>
                <th>クラス</th>
                <td>
                    <select name="class_num" required>
                        <c:forEach var="classNum" items="${class_num_set}">
                            <option value="${classNum}" <c:if test="${param.class_num == classNum}">selected</c:if>>${classNum}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

        </table>
        <input type="submit" value="登録して終了">
    </form>

    <p><a href="StudentList.action">学生一覧に戻る</a></p>
</body>
</html>