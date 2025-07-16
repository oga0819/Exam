package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ログイン中の教員情報から学校情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "subject_error.jsp";
        }
        School school = teacher.getSchool();

        // リクエストパラメータからcdを取得
        String cd = request.getParameter("cd");
        if (cd == null || cd.isEmpty()) {
            request.setAttribute("error", "科目コードが指定されていません。");
            return "subject_error.jsp";
        }

        // 科目情報を取得
        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(cd, school);
        if (subject == null) {
            request.setAttribute("error", "指定された科目が見つかりません。");
            return "subject_error.jsp";
        }

        // リクエストに科目を保存
        request.setAttribute("subject", subject);

        // 確認ページへ
        return "subject_delete.jsp";
    }
}