package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListStudent;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            request.setAttribute("error", "ログインしてください。");
            return "error.jsp";
        }

        // 画面からのパラメータ取得
        String mode = request.getParameter("mode"); // "student" or "subjectClass"

        if ("subjectClass".equals(mode)) {
            // 科目＋クラスごと成績参照モード

            String subjectCd = request.getParameter("subjectCd");
            String classNum = request.getParameter("classNum");

            if (subjectCd == null || subjectCd.isEmpty() || classNum == null || classNum.isEmpty()) {
                request.setAttribute("error", "科目コードとクラス番号は必須です。");
                return "error.jsp";
            }

            TestListStudent target = new TestListStudent();

            String name = request.getParameter("name");
            if (name != null && !name.isEmpty()) {
                target.setName(name);
            }

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNum(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNum(null);
                }
            }

            String pointStr = request.getParameter("point");
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    target.setPoint(Integer.valueOf(pointStr));
                } catch (NumberFormatException e) {
                    target.setPoint(null);
                }
            }

            TestListSubjectDAO dao = new TestListSubjectDAO();
            List<TestListStudent> list = dao.filter(subjectCd, classNum, target);

            request.setAttribute("subjectClassTestList", list);
            request.setAttribute("searchTarget", target);
            request.setAttribute("subjectCd", subjectCd);
            request.setAttribute("classNum", classNum);

            return "subject_class_list.jsp";

        } else {
            // 学生別成績一覧モード（デフォルト）

            TestListStudent target = new TestListStudent();

            target.setName(request.getParameter("name"));
            target.setSubjectCd(request.getParameter("subjectCd"));

            String numStr = request.getParameter("num");
            if (numStr != null && !numStr.isEmpty()) {
                try {
                    target.setNum(Integer.valueOf(numStr));
                } catch (NumberFormatException e) {
                    target.setNum(null);
                }
            }

            String pointStr = request.getParameter("point");
            if (pointStr != null && !pointStr.isEmpty()) {
                try {
                    target.setPoint(Integer.valueOf(pointStr));
                } catch (NumberFormatException e) {
                    target.setPoint(null);
                }
            }

            TestListStudentDAO dao = new TestListStudentDAO();
            List<TestListStudent> list = dao.filter(target);

            request.setAttribute("studentTestList", list);
            request.setAttribute("searchTarget", target);

            return "studentlist.jsp";
        }
    }
}
