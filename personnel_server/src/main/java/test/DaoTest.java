package test;

import com.alibaba.fastjson.JSONObject;
import dao.user.UserDao;
import domain.User;

public class DaoTest {
    public static void main(String[] args) {
//        new DaoTest().testLogin();

        System.out.println(getParam("id=1","id"));
    }
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUsername("Jack");
        loginuser.setPassword("123");
        UserDao dao = new UserDao ();
        User user = dao.login(loginuser);
        System.out.println(user.toString());
    }
    public static String getParam(String str,String key) {
        String rtn = null;
        String arr [] = str.split("&");
        for(String s : arr)
        {
            String keyInArr = s.split("=")[0];
            String value = s.split("=")[1];
            if(keyInArr.equals(key))
            {
                rtn = value;
                break;
            }
        }
        return rtn;
    }
}
