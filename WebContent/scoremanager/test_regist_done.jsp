<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/test_regist.css">

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>成績管理</h2>
            <p>
            	<label>変更が完了しました</label>
            </p>
            <a href="TestRegist.action">戻る</a>
            <a href="TestList.action">成績参照</a>

        </section>
    </c:param>
</c:import>