<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="../css/sub_list.css">

<c:import url="base.jsp">
    <c:param name="title">科目一覧</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>科目管理</h2>
            <div class="register-link">
                <a href="SubjectCreate.action">新規登録</a>
            </div>
            <c:choose>
                <c:when test="${ not empty subjects }">
                    <div>検索結果 : ${ fn:length(subjects) }件</div>
                    <table class="table">
                        <tr>
                            <th>科目コード</th>
                            <th>科目名</th>
                            <th>学校コード</th>
                            <th></th>
                        </tr>
                        <c:forEach var="subject" items="${ subjects }">
                            <tr>
                                <td>${ subject.cd }</td>
                                <td>${ subject.name }</td>
                                <td>${ subject.school.cd }</td>
                                <td>
                                    <a href="SubjectUpdate.action?cd=${ subject.cd }">変更</a>
                                </td>
                                <td>
                                    <a href="SubjectDelete.action?cd=${ subject.cd }">削除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>科目が登録されていません。</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>