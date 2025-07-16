package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    protected String baseSql = "SELECT st.name AS student_name, t.subject_cd, s.name AS subject_name, t.no, t.point "
            + "FROM TEST t "
            + "JOIN STUDENT st ON t.student_no = st.no "
            + "JOIN SUBJECT s ON t.subject_cd = s.cd";

    protected List<TestListStudent> postFilter(ResultSet rs) throws SQLException {
        List<TestListStudent> result = new ArrayList<>();
        while (rs.next()) {
            TestListStudent tls = new TestListStudent();
            tls.setSubjectCd(rs.getString("subject_cd"));
            tls.setSubjectName(rs.getString("subject_name"));
            tls.setNum(rs.getInt("no"));
            tls.setPoint(rs.getInt("point"));
            result.add(tls);
        }
        return result;
    }

    /**
     * Studentオブジェクトの情報を使い、該当する成績リストを返す
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        StringBuilder sql = new StringBuilder(baseSql + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 学生番号で絞り込み
        if (student.getNo() != null && !student.getNo().isEmpty()) {
            sql.append(" AND t.student_no = ?");
            params.add(student.getNo());
        }

        // 名前などで絞り込みたい場合、Studentにフィールドがあれば追加可能

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
