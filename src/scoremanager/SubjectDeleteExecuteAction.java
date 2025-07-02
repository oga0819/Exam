package  scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ログイン中の学校情報を取得
        School school = (School) request.getSession().getAttribute("school");

        // 削除対象のCDを取得
        String cd = request.getParameter("cd");

        // Subjectインスタンスを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setSchool(school);

        // 削除処理
        SubjectDAO dao = new SubjectDAO();
        boolean success = dao.delete(subject);

        // メッセージを設定
        if (success) {
            request.setAttribute("message", "科目を削除しました。");
        } else {
            request.setAttribute("message", "科目の削除に失敗しました。");
        }

        // リダイレクト先を指定（一覧へ戻るなど）
        return "subject_result.jsp";
    }
}