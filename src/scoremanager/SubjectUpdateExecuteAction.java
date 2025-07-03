package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
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
        String name = request.getParameter("name");

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(school);
        subject.setName(name);

        // 入力チェック
        if (cd == null || cd.trim().isEmpty()) {
            request.setAttribute("error", "科目コードは必須です。");
            request.setAttribute("subject", subject);
            return "subject_update.jsp";
        }
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "科目名は必須です。");
            request.setAttribute("subject", subject);
            return "subject_update.jsp";
        }

        SubjectDAO dao = new SubjectDAO();

        // 更新前レコードの存在チェック
        Subject exists = dao.get(cd, school);
        if (exists == null) {
            request.setAttribute("error", "指定された科目が存在しません。");
            request.setAttribute("subject", subject);
            return "subject_update.jsp";
        }

        // 更新処理
        boolean result = dao.save(subject);
        if (!result) {
            request.setAttribute("error", "科目の更新に失敗しました。");
            request.setAttribute("subject", subject);
            return "subject_update.jsp";
        }

        request.setAttribute("message", "科目名を変更しました。");
        return "subject_update_done.jsp";
    }
}