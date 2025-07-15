package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDAO extends DAO{

	protected String baseSql =
		    "SELECT t.subject_cd, s.name AS subject_name, t.no, t.point " +
		    "FROM TEST t " +
		    "JOIN SUBJECT s ON t.subject_cd = s.cd";


    protected List<TestListStudent> postFilter(ResultSet rs) throws SQLException {
        List<TestListStudent> result = new ArrayList<>();
        while (rs.next()) {
        	TestListStudent test = new TestListStudent();
            test.setSubjectCd(rs.getString("subject_cd"));
            test.setName(rs.getString("subject_name"));
            test.setNo(rs.getInt("no"));
            test.setPoint(rs.getInt("point"));
            result.add(test);
        }
        return result;
    }

    public List<TestListStudent> filter(TestListStudent target) throws Exception {
        StringBuilder sql = new StringBuilder(baseSql + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (target.getName() != null && !target.getName().isEmpty()) {
            sql.append(" AND name = ?");
            params.add(target.getName());
        }
        if (target.getSubjectCd() != null && !target.getSubjectCd().isEmpty()) {
            sql.append(" AND subject_cd = ?");
            params.add(target.getSubjectCd());
        }
        if (target.getNo() != null) {
            sql.append(" AND no = ?");
            params.add(target.getNo());
        }
        if (target.getPoint() != null) {
            sql.append(" AND point = ?");
            params.add(target.getPoint());
        }

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }
            ResultSet rs = st.executeQuery();
            return postFilter(rs);
        }
    }
}