package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListStudent;
import dao.TestListStudentDAO;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

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

        // 検索条件用のTestListStudentオブジェクトを作成しパラメータをセット
        TestListStudent target = new TestListStudent();

        target.setName(request.getParameter("name"));
        target.setSubjectCd(request.getParameter("subjectCd"));

        String numStr = request.getParameter("num");
        if (numStr != null && !numStr.isEmpty()) {
            try {
                target.setNum(Integer.valueOf(numStr));
            } catch (NumberFormatException e) {
                target.setNum(null); // 不正な入力は無視
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

        // DAOで検索実行
        TestListStudentDAO dao = new TestListStudentDAO();
        List<TestListStudent> list = dao.filter(target);

        // 検索結果をリクエストスコープへセット
        request.setAttribute("studentTestList", list);

        // 検索条件を戻す（画面で入力値表示に利用可）
        request.setAttribute("searchTarget", target);

        // 結果表示JSPへ遷移
        return "test_list_student.jsp";
    }
}
