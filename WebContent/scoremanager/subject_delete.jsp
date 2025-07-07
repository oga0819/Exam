<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/subject_delete.css">

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>科目情報削除</h2>
            <p>以下の科目情報を削除します。よろしいですか？</p>
            <div>
                <label>科目コード：</label>
                <span>${subject.cd}</span>
            </div>
            <div>
                <label>科目名　　：</label>
                <span>${subject.name}</span>
            </div>
            <form action="SubjectDeleteExecute.action" method="post" style="margin-top:18px;">
                <input type="hidden" name="cd" value="${subject.cd}">
                <button type="submit" style="background:#c00;color:#fff;padding:8px 24px;border:none;border-radius:4px;font-weight:bold;cursor:pointer;">削除</button>
            </form>
            <div style="margin-top:14px;">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>