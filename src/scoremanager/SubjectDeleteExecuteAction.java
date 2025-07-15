package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションから teacher を取得し school を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "subject_delete.jsp";
        }
        School school = teacher.getSchool();

        // リクエストから科目コードを取得
        String cd = request.getParameter("cd");
        if (cd == null || cd.isEmpty()) {
            request.setAttribute("error", "科目コードが指定されていません");
            return "subject_delete.jsp";
        }

        // 削除対象の科目オブジェクトを生成して school をセット
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(school); // ★これがないとNPEになる！

        // 削除実行
        SubjectDAO dao = new SubjectDAO();
        boolean success = dao.delete(subject);

        if (success) {
            return "subject_delete_done.jsp";
        } else {
            request.setAttribute("error", "科目の削除に失敗しました");
            return "error.jsp";
        }
    }
}