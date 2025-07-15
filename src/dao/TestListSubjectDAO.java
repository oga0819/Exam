package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListSubjectDAO extends DAO {

    protected String baseSql =
        "SELECT t.name, t.subject_cd, s.name AS subject_name, t.no, t.point, t.class_num " +
        "FROM TEST t JOIN subject s ON t.subject_cd = s.cd";

    protected List<TestListStudent> postFilter(ResultSet rs) throws Exception {
        List<TestListStudent> result = new ArrayList<>();
        while (rs.next()) {
            TestListStudent record = new TestListStudent();
            record.setName(rs.getString("name"));
            record.setSubjectCd(rs.getString("subject_cd"));
            record.setNo(rs.getInt("no"));
            record.setPoint(rs.getInt("point"));
            // もしTestListStudentにclassNumのフィールドがあれば設定する
            // record.setClassNum(rs.getString("class_num"));
            result.add(record);
        }
        return result;
    }

    /**
     * 科目コードとクラス番号を必須条件として成績を取得。
     * 他に任意の絞り込み条件があれば追加可能。
     */
    public List<TestListStudent> filter(String subjectCd, String classNum, TestListStudent target) throws Exception {
        if (subjectCd == null || subjectCd.isEmpty()) {
            throw new IllegalArgumentException("subjectCd（科目コード）は必須です");
        }
        if (classNum == null || classNum.isEmpty()) {
            throw new IllegalArgumentException("classNum（クラス番号）は必須です");
        }

        StringBuilder sql = new StringBuilder(baseSql + " WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 必須条件
        sql.append(" AND t.subject_cd = ?");
        params.add(subjectCd);

        sql.append(" AND t.class_num = ?");
        params.add(classNum);

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
