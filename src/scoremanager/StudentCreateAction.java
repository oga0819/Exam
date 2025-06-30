package scoremanager;

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
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ClassNumDAOのfilterはList<String>を返す
        ClassNumDAO cNumDao = new ClassNumDAO();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        // 他のActionと同じ属性名でセットしておく
        request.setAttribute("class_num_set", classNumList);

        // 必要に応じて他の初期値などもセット

        return "student_create.jsp";
    }
}