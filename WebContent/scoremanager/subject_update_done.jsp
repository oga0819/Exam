<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sub_update_done.css">

<c:import url="base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <div class="complete-box">
      <!-- タイトル -->
      <div class="complete-title">科目情報登録</div>

      <!-- 完了メッセージ -->
      <div class="complete-message">${message}</div>

      <!-- 戻るリンク -->
      <div class="complete-link">
        <a href="SubjectList.action">科目一覧</a>
      </div>
    </div>
  </c:param>
</c:import>
