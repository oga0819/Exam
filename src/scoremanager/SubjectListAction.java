package scoremanager;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action; // インポートを忘れずに

public class SubjectListAction extends Action {
    private SubjectDAO subjectDao = new SubjectDAO();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        if (teacher == null) {
            return "login.jsp";
        }
        School school = teacher.getSchool();
        List<Subject> subjects = subjectDao.filter(school);
        req.setAttribute("subjects", subjects);
        return "subject_list.jsp";
    }
}