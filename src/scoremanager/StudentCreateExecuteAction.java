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
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher"); // "user" に統一
        School school = teacher.getSchool();

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null; // チェックボックス対策

        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setIsAttend(isAttend);
        student.setSchool(school);

        StudentDAO studentDAO = new StudentDAO();
        boolean result = studentDAO.save(student);

        if (!result) {
            request.setAttribute("error", "登録に失敗しました。");
            return "student_create.jsp";
        }

        return "student_create_done.jsp";
    }
}