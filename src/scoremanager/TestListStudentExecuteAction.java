package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            request.setAttribute("error", "ログインしてください。");
            return "error.jsp";
        }

        // 学生番号をフォームから取得（name="f4"）
        String studentNo = request.getParameter("f4");

        // Studentインスタンスに学生番号をセット（filter用）
        Student studentFilter = new Student();
        studentFilter.setNo(studentNo);

        // 成績を取得
        TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
        List<TestListStudent> testList = testListStudentDAO.filter(studentFilter);

        // 学生情報を取得
        StudentDAO studentDAO = new StudentDAO();
        Student studentInfo = null;
        if (studentNo != null && !studentNo.isEmpty()) {
            studentInfo = studentDAO.get(studentNo);
        }

        // JSPへセット
        request.setAttribute("studentTestList", testList);

<<<<<<< master
<<<<<<< HEAD
        //学生情報（氏名・学生番号）をセット（nullチェック必須）
=======
        // 学生情報（氏名・学生番号）をセット（nullチェック必須）
>>>>>>> 4545e92 test
        if (studentInfo != null) {
            request.setAttribute("studentName", studentInfo.getName());
            request.setAttribute("studentNo", studentInfo.getNo());
        } else {
            request.setAttribute("studentName", "");
            request.setAttribute("studentNo", studentNo);
=======
        target.setName(request.getParameter("name"));
        target.setSubjectCd(request.getParameter("subjectCd"));

        String numStr = request.getParameter("num");
        if (numStr != null && !numStr.isEmpty()) {
            try {
                target.setNo(Integer.valueOf(numStr));
            } catch (NumberFormatException e) {
                target.setNo(null); // 不正な入力は無視
            }
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
        }

        // 検索条件戻し用
        request.setAttribute("searchTarget", studentFilter);

        return "test_list_student.jsp";
    }
}
