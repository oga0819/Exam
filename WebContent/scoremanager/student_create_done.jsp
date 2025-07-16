<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/stu_create_done.css">

<c:import url="base.jsp">
  <c:param name="title" value="学生情報登録完了" />
  <c:param name="content">
      <div class="page-title">学生情報登録</div>
      <div class="complete-message">学生情報が正常に登録されました。</div>
      <div class="link-row">
        <a href="StudentList.action" class="list-link">学生一覧に戻る</a>
      </div>
  </c:param>
  <c:param name="scripts" value="" />
</c:import>
