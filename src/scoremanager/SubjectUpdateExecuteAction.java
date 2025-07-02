package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;

public class SubjectUpdateExecuteAction {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        School school = (School) request.getSession().getAttribute("school"); // セッションにSchoolが格納されている前提

        if (cd == null || name == null || name.trim().isEmpty() || school == null) {
            request.setAttribute("error", "入力内容に誤りがあります。");
            return "error.jsp";
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        SubjectDAO dao = new SubjectDAO();
        boolean result = dao.save(subject);

        if (!result) {
            request.setAttribute("error", "科目の更新に失敗しました。");
            return "error.jsp";
        }

        request.setAttribute("message", "科目名を変更しました。");
        // 一覧画面、または詳細画面などにリダイレクト
        return "subject_update_done.jsp";
    }
}