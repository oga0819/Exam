package scoremanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        //セッションからログイン中の教師を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

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

        int entYear;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "");
            return "test_list.jsp";
        }

        //DAO呼び出し
        TestListSubjectDAO dao = new TestListSubjectDAO();
        School school = teacher.getSchool(); // teacherからスクール情報を取得

        List<TestListSubject> list = dao.filter(entYear, classNum, subjectCd, school);

        //科目名を取得し、JSPに渡す
        SubjectDAO subjectDao = new SubjectDAO();
        Subject subject = subjectDao.get(subjectCd, school); // ← School を追加

        if (subject != null) {
            request.setAttribute("subjectName", subject.getName());
        } else {
            request.setAttribute("subjectName", "不明な科目");
        }


        //テスト回を重複なく
        Set<Integer> testNoSet = new TreeSet<Integer>();
        for (TestListSubject student : list) {
            testNoSet.addAll(student.getPoints().keySet());
        }
        List<Integer> testNoList = new ArrayList<>(testNoSet); //SetをListに変換

        request.setAttribute("testNoList", testNoList);
        request.setAttribute("subjectClassTestList", list);

        //結果をリクエストスコープにセット
        request.setAttribute("subjectClassTestList", list);

        //検索条件を戻す（画面で入力値表示に利用）
        request.setAttribute("selectedEntYear", entYear);
        request.setAttribute("selectedClassNum", classNum);
        request.setAttribute("selectedSubjectCd", subjectCd);

        //JSPへ遷移
        return "test_list_subject.jsp";
    }
}
