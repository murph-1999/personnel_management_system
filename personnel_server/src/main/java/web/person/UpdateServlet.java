package web.person;

import com.alibaba.fastjson.JSONObject;
import dao.person.UpdateDao;
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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        int id = Integer.parseInt(GetParamsUtil.getParam(readerStr,"id"));
        int age = Integer.parseInt(GetParamsUtil.getParam(readerStr,"age"));
        UpdateDao dao = new UpdateDao();
        dao.update(id,age);
        resp.setStatus(200);
        Map<String,Object> map = new HashMap<>();
        String msg = "update done";
        map.put("code",200);
        map.put("msg",msg);
        JSONObject object = new JSONObject(map);
        resp.getWriter().println(object);
    }
}
