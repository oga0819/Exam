<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sub_delete_done.css">

<c:import url="base.jsp">
  <c:param name="title">科目削除完了</c:param>
  <c:param name="content">
    <h1>科目削除完了</h1>
    <div>科目が正常に削除されました。</div>
    <div>
        <a href="SubjectList.action">科目一覧へ戻る</a>
    </div>
  </c:param>
</c:import>
