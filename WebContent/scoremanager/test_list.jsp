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
	                <input type="submit" value="検索" class="btn"><br>

	            </form>

	            <!-- 科目情報絞り込みフォーム未入力時　エラーメッセージ表示 -->
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

			<!-- 利用方法案内メッセージ -->
			<p id="annaimessage">
				<label>科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</label>
			</p>

        </section>
    </c:param>
</c:import>