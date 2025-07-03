package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 教員セッションから学校情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "login.jsp";
        }
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");
        if (cd == null || cd.trim().isEmpty()) {
            request.setAttribute("error", "不正なアクセスです。");
            return "error.jsp";
        }

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.get(cd, school);

        if (subject == null) {
            request.setAttribute("error", "科目が見つかりません。");
            return "error.jsp";
        }

        request.setAttribute("subject", subject);

        return "subject_update.jsp"; // 科目更新画面へ
    }
}