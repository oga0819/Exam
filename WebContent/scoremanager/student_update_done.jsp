<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/stu_update_done.css">

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2 class="page-title">学生情報変更</h2>
            <p class="complete-message">
            	<label>変更が完了しました</label>
            </p>
            <a href="StudentList.action">学生一覧</a>
        </section>
    </c:param>
</c:import>