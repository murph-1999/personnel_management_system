package web.user;

import com.alibaba.fastjson.JSONObject;
import dao.user.UserDao;
import domain.User;
import util.GetParamsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(1);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        BufferedReader reader = req.getReader();
        String readerStr = "";
        String line;

        while ((line = reader.readLine()) != null){
            readerStr = readerStr.concat(line);
        }
        System.out.println(readerStr);

        User loginUser = new User();

        String username =  GetParamsUtil.getParam(readerStr,"username");
        System.out.println(username);
        String password =  GetParamsUtil.getParam(readerStr,"password");

        loginUser.setUsername(username);
        loginUser.setPassword(password);
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        if(user == null){
            //返回错误码
            resp.setStatus(401);
            Map<String,Object> map = new HashMap<>();
            map.put("code",401);
            map.put("list",loginUser);
            JSONObject object = new JSONObject(map);
            resp.getWriter().println(object);
        }else{
            //登录成功，存储user
            req.setAttribute("user",user);
            //设置成功码
            resp.setStatus(200);
            Map<String,Object> map = new HashMap<>();
            map.put("code",200);
            map.put("list",user);
            String msg = "login success";
            map.put("msg",msg);
            JSONObject object = new JSONObject(map);
            resp.getWriter().println(object);
        }
    }
}
