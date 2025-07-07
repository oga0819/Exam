package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListStudent;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        // セッションからログイン中の教師を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            request.setAttribute("error", "ログインしてください。");
            return "error.jsp";
        }

        // パラメータ取得（科目コードとクラス番号は必須）
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");

        if (subjectCd == null || subjectCd.isEmpty() || classNum == null || classNum.isEmpty()) {
            request.setAttribute("error", "科目コードとクラス番号は必須です。");
            return "error.jsp";
        }

        // 検索条件用のTestListStudentオブジェクトを作成し任意条件をセット
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

        // DAO呼び出し
        TestListSubjectDAO dao = new TestListSubjectDAO();
        List<TestListStudent> list = dao.filter(subjectCd, classNum, target);

        // 結果をリクエストスコープにセット
        request.setAttribute("subjectClassTestList", list);

        // 検索条件を戻す（画面で入力値表示に利用）
        request.setAttribute("searchTarget", target);
        request.setAttribute("subjectCd", subjectCd);
        request.setAttribute("classNum", classNum);

        // JSPへ遷移
        return "test_list.jsp";
    }
}
