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
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        School school = teacher.getSchool();

        // フォームからパラメータ取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");
        boolean isAttend = Boolean.parseBoolean(request.getParameter("is_attend"));

        // Studentインスタンス生成しプロパティセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setIsAttend(isAttend);
        student.setSchool(school);

        // 登録実行
        StudentDAO studentDAO = new StudentDAO();
        boolean result = studentDAO.save(student);

        if (!result) {
            request.setAttribute("error", "登録に失敗しました。");
            return "student_create.jsp";
        }

        // 一覧画面へリダイレクト
        return "student_list.action";
    }
}