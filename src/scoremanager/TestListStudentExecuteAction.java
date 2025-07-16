package scoremanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        //セッションから教師情報取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        //教師の所属学校取得
        School school = teacher.getSchool();

        //学校からクラス一覧取得
        ClassNumDAO classDao = new ClassNumDAO();
        List<String> classList = classDao.filter(school);

        //科目一覧取得
        SubjectDAO subjectDao = new SubjectDAO();
        List<Subject> subjectList = subjectDao.filter(school);

        //学生情報から入学年度一覧取得
        StudentDAO studentDao = new StudentDAO();
        List<Student> studentList = studentDao.filter(school, true);

        //入学年度の降順セット作成
        Set<Integer> entYearSet = new TreeSet<>((a, b) -> b - a); // 降順
        for (Student s : studentList) {
        	entYearSet.add(s.getEntYear());
        }
        List<Integer> entYearList = new ArrayList<>(entYearSet);

        //JSPに渡す
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("entYearList", entYearList);

        //学生番号をフォームから取得
        String studentNo = request.getParameter("f4");

        //Studentインスタンスに学生番号をセット
        Student studentFilter = new Student();
        studentFilter.setNo(studentNo);

        //成績取得
        TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
        List<TestListStudent> testList = testListStudentDAO.filter(studentFilter);

        //学生情報取得
        StudentDAO studentDAO = new StudentDAO();
        Student studentInfo = null;
        if (studentNo != null && !studentNo.isEmpty()) {
            studentInfo = studentDAO.get(studentNo);
        }

        //JSPへセット
        request.setAttribute("studentTestList", testList);

        //学生情報（氏名・学生番号）をセット（nullチェック必須）
        if (studentInfo != null) {
            request.setAttribute("studentName", studentInfo.getName());
            request.setAttribute("studentNo", studentInfo.getNo());
        } else {
            request.setAttribute("studentName", "");
            request.setAttribute("studentNo", studentNo);
        }

        return "test_list_student.jsp";
    }
}
