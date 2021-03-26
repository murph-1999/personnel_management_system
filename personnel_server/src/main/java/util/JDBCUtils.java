package util;
import java.sql.*;

public class JDBCUtils {
    private static String url = "jdbc:mysql://121.5.101.19:3306/personnel_management?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
    private static String user = "root";
    private static String password = "123456";
    private static String driver= "com.mysql.jdbc.Driver";
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

//    private static DataSource ds ;
//
//    static {
//
//        try {
//            Properties pro = new Properties();
//            //加载配置文件
//            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//////            FileInputStream is = new FileInputStream(new File("src/druid.properties"));
//            pro.load(is);
//
//            //初始化连接池对象
//            ds = DruidDataSourceFactory.createDataSource(pro);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 获取连接池对象
     */
//    public static DataSource getDataSource(){
//        return ds;
//    }


    /**
     * 获取连接Connection对象
     */
//    public static Connection getConnection() throws SQLException {
//        return  ds.getConnection();
//    }

    //释放资源
    public static void closeConnection(Statement stat, Connection conn) throws SQLException {
            if(stat != null){
                try{
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    }

    //重载，释放资源
    public static void closeConnection(ResultSet res,Statement stat, Connection conn) throws SQLException {
        if(res != null){
            try{
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stat != null){
            try{
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
