<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/test_list.css">

<c:import url="base.jsp">
<<<<<<< HEAD
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>成績参照</h2>

			<div id="filter-wrapper">
				<!-- 絞り込みフォーム 科目情報 -->
	            <form id="filter" action="TestListSubjectExecute.action" method="get">
	            <p class="sform"><label>科目情報</label></p>
	            <input type="hidden" name="mode" value="subjectClass" />
	                <label>入学年度<br>
	                    <select name="f1">
	                    	<option value="">------</option>
	                        <c:forEach var="y" items="${entYearList}">
	                            <option value="${y}" <c:if test="${y == selectedEntYear}">selected</c:if>>${y}</option>
	                        </c:forEach>
	                    </select>
	                </label>

	                <label>クラス<br>
	                    <select name="f2">
	                        <option value="">------</option>
	                        <c:forEach var="c" items="${classList}">
								<option value="${c}" <c:if test="${c == selectedClassNum}">selected</c:if>>${c}</option>
							</c:forEach>
	                    </select>
	                </label>

	                <label>科目<br>
	                    <select name="f3">
	                        <option value="">------</option>
	                        <c:forEach var="s" items="${subjectList}">
								<option value="${s.cd}" <c:if test="${selectedSubject != null && s.cd == selectedSubject.cd}">selected</c:if>>${s.name}</option>
	                        </c:forEach>
	                    </select>
	                </label>
	                <input type="submit" value="検索" class="btn">

	            </form>

				<!-- 絞り込みフォーム未入力時　エラーメッセージ表示 -->
		        <c:if test="${not empty error}">
					<div id="errormessage">
						${error}
					</div>
				</c:if>

				<!-- 絞り込みフォーム 学生情報 -->
	            <form id="filter" action="TestListStudentExecute.action" method="get">
	            <p class="sform"><label>学生情報</label></p>
	            <input type="hidden" name="mode" value="student" />
	                <label>学生番号<br>
	                    <input type="text" name="f4" required/>
	                </label>
	                <input type="submit" value="検索" class="btn">
	            </form>
            </div>

			<div>氏名：${studentName}（${studentNo}）</div>

			<!-- 学生データが空の場合の表示 -->
			<c:if test="${empty sutudentTestList}">
			    <div>成績情報が存在しませんでした</div>
			</c:if>

			<!-- 学生データがある場合のみテーブルを表示 -->
			<c:if test="${not empty studentTestList}">
				<table border="1">
				    <tr>
				        <th>科目名</th>
				        <th>科目コード</th>
				        <th>回数</th>
				        <th>点数</th>
				    </tr>
				    <c:forEach var="record" items="${studentTestList}">
				        <tr>
				            <td>${record.subjectName}</td>
				            <td>${record.subjectCd}</td>
				            <td>${record.num}回目</td>
				            <td>${record.point}点</td>
				        </tr>
				    </c:forEach>
				</table>
			</c:if>


        </section>
    </c:param>
</c:import>
=======
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
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
