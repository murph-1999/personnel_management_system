package dao.person;

import domain.Person;
import util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowDao {
    public List<Person> query() {
        ResultSet res = null;
        Statement stat = null;
        Connection conn = null;
        List<Person> list = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql= "select * from person";
            stat = conn.createStatement();
            res = stat.executeQuery(sql);

            list = new ArrayList<Person>();
            while(res.next()){
                Person person = null;
                int id = res.getInt("id");
                String name = res.getString("name");
                int age = res.getInt("age");
                person = new Person();
                person.setId(id);
                person.setName(name);
                person.setAge(age);
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConnection(res,stat,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Person> queryFilter(String personname, int personage) {
        ResultSet res = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        List<Person> list = null;
        try {
            String sql = null;
            conn = JDBCUtils.getConnection();
            if(personname == null && personage != 0){
                sql = "select * from person where age = ?";
                pstmt = conn.prepareStatement(sql) ;
                pstmt.setInt(1,personage);

            } else if(personname != null && personage == 0){
                sql = "select * from person where name = ?";
                pstmt = conn.prepareStatement(sql) ;
                pstmt.setString(1,personname);

            } else{
                sql= "select * from person where name = ? and age = ?";
                pstmt = conn.prepareStatement(sql) ;
                pstmt.setString(1,personname);
                pstmt.setInt(2,personage);
            }

            res = pstmt.executeQuery();

            list = new ArrayList<Person>();
            while(res.next()){
                Person person = null;
                int id = res.getInt("id");
                String name = res.getString("name");
                int age = res.getInt("age");
                person = new Person();
                person.setId(id);
                person.setName(name);
                person.setAge(age);
                list.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.closeConnection(res,pstmt,conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
