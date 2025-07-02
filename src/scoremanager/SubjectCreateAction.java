package scoremanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;

public class SubjectCreateAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        // 学校情報をセッションやリクエスト等から取得する想定
        School school = (School) req.getSession().getAttribute("school");

        // 新規登録画面用の空Subjectを作成
        Subject subject = new Subject();
        subject.setSchool(school);

        // リクエスト属性にセットしてJSPへ
        req.setAttribute("subject", subject);

        // 新規登録画面へフォワード
        req.getRequestDispatcher("/WebContent/scoremanager/subject_create.jsp").forward(req, res);
    }

    // POSTが来た場合もGETにフォワード（フォームからの直接POSTを防ぐため等）
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        doGet(req, res);
    }
}