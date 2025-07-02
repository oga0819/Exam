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

    // 削除
    public boolean delete(Subject subject) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = getConnection();
            String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";
            st = con.prepareStatement(sql);
            st.setString(1, subject.getCd());
            st.setString(2, subject.getSchool().getCd());
            int result = st.executeUpdate();
            return result > 0;
        } finally {
            if (st != null) try { st.close(); } catch (Exception ex) {}
            if (con != null) try { con.close(); } catch (Exception ex) {}
        }
    }
}