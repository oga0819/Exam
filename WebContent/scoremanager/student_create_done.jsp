<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:forward page="base.jsp">
  <jsp:param name="title" value="学生情報登録完了"/>
  <jsp:param name="content" value='
  	<link rel="stylesheet" href="../css/s_createdone.css">
    <div class="page-title">学生情報登録</div>
    <div class="complete-message">学生情報が正常に登録されました。</div>
    <div class="link-row">
      <a href="StudentList.action" class="list-link">学生一覧に戻る</a>
    </div>
  '/>
  <jsp:param name="scripts" value="" />
</jsp:forward>