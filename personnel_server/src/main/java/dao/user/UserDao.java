package dao.user;

import domain.User;
import org.springframework.dao.DataAccessException;
import util.JDBCUtils;

import java.sql.*;

public class UserDao {

    public User login(User loginUser){
        Connection conn = null;
        ResultSet res = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from account where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql) ;
            pstmt.setString(1,loginUser.getUsername()) ;
            pstmt.setString(2,loginUser.getPassword()) ;
            res = pstmt.executeQuery();
            if(res.next()){
                User user = new User();
                user.setUsername(loginUser.getUsername());
                user.setPassword(loginUser.getPassword());
                System.out.println("登陆成功");
                return user;
            }
        } catch (DataAccessException | SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
