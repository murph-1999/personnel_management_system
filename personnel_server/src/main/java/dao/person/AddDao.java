package dao.person;

import domain.Person;

import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddDao {
    public void insert(Person person) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql= "insert into person values(null,?,?)";
            pstmt = conn.prepareStatement(sql) ;
            pstmt.setString(1,person.getName());
            pstmt.setInt(2,person.getAge());
            int count = pstmt.executeUpdate();
            System.out.println("一共更新了" + count + "条");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConnection(pstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
