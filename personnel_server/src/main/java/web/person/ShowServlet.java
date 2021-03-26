package web.person;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import dao.person.ShowDao;
import dao.user.UserDao;
import domain.Person;
import util.GetParamsUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String name = null;
        int age = 0;
        ShowDao dao = new ShowDao();
        List<Person> list = null;
        if(req.getParameter("name") == "" && req.getParameter("age") == "") {
            list= dao.query();
        } else if(req.getParameter("name") == ""&&req.getParameter("age") != "") {
            age = Integer.parseInt(req.getParameter("age").trim());
            list= dao.queryFilter(null,age);
        } else if(req.getParameter("name") != ""&&req.getParameter("age") == "") {
            name = req.getParameter("name").trim();
            list= dao.queryFilter(name,age);
        } else {
            name = req.getParameter("name").trim();
            age = Integer.parseInt(req.getParameter("age").trim());
            list= dao.queryFilter(name,age);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("code" , 200);
        map.put("list" , list);
        JSONObject object = new JSONObject(map);
        resp.getWriter().println(object);
    }
}
