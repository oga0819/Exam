<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sub_create_done.css">

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2 class="page-title">科目情報登録</h2>
            <p class="complete-message">
            	<label>登録が完了しました</label>
            </p>
            <a href="SubjectList.action">科目一覧</a>
        </section>
    </c:param>
</c:import>