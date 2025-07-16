package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

<<<<<<< HEAD
	protected String baseSql =
		    "SELECT t.student_no, t.class_num, t.no, t.point, t.subject_cd, s.name AS subject_name, " +
		    "       st.name AS student_name, st.ent_year " +
		    "FROM TEST t " +
		    "JOIN subject s ON t.subject_cd = s.cd " +
		    "JOIN student st ON t.student_no = st.NO"; //studentテーブル結合
=======
    protected String baseSql =
        "SELECT t.name, t.subject_cd, s.name AS subject_name, t.no, t.point, t.class_num " +
        "FROM TEST t JOIN subject s ON t.subject_cd = s.cd";
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git

    protected List<TestListSubject> postFilter(ResultSet rs) throws Exception {
        List<TestListSubject> result = new ArrayList<>();
        Map<String, TestListSubject> studentMap = new HashMap<>(); //studentNoをキーに統合

        while (rs.next()) {
<<<<<<< HEAD
            String studentNo = rs.getString("student_no");
            TestListSubject record = studentMap.get(studentNo);

            if (record == null) {
                record = new TestListSubject();
                record.setEntYear(rs.getInt("ent_year"));
                record.setStudentNo(studentNo);
                record.setStudentName(rs.getString("student_name"));
                record.setClassNum(rs.getString("class_num"));
                record.setPoints(new HashMap<>());

                studentMap.put(studentNo, record);
                result.add(record);
            }

            //テスト回と点数をMapに追加
            int testNo = rs.getInt("no");
            int point = rs.getInt("point");
            record.getPoints().put(testNo, point);
=======
            TestListStudent record = new TestListStudent();
            record.setName(rs.getString("name"));
            record.setSubjectCd(rs.getString("subject_cd"));
            record.setNo(rs.getInt("no"));
            record.setPoint(rs.getInt("point"));
            // もしTestListStudentにclassNumのフィールドがあれば設定する
            // record.setClassNum(rs.getString("class_num"));
            result.add(record);
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git
        }

        return result;
    }

    /**
     * 入学年度とクラス番号、科目、スクールを必須条件として成績を取得。
     */
    public List<TestListSubject> filter(int entYear, String classNum, String subjectCd, School school) throws Exception {
        if (subjectCd == null || subjectCd.isEmpty()) {
            throw new IllegalArgumentException("subjectCd（科目コード）は必須です");
        }
        if (classNum == null || classNum.isEmpty()) {
            throw new IllegalArgumentException("classNum（クラス番号）は必須です");
        }

        StringBuilder sql = new StringBuilder(baseSql + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        //必須条件
        sql.append(" AND t.subject_cd = ?");
        params.add(subjectCd);

        sql.append(" AND t.class_num = ?");
        params.add(classNum);

<<<<<<< HEAD
        sql.append(" AND st.ent_year = ?");
        params.add(entYear);
=======
        // 任意の条件
        if (target != null) {
            if (target.getName() != null && !target.getName().isEmpty()) {
                sql.append(" AND t.name = ?");
                params.add(target.getName());
            }
            if (target.getNo() != null) {
                sql.append(" AND t.no = ?");
                params.add(target.getNo());
            }
            if (target.getPoint() != null) {
                sql.append(" AND t.point = ?");
                params.add(target.getPoint());
            }
        }
>>>>>>> branch 'master' of https://github.com/oga0819/Exam.git

        try (Connection con = getConnection();PreparedStatement st = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                st.setObject(i + 1, params.get(i));
            }
            ResultSet rs = st.executeQuery();

            return postFilter(rs);
        }
    }
}
