package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "login.jsp";
        }
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // ★ ここで空チェック
        if (cd == null || cd.trim().isEmpty()) {
            request.setAttribute("error", "科目コードは必須です。");
            request.setAttribute("subject", subject);
            return "subject_create.jsp";
        }
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "科目名は必須です。");
            request.setAttribute("subject", subject);
            return "subject_create.jsp";
        }

        SubjectDAO dao = new SubjectDAO();
        try {
            // 重複チェック（既存科目の有無）
            Subject exists = dao.get(cd, school);
            if (exists != null) {
                request.setAttribute("error", "その科目コードは既に登録されています。");
                request.setAttribute("subject", subject);
                return "subject_create.jsp";
            }

            // 新規登録
            boolean result = dao.save(subject);
            if (result) {
                return "subject_create_done.jsp";
            } else {
                request.setAttribute("error", "登録に失敗しました。");
                request.setAttribute("subject", subject);
                return "subject_create.jsp";
            }
        } catch (Exception e) {
            request.setAttribute("error", "エラーが発生しました: " + e.getMessage());
            request.setAttribute("subject", subject);
            return "subject_create.jsp";
        }
    }
}