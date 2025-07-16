package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    // 主キーで1件取得
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT cd, name, school_cd FROM subject WHERE cd = ? AND school_cd = ?";
            st = con.prepareStatement(sql);
            st.setString(1, cd);
            st.setString(2, school.getCd());
            rs = st.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school); // 既にschool渡してる場合
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ex) {}
            if (st != null) try { st.close(); } catch (Exception ex) {}
            if (con != null) try { con.close(); } catch (Exception ex) {}
        }
        return subject;
    }

    // 指定schoolの科目一覧
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            String sql = "SELECT cd, name FROM subject WHERE school_cd = ?";
            st = con.prepareStatement(sql);
            st.setString(1, school.getCd());
            rs = st.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception ex) {}
            if (st != null) try { st.close(); } catch (Exception ex) {}
            if (con != null) try { con.close(); } catch (Exception ex) {}
        }
        return list;
    }

    // INSERTまたはUPDATE
    public boolean save(Subject subject) throws Exception {
        // ここでは新規登録のみ実装（既存ならUPDATEに変更）
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = getConnection();
            // すでに存在するかチェック
            String checkSql = "SELECT COUNT(*) FROM subject WHERE cd = ? AND school_cd = ?";
            st = con.prepareStatement(checkSql);
            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchool().getCd());
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            st.close();

            if (count == 0) {
                String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
                st = con.prepareStatement(sql);
                st.setString(1, subject.getCd());
                st.setString(2, subject.getName());
                st.setString(3, subject.getSchool().getCd());
                int result = st.executeUpdate();
                return result > 0;
            } else {
                String sql = "UPDATE subject SET name = ? WHERE cd = ? AND school_cd = ?";
                st = con.prepareStatement(sql);
                st.setString(1, subject.getName());
                st.setString(2, subject.getCd());
                st.setString(3, subject.getSchool().getCd());
                int result = st.executeUpdate();
                return result > 0;
            }
        } finally {
            if (st != null) try { st.close(); } catch (Exception ex) {}
            if (con != null) try { con.close(); } catch (Exception ex) {}
        }
    }

 // subject に紐づく test レコードを先に削除し、次に subject を削除（すべてこのメソッド内で完結）
    public boolean delete(Subject subject) throws Exception {
        Connection con = null;
        PreparedStatement stTest = null;
        PreparedStatement stSubject = null;
        try {
            con = getConnection();
            con.setAutoCommit(false); // トランザクション開始

            // ① test テーブルの関連データを削除
            String deleteTestSql = "DELETE FROM test WHERE subject_cd = ? AND school_cd = ?";
            stTest = con.prepareStatement(deleteTestSql);
            stTest.setString(1, subject.getCd());
            stTest.setString(2, subject.getSchool().getCd());
            stTest.executeUpdate(); // 関連がなくてもOK（削除件数は確認しない）

            // ② subject テーブルのデータを削除
            String deleteSubjectSql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";
            stSubject = con.prepareStatement(deleteSubjectSql);
            stSubject.setString(1, subject.getCd());
            stSubject.setString(2, subject.getSchool().getCd());
            int subjectDeleted = stSubject.executeUpdate();

            con.commit(); // 両方成功したらコミット
            return subjectDeleted > 0; // 削除成功したか返す
        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (Exception ex) {}
            throw e; // 呼び出し元で処理
        } finally {
            if (stTest != null) try { stTest.close(); } catch (Exception ex) {}
            if (stSubject != null) try { stSubject.close(); } catch (Exception ex) {}
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (Exception ex) {}
        }
    }
}