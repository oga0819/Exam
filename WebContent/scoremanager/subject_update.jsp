<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sub_update.css">

<c:import url="base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>科目情報変更</h2>
            <form action="SubjectUpdateExecute.action" method="post">
                <div>
                    <label>科目コード</label><br>
                    <input type="text" name="cd" value="${subject.cd}" readonly style="background:#f5f5f5; border:none;">
                </div>
                <div>
                    <label>科目名</label><br>
                    <input type="text" name="name" value="${subject.name}" required>
                    <c:if test="${not empty errorname}">
                        <p style="color: red;">${errorname}</p>
                    </c:if>
                </div>
                <div>
                    <button type="submit">変更</button>
                </div>
                <div>
                    <a href="SubjectList.action">一覧へ戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>