package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDAO;
import tool.Action;

public class StudentCreateAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int currentYear = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
            entYearSet.add(i);
        }

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher"); // "teacher" に統一

        ClassNumDAO cNumDao = new ClassNumDAO();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("ent_year_set", entYearSet);

        return "student_create.jsp";
    }
}