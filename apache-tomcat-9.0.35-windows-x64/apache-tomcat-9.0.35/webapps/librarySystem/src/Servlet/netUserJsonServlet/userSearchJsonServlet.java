package Servlet.netUserJsonServlet;

import DB.netUser.RegisterDaoImpl;
import DB.netUser.netUser;
import Service.netUserService.*;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "userSearchJsonServlet")
public class userSearchJsonServlet extends HttpServlet {
    @Override
    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String task = request.getParameter("task");
        if(task!=null)
            System.out.println("service"+task);
        else
            System.out.println("task="+task);

        if(task!=null){
            switch (task){
                case "update":update(request,response);break;
                case "delete":  delete(request,response);break;
                case "insert": insert(request,response);break;
                case "ByUNo":query_ByUNo(request,response);break;
                case "ByName":query_ByUserName(request,response);break;
                case "ByJob":query_ByUserJob(request,response);break;
                case "BySex":query_ByUserSex(request,response); break;
                case "ByAge":query_ByUserAge(request,response); break;
                case "queryByPage":query_ByPage(request,response);break;
                default:queryAll(request,response);break;
            }
        }
        else{
            queryAll(request,response);
        }
    }
    private void queryAll(HttpServletRequest Request, HttpServletResponse response){
        try {
            List<netUser> lst = new getUserJsonServiceImpl().getUserJson();
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("全部传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response){
        //保存更新的数据
        try {
            request.setCharacterEncoding("UTF-8");
            String uNo = request.getParameter("uNo");
            String userName = request.getParameter("userName");
            String userPassword = request.getParameter("userPassword");
            String userJob = request.getParameter("userJob");
            String usex = request.getParameter("usex");
            String uage = request.getParameter("uage");

            netUser user = new netUser();
            user.setUNo(uNo);
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setUserJob(userJob);
            user.setUsex(usex);
            user.setUage(Integer.parseInt(uage));
            System.out.println(user);
            System.out.println(new UpdateServiceImpl().doUpdate(user));
            PrintWriter out = response.getWriter();
            out.append("update-ok");
            out.flush();
            out.close();
            System.out.println("改值");
            return ;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response){
        try{
            request.setCharacterEncoding("UTF-8");
            String uNo = request.getParameter("uNo");
            System.out.println(new DeleteServiceImpl().doDelete(uNo));
            PrintWriter out = response.getWriter();
            out.append("del-ok");
            out.flush();
            out.close();
            System.out.println("删值");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void insert(HttpServletRequest req, HttpServletResponse resp){
        //保存更新的数据
        try {
            req.setCharacterEncoding("UTF-8");
            String uNo = req.getParameter("uNo");
            String uName = req.getParameter("userName");
            String uPassWord = req.getParameter("userPassword");
            int uAge=Integer.valueOf(req.getParameter("uage"));
            String uJob = req.getParameter("userJob");
            String uSex = req.getParameter("usex");

            LoginServiceImpl ls = new LoginServiceImpl();
            netUser user = ls.checkLoginService(uNo);

            Map<String, Object> map = new HashMap<>();
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();

            if(user==null) {
                netUser USER = new netUser();
                USER.setUNo(uNo);
                USER.setUserName(uName);
                USER.setUserPassword(uPassWord);
                USER.setUage(uAge);
                USER.setUserJob(uJob);
                USER.setUsex(uSex);
                System.out.println(USER);
                boolean doInsertOk = new RegisterDaoImpl().doInsert(USER);
                if(doInsertOk==true) {
                    map.put( "success",true);
                    map.put( "message", "ok");
                    System.out.println("插入用户记录成功");
                }
                else{
                    map.put( "success",false);
                    map.put( "message", "Error");
                    System.out.println("插入用户记录失败");
                }
            }
            else{
                map.put( "success",false);
                map.put( "message", "Exist");
                System.out.println("用户记录已经存在");
            }

            JSONObject json = JSONObject.fromObject(map);
            resp.setCharacterEncoding("utf-8");
            out = resp.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void query_ByUNo(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String uNo = request.getParameter("uNo");
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByUno(uNo);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            for(int i=0;i<lst.size();i++)
                System.out.println(lst.get(i));
            System.out.println("uNo传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void query_ByUserName(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String userName = request.getParameter("userName");
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByUserName(userName);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("userName传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void query_ByUserJob(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String userJob = request.getParameter("userJob");
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByUserJob(userJob);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("userJob传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void query_ByUserSex(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String userSex = request.getParameter("userSex");
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByUserSex(userSex);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("userSex传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void query_ByUserAge(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String userAge = request.getParameter("userAge");
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByUserAge(userAge);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("userAge传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void query_ByPage(HttpServletRequest Request, HttpServletResponse response){
        try {
            Request.setCharacterEncoding("UTF-8");
            int pageNow, pageSize;
            pageNow  = Integer.parseInt(Request.getParameter("page"));     //第几页的数据
            pageSize = Integer.parseInt(Request.getParameter("rows"));  //每页多少条数据
            List<netUser> lst = new getUserJsonServiceImpl().getUserJsonByPage( pageNow,pageSize);
            int total = new getUserJsonServiceImpl ().getUserSum();
            Map<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("分页传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
