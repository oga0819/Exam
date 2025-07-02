package scoremanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;

public class SubjectCreateExecuteAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        // 入力値取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        School school = (School) req.getSession().getAttribute("school");

        // 入力値設定
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        try {
            SubjectDAO dao = new SubjectDAO();
            // 重複チェック（既存科目の有無）
            Subject exists = dao.get(cd, school);
            if (exists != null) {
                req.setAttribute("error", "その科目コードは既に登録されています。");
                req.setAttribute("subject", subject);
                // 新規登録画面へ戻す
                req.getRequestDispatcher("/WebContent/scoremanager/subject_create.jsp").forward(req, res);
                return;
            }

            // 新規登録
            boolean result = dao.save(subject);
            if (result) {
                // 完了画面へ
                req.getRequestDispatcher("/WebContent/scoremanager/subject_create_done.jsp").forward(req, res);
            } else {
                req.setAttribute("error", "登録に失敗しました。");
                req.setAttribute("subject", subject);
                req.getRequestDispatcher("/WebContent/scoremanager/subject_create.jsp").forward(req, res);
            }
        } catch (Exception e) {
            req.setAttribute("error", "エラーが発生しました: " + e.getMessage());
            req.setAttribute("subject", subject);
            req.getRequestDispatcher("/WebContent/scoremanager/subject_create.jsp").forward(req, res);
        }
    }

    // GETリクエストは新規画面へリダイレクト
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        res.sendRedirect("SubjectCreate.action");
    }
}