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
                    target.setNum(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNum(null);
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

            return "subject_class_list.jsp";

        } else {
            // 学生別成績一覧モード（デフォルト）
            TestListStudent target = new TestListStudent();

            target.setName(request.getParameter("name"));
            target.setSubjectCd(request.getParameter("subjectCd"));

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNum(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNum(null);
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

            return "test_list_student.jsp";
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
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        // 最初（画面表示初期化時）
        String init = request.getParameter("init");
        if (init == null || "true".equals(init)) {

            // セッションから教師情報を取得
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");

            if (teacher == null) {
                request.setAttribute("error", "ログインしてください。");
                return "error.jsp";
            }

            // 教師の所属学校を取得
            School school = teacher.getSchool();

            // 学校からクラス一覧を取得
            ClassNumDAO classDao = new ClassNumDAO();
            List<String> classList = classDao.filter(school);

            // 科目一覧を取得
            SubjectDAO subjectDao = new SubjectDAO();
            List<Subject> subjectList = subjectDao.filter(school);

            // 学生情報から入学年度一覧を取得
            StudentDAO studentDao = new StudentDAO();
            List<Student> studentList = studentDao.filter(school, true);

            // 入学年度の降順セットを作成
            Set<Integer> entYearSet = new TreeSet<>((a, b) -> b - a); // 降順
            for (Student s : studentList) {
                entYearSet.add(s.getEntYear());
            }
            List<Integer> entYearList = new ArrayList<>(entYearSet);

            // JSPに渡す
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("entYearList", entYearList);

            return "test_list.jsp";
        }

        // 画面からのパラメータ取得
        String mode = request.getParameter("mode"); // "student" or "subjectClass"

        if ("subjectClass".equals(mode)) {
            // 科目＋クラスごと成績参照モード

            String entYearStr = request.getParameter("f1"); // 入学年度
            String classNum = request.getParameter("f2");   // クラス番号
            String subjectCd = request.getParameter("f3");  // 科目コード

            if (entYearStr == null || entYearStr.isEmpty() ||
                classNum == null || classNum.isEmpty() ||
                subjectCd == null || subjectCd.isEmpty()) {
                request.setAttribute("error", "入学年度、クラス番号、科目コードは必須です。");
                return "error.jsp";
            }

            int entYear;
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "入学年度は数値で指定してください。");
                return "error.jsp";
            }

            // セッションから教師情報を取得
            HttpSession session = request.getSession();
            Teacher teacher = (Teacher) session.getAttribute("teacher");
            if (teacher == null) {
                request.setAttribute("error", "ログインしてください。");
                return "error.jsp";
            }
            School school = teacher.getSchool();

            // DAO呼び出し
            TestListSubjectDAO dao = new TestListSubjectDAO();
            List<TestListSubject> list = dao.filter(entYear, classNum, subjectCd, school);

            // 結果をリクエストスコープにセット
            request.setAttribute("subjectClassTestList", list);

            // 検索条件を戻す（画面で入力値表示に利用）
            request.setAttribute("selectedEntYear", entYear);
            request.setAttribute("classNum", classNum);
            request.setAttribute("subjectCd", subjectCd);

            return "subject_class_list.jsp";

        } else {
            // 学生別成績一覧モード（デフォルト）

            // ここはお手持ちの処理を必要に応じて適宜修正してください
            // 例としてそのまま残しておきます

            TestListStudent target = new TestListStudent();

            target.setSubjectName(request.getParameter("subjectName"));
            target.setSubjectCd(request.getParameter("subjectCd"));

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNum(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNum(null);
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

            // DAO呼び出し
            // TestListStudentDAO dao = new TestListStudentDAO();
            // List<TestListStudent> list = dao.filter(target);

            // request.setAttribute("studentTestList", list);
            request.setAttribute("searchTarget", target);

            return "test_list_student.jsp";
        }
    }
}

