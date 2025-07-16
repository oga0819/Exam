package scoremanager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.DAO;

public class TestDeleteExecuteAction extends DAO {

    /**
     * 科目コードと回数をキーにTESTテーブルのレコードを削除する
     * @param subjectCd 科目コード
     * @param no 回数
     * @return 削除成功:true, 失敗:false
     * @throws Exception
     */
    public boolean deleteTest(String subjectCd, int no) throws Exception {
        String sql = "DELETE FROM TEST WHERE subject_cd = ? AND no = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subjectCd);
            ps.setInt(2, no);

            int count = ps.executeUpdate();

            return count > 0;
        }
    }
}
