<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/test_list.css">

<c:import url="base.jsp">
  <c:param name="title">成績一覧</c:param>
  <c:param name="scripts"></c:param>
  <c:param name="content">
    <section>
      <h2>成績一覧</h2>

      <!-- ▼ 学生番号での検索結果 -->
      <c:if test="${not empty studentTestList}">
        <h3>【学生別】検索結果</h3>
        <table class="table">
          <thead>
            <tr>
              <th>科目名</th>
              <th>科目コード</th>
              <th>回数</th>
              <th>点数</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="test" items="${studentTestList}">
              <tr>
                <td>${test.subjectCd}</td>
                <td>${test.name}</td>
                <td>${test.no}</td>
                <td>${test.point}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <!-- ▼ 科目＋クラスでの検索結果 -->
      <c:if test="${not empty subjectClassTestList}">
        <h3>【科目別】検索結果</h3>
        <p>科目コード：${subjectCd} ／ クラス番号：${classNum}</p>
        <table class="table">
          <thead>
            <tr>
              <th>学生番号</th>
              <th>氏名</th>
              <th>回数</th>
              <th>点数</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="test" items="${subjectClassTestList}">
              <tr>
                <td>${test.num}</td>
                <td>${test.name}</td>
                <td>${test.no}</td>
                <td>${test.point}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <!-- データがどちらも無い場合 -->
      <c:if test="${empty studentTestList and empty subjectClassTestList}">
        <p>該当する成績データはありません。</p>
      </c:if>

      <div style="margin-top: 20px;">
        <a href="test_list.jsp">成績参照検索に戻る</a>
      </div>
    </section>
  </c:param>
</c:import>
