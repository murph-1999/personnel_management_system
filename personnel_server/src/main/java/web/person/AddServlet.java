package web.person;

import com.alibaba.fastjson.JSONObject;
import dao.person.AddDao;
import domain.Person;
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

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");
//        BufferedReader reader = req.getReader();
//        String readerStr = "";
//        String line;
//        String name = null;
//        int age = 0;
//        while ((line = reader.readLine()) != null){
//            readerStr = readerStr.concat(line);
//        }
//        System.out.println(readerStr);
//        name = GetParamsUtil.getParam(readerStr,"name");
//        age = Integer.parseInt(GetParamsUtil.getParam(readerStr,"age"));

        String name = null;
        int age = 0;
        if(req.getParameter("age")!="" && req.getParameter("name")!="") {
            age = Integer.parseInt(req.getParameter("age"));
            name = req.getParameter("name");
            System.out.println("添加的姓名是："+name);
            System.out.println("添加的年龄是："+age);
            AddDao dao = new AddDao();
            Person person = new Person();
            person.setName(name);
            person.setAge(age);
            dao.insert(person);
            resp.setStatus(200);

            Map<String,Object> map = new HashMap<>();
            map.put("code",200);
            String msg = "add done";
            map.put("msg",msg);
            JSONObject object = new JSONObject(map);
            System.out.println(object);
            resp.getWriter().println(object);
        }

    }
}
