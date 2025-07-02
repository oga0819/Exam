package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        if (teacher == null) {
            request.setAttribute("error", "セッションが切れています。再度ログインしてください。");
            return "login.jsp";
        }
        School school = teacher.getSchool();

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        int entYear = 0;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "入学年度が正しくありません。");
            return "student_create.jsp";
        }
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null;

        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setIsAttend(isAttend);
        student.setSchool(school);

        StudentDAO studentDAO = new StudentDAO();
        boolean result;
        try {
            result = studentDAO.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データベース処理でエラーが発生しました。");
            return "student_create.jsp";
        }

        if (!result) {
            request.setAttribute("error", "登録に失敗しました。");
            return "student_create.jsp";
        }

        return "student_create_done.jsp";
    }
}