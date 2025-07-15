package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TestDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        // ログインチェック
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "ログインしてください。");
            return "error.jsp";
        }

        // パラメータ取得
        String subjectCd = request.getParameter("subjectCd");
        String noStr = request.getParameter("no");

        if (subjectCd == null || subjectCd.isEmpty() || noStr == null || noStr.isEmpty()) {
            request.setAttribute("error", "パラメータが不足しています。");
            return "error.jsp";
        }

        Integer no = null;
        try {
            no = Integer.valueOf(noStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "回数が不正です。");
            return "error.jsp";
        }

        // 削除実行
        TestDeleteExecuteAction executeAction = new TestDeleteExecuteAction();
        boolean deleted = executeAction.deleteTest(subjectCd, no);

        if (!deleted) {
            request.setAttribute("error", "削除に失敗しました。");
            return "error.jsp";
        }

        // 削除成功後は成績一覧画面へリダイレクトなど
        // 再検索や一覧表示をしたい場合は適宜パラメータを渡すなど工夫してください
        response.sendRedirect("test_list.jsp");
        return null; // 既にリダイレクト済みのためnullを返す
    }
}
