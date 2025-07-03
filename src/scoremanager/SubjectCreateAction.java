package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 教員セッションから学校情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "login.jsp";
        }
        School school = teacher.getSchool();

        // 空Subjectを作成し学校をセット
        Subject subject = new Subject();
        subject.setSchool(school);

        // リクエスト属性にセット
        request.setAttribute("subject", subject);

        // 新規登録画面へ
        return "subject_create.jsp";
    }
}