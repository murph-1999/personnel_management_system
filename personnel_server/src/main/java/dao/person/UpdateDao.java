package dao.person;

import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {
    public void update(int id, int age) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql= "update person set age = ?  where id = ?";
            pstmt = conn.prepareStatement(sql) ;
            pstmt.setInt(1,age);
            pstmt.setInt(2,id);
            int count = pstmt.executeUpdate();
            System.out.println("一共更新了" + count + "条");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConnection(pstmt,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
