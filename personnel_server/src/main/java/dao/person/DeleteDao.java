package dao.person;

import util.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDao {

    public void delete(int id){
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql= "delete from person where id = ?";
            pstmt = conn.prepareStatement(sql) ;
            pstmt.setInt(1,id);
            int count =  pstmt.executeUpdate();
            System.out.println("一共删除了" + count + "条");
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
