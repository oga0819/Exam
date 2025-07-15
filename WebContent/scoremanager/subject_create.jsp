<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/sub_create.css">

<c:import url="base.jsp">
    <c:param name="title">科目新規登録</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2 class="page-title">科目情報登録</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>

            <form action="SubjectCreateExecute.action" method="post">
                <table>
                    <tr>
                        <th>科目コード</th>
                        <td>
                            <input
                              type="text"
                              name="cd"
                              value="${subject.cd}"
                              required
                              placeholder="例: A01"
                            >
                        </td>
                    </tr>
                    <tr>
                        <th>科目名</th>
                        <td>
                            <input
                              type="text"
                              name="name"
                              value="${subject.name}"
                              required
                              placeholder="例: 数学"
                            >
                        </td>
                    </tr>
                </table>

                <div class="actions">
                    <input type="submit" value="登録">
                    <a href="SubjectList.action">一覧へ戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
