package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;

public class SubjectUpdateAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");
        School school = (School) request.getSession().getAttribute("school"); // セッションにSchoolが格納されている前提

        if (cd == null || school == null) {
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