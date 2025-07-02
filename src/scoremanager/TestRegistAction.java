package scoremanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.SubjectDAO;
import tool.Action;

public class TestRegistAction extends Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession(false);
	    Teacher teacher = (session != null) ? (Teacher) session.getAttribute("teacher") : null;

	    if (teacher == null || teacher.getSchool() == null || teacher.getSchool().getCd() == null) {
	        req.setAttribute("error", "ログイン情報が不正です。");
	        return "error.jsp";
	    }

	    String schoolCd = teacher.getSchool().getCd();

	    // 各リストを作成
	    List<ClassNum> classList = new ClassNumDAO().filter(schoolCd);
	    List<Subject> subjectList = new SubjectDAO().filter(schoolCd);

	    List<Integer> countList = new ArrayList<>();
	    for (int i = 1; i <= 5; i++) {
	        countList.add(i);
	    }

	    List<Integer> yearList = new ArrayList<>();
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    for (int y = currentYear - 5; y <= currentYear; y++) {
	        yearList.add(y);
	    }

	    req.setAttribute("classList", classList);
	    req.setAttribute("subjectList", subjectList);
	    req.setAttribute("countList", countList);
	    req.setAttribute("yearList", yearList);

	    return "test_regist.jsp";
	}}


