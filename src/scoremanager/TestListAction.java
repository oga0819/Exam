/*
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
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        //最初
        String init = request.getParameter("init");
        if (init == null || "true".equals(init)) {

            //セッションから教師情報を取得
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            //教師の所属学校を取得
            School school = teacher.getSchool();

            //学校からクラス一覧を取得
            ClassNumDAO classDao = new ClassNumDAO();
            List<String> classList = classDao.filter(school);

            //科目一覧を取得
            SubjectDAO subjectDao = new SubjectDAO();
            List<Subject> subjectList = subjectDao.filter(school);

            //学生情報から入学年度一覧を取得
            StudentDAO studentDao = new StudentDAO();
            List<Student> studentList = studentDao.filter(school, true);

            Set<Integer> entYearSet = new TreeSet<>((a, b) -> b - a); // 降順
            for (Student s : studentList) {
                entYearSet.add(s.getEntYear());
            }
            List<Integer> entYearList = new ArrayList<>(entYearSet);

            //JSPに渡す
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("entYearList", entYearList);

            return "test_list.jsp";
        }



        // 画面からのパラメータ取得
        String mode = request.getParameter("mode"); // "student" or "subjectClass"

        if ("subjectClass".equals(mode)) {
            // 科目＋クラスごと成績参照モード

            String subjectCd = request.getParameter("subjectCd");
            String classNum = request.getParameter("classNum");

            if (subjectCd == null || subjectCd.isEmpty() || classNum == null || classNum.isEmpty()) {
                request.setAttribute("error", "科目コードとクラス番号は必須です。");
                return "error.jsp";
            }

            TestListStudent target = new TestListStudent();

            String name = request.getParameter("name");
            if (name != null && !name.isEmpty()) {
                target.setName(name);
            }

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNo(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNo(null);
                }
            }

            String pointStr = request.getParameter("point");
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    target.setPoint(Integer.valueOf(pointStr));
                } catch (NumberFormatException e) {
                    target.setPoint(null);
                }
            }

            TestListSubjectDAO dao = new TestListSubjectDAO();
            List<TestListStudent> list = dao.filter(subjectCd, classNum, target);

            request.setAttribute("subjectClassTestList", list);
            request.setAttribute("searchTarget", target);
            request.setAttribute("subjectCd", subjectCd);
            request.setAttribute("classNum", classNum);

            return "subject_list.jsp";

        } else {
            // 学生別成績一覧モード（デフォルト）
            TestListStudent target = new TestListStudent();

            target.setName(request.getParameter("name"));
            target.setSubjectCd(request.getParameter("subjectCd"));

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNo(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNo(null);
                }
            }

            String pointStr = request.getParameter("point");
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    target.setPoint(Integer.valueOf(pointStr));
                } catch (NumberFormatException e) {
                    target.setPoint(null);
                }
            }

            TestListStudentDAO dao = new TestListStudentDAO();
            List<TestListStudent> list = dao.filter(target);

            request.setAttribute("studentTestList", list);
            request.setAttribute("searchTarget", target);

<<<<<<< HEAD
            return "test_list_student.jsp";
=======
            return "test_list.jsp";
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
        }
    }
}*/

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
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

            //セッションから教師情報を取得
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            //教師の所属学校を取得
            School school = teacher.getSchool();

            //学校からクラス一覧を取得
            ClassNumDAO classDao = new ClassNumDAO();
            List<String> classList = classDao.filter(school);

            //科目一覧を取得
            SubjectDAO subjectDao = new SubjectDAO();
            List<Subject> subjectList = subjectDao.filter(school);

            //学生情報から入学年度一覧を取得
            StudentDAO studentDao = new StudentDAO();
            List<Student> studentList = studentDao.filter(school, true);

            //入学年度の降順セットを作成
            Set<Integer> entYearSet = new TreeSet<>((a, b) -> b - a); // 降順
            for (Student s : studentList) {
                entYearSet.add(s.getEntYear());
            }
            List<Integer> entYearList = new ArrayList<>(entYearSet);

            //JSPに渡す
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("entYearList", entYearList);

            return "test_list.jsp";


    }
}

