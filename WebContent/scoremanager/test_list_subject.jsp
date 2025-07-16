<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/test_list.css">

<c:import url="base.jsp">
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

            <!-- 結果表示 -->
            <!-- 科目名 -->
			<p>科目：${subjectName}</p>

            <!-- 学生データが空の場合の表示 -->
			<c:if test="${empty subjectClassTestList}">
			    <div>学生情報が存在しませんでした</div>
			</c:if>

            <!-- 学生データがある場合のみテーブルを表示 -->
			<c:if test="${not empty subjectClassTestList}">
				<table border="1">
				    <tr>
				        <th>入学年度</th>
				        <th>クラス</th>
				        <th>学生番号</th>
				        <th>氏名</th>
				        <!-- ここに全テスト回のカラムを動的に出す -->
				        <c:forEach var="testNo" items="${testNoList}">
				            <th>${testNo}回目</th>
				        </c:forEach>
				    </tr>
				    <c:forEach var="student" items="${subjectClassTestList}">
				        <tr>
				            <td>${student.entYear}</td>
				            <td>${student.classNum}</td>
				            <td>${student.studentNo}</td>
				            <td>${student.studentName}</td>
				            <!-- テスト回の点数を順に表示 -->
				            <c:forEach var="testNo" items="${testNoList}">
				                <td>
				                    <c:choose>
				                        <c:when test="${student.points[testNo] != null}">
				                            ${student.points[testNo]}
				                        </c:when>
				                        <c:otherwise>
				                            <!-- 点数なしの場合は空欄 -->
				                        </c:otherwise>
				                    </c:choose>
				                </td>
				            </c:forEach>
				        </tr>
				    </c:forEach>
				</table>
			</c:if>

        </section>
    </c:param>
</c:import>