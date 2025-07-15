<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="../css/grade_search.css">

<c:import url="base.jsp">
    <c:param name="title">成績参照検索</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section>
            <h2>成績参照検索</h2>

            <!-- ▼ 科目情報から検索 -->
            <p>科目情報から検索</p>
            <form action="TestListSubjectExecute.action" method="get">
                <div>
                    <label>入学年度</label><br>
                    <select name="ent_year">
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label>クラス</label><br>
                    <select name="class_num">
                        <c:forEach var="class" items="${class_num_set}">
                            <option value="${class}">${class}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label>科目</label><br>
                    <select name="subject_cd">
                        <c:forEach var="subject" items="${subject_list}">
                            <option value="${subject.cd}">${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <input type="submit" value="検索">
                </div>
            </form>

            <hr style="margin: 30px 0;">

            <!-- ▼ 学生番号から検索 -->
            <p>学生情報から検索</p>
            <form action="TestListStudentExecute.action" method="get">
                <div>
                    <label>学生番号</label><br>
                    <input type="text" name="student_no" placeholder="学生番号を入力してください" maxlength="10" required>
                </div>

                <div>
                    <input type="submit" value="検索">
                </div>
            </form>
        </section>
    </c:param>
</c:import>
