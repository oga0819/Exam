package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ログイン中の学校情報を取得
        School school = (School) request.getSession().getAttribute("school");

        // リクエストパラメータからcdを取得
        String cd = request.getParameter("cd");

        // 科目情報を取得
        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(cd, school);

        // リクエストに科目を保存
        request.setAttribute("subject", subject);

        // 確認ページへ
        return "subject_delete.jsp";
    }
}