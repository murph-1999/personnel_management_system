package web.person;

import com.alibaba.fastjson.JSONObject;
import dao.person.DeleteDao;
import javafx.application.Application;
import javafx.stage.Stage;
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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 接收用户端传来的JSON字符串（body体里的数据）
        BufferedReader reader = req.getReader();
        String readerStr = "";
        String line;

        while ((line = reader.readLine()) != null){
            readerStr = readerStr.concat(line);
        }
        System.out.println(readerStr);

        if(GetParamsUtil.getParam(readerStr,"id") == null) {
            resp.setStatus(401);
            Map<String,Object> map = new HashMap<>();
            map.put("code",401);
            String msg = "delete failed";
            map.put("msg",msg);
            JSONObject object = new JSONObject(map);
            resp.getWriter().println(object);
        } else {
            int id = Integer.parseInt(GetParamsUtil.getParam(readerStr,"id"));
            DeleteDao dao = new DeleteDao();
            dao.delete(id);
            resp.setStatus(200);
            Map<String,Object> map = new HashMap<>();
            map.put("code",200);
            String msg = "delete done";
            map.put("msg",msg);
            JSONObject object = new JSONObject(map);
            resp.getWriter().println(object);}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
