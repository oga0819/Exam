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
import bean.TestListSubject;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

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

        //パラメータ取得
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2");   // クラス番号
        String subjectCd = request.getParameter("f3");  // 科目コード

        //入力チェック
        if (entYearStr == null || entYearStr.isEmpty()
                || classNum == null || classNum.isEmpty()
                || subjectCd == null || subjectCd.isEmpty()) {
            request.setAttribute("error", "入学年度とクラスと科目を選択してください。");
            return "test_list.jsp";
        }

<<<<<<< HEAD
        int entYear;
=======
        // パラメータ取得（科目コードとクラス番号は必須）
        String subjectCd = request.getParameter("subject_Cd");
        String classNum = request.getParameter("class_Num");
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git

        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "");
            return "test_list.jsp";
        }

<<<<<<< HEAD
        //DAO呼び出し
=======
        // 検索条件用のTestListStudentオブジェクトを作成し任意条件をセット
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

        // DAO呼び出し
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
        TestListSubjectDAO dao = new TestListSubjectDAO();
        List<TestListSubject> list = dao.filter(entYear, classNum, subjectCd, school);

        //科目名を取得、JSPに渡す
        Subject subject = subjectDao.get(subjectCd, school);

        if (subject != null) {
            request.setAttribute("subjectName", subject.getName());
        } else {
            request.setAttribute("subjectName", "不明な科目");
        }

        //selectedSubjectにSubjectオブジェクトをセット
        request.setAttribute("selectedSubject", subject);

        //テスト回を重複なく
        Set<Integer> testNoSet = new TreeSet<Integer>();
        for (TestListSubject student : list) {
            testNoSet.addAll(student.getPoints().keySet());
        }
        List<Integer> testNoList = new ArrayList<>(testNoSet); //SetをListに変換

        request.setAttribute("testNoList", testNoList);
        request.setAttribute("subjectClassTestList", list);

<<<<<<< HEAD
        //結果をリクエストスコープにセット
        request.setAttribute("subjectClassTestList", list);
=======
        // 検索条件を戻す（画面で入力値表示に利用）
        String entYear = request.getParameter("ent_year"); // 追加

>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git

<<<<<<< HEAD
        //検索条件を戻す（画面で入力値表示に利用）
        request.setAttribute("selectedEntYear", entYear);
        request.setAttribute("selectedClassNum", classNum);
        request.setAttribute("selectedSubjectCd", subjectCd);

        //System.out.println("選択パラメータ: entYear=" + entYearStr + ", classNum=" + classNum + ", subjectCd=" + subjectCd);
        //System.out.println("科目取得: subject=" + subject);
        //System.out.println("結果リスト件数: " + list.size());

        return "test_list_subject.jsp";
=======
        // JSPへ遷移
        return "test_list_student.jsp";
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
    }
}
