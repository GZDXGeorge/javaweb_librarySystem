package Servlet;

import DB.RBDao.RB;
import DB.book.book;
import DB.netUser.netUser;
import Service.RBService.rBAddServiceImpl;
import Service.RBService.rBSearchServiceImpl;
import Service.bookService.bookSearchServiceImpl;
import Service.netUserService.LoginServiceImpl;
import Service.netUserService.getUserJsonServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "showBookServlet")
public class showBookServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String task = request.getParameter("task");
            if(task!=null)
                System.out.println("service task="+task);
            else
                System.out.println("service task="+task);
            if(task!=null) {
                switch (task){
                    case "queryAll":queryAll(request,response);break;
                    case "queryByPage": queryByPage(request,response);break;
                    case "insert": insert(request,response);break;
                    case "queryByBName":queryByBName(request,response);break;
                    case "queryByBAuthor": queryByBAuthor(request,response);break;
                    case "searchJob":searchJob(request,response);break;
                    default:break;
                }

            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<book> plist = null;
        Map<String, Object> map = new HashMap<>();
        int pageSize = 3;
        int pageNow=1;
        String  pageSizeStr =  request.getParameter("pageSize");
        System.out.println("pageSizeStr="+pageSizeStr);
        if(!"".equals(pageSizeStr) && pageSizeStr != null)
            pageSize = Integer.parseInt(pageSizeStr);
        int totalItem = new bookSearchServiceImpl().getBookSum();

        System.out.println(pageNow+" "+pageSize);
        plist = new bookSearchServiceImpl().getBookJson();
        int lstLength = plist.size();
        pageSize = pageSize>lstLength?pageSize:lstLength;

        map.put("totalPage",1);//总页数//        map.put("totalItem",totalItem); //总元素数
        map.put("pageNow",1);
        map.put("pageSize", pageSize);   //每页的元素数
        map.put("bookList",plist);
        /*将list集合装换成json对象*/
        JSONObject data = JSONObject.fromObject(map);//JSONArray data = JSONArray.fromObject(map);
        //接下来发送数据
        /*设置编码，防止出现乱码问题*/
        response.setCharacterEncoding("utf-8");
        /*得到输出流*/
        PrintWriter respWritter = response.getWriter();
        /*将JSON格式的对象toString()后发送*/
        respWritter.append(data.toString());
        respWritter.flush();
        respWritter.close();
        System.out.println("书籍部分传值");
    }
    private void queryByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<book> plist = null;
        Map<String, Object> map = new HashMap<>();
        int pageSize = 3;
        int pageNow=1;
        String  pageNowStr = request.getParameter("pageNow");
        String  pageSizeStr =  request.getParameter("pageSize");
        System.out.println(pageNowStr+" "+pageSizeStr);

        if(!"".equals(pageNowStr) && pageNowStr != null)
            pageNow = Integer.parseInt(pageNowStr);

        if(!"".equals(pageSizeStr) && pageSizeStr != null)
            pageSize = Integer.parseInt(pageSizeStr);

        int totalItem = new bookSearchServiceImpl().getBookSum();

        System.out.println(pageNow+" "+pageSize);

        plist = new bookSearchServiceImpl().getBookJsonByPage( pageNow,pageSize);

        int totalPage = totalItem %pageSize==0? totalItem/pageSize:totalItem/pageSize+1;
        map.put("totalPage",totalPage);//总页数//        map.put("totalItem",totalItem); //总元素数
        map.put("pageNow",pageNow);
        map.put("pageSize",pageSize);   //每页的元素数
        map.put("bookList",plist);
        /*将list集合装换成json对象*/
        JSONObject data = JSONObject.fromObject(map);//JSONArray data = JSONArray.fromObject(map);
        //接下来发送数据
        /*设置编码，防止出现乱码问题*/
        response.setCharacterEncoding("utf-8");
        /*得到输出流*/
        PrintWriter respWritter = response.getWriter();
        /*将JSON格式的对象toString()后发送*/
        respWritter.append(data.toString());
        respWritter.flush();
        respWritter.close();
        System.out.println("书籍部分传值");
    }
    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存更新的数据
        try {
            req.setCharacterEncoding("UTF-8");
            HttpSession session =req.getSession();
            String uNo= (String)session.getAttribute("Uno");
            String bNo = req.getParameter("BNo");
            String rbDate = " ";

            LoginServiceImpl ls = new LoginServiceImpl();
            netUser user = ls.checkLoginService(uNo);
            book tmpBook = new bookSearchServiceImpl().getBookJsonByBno( bNo);

            RB tmp = new rBSearchServiceImpl().search_byBNo(bNo);//从书号看是否有记录

            Map<String, Object> map = new HashMap<>();
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();

            if(user==null || tmpBook==null){
                map.put( "success",false);
                map.put( "message", "NotExist");
                System.out.println("该用户或图书编号不存在");
            }else if(tmp!=null ){
                map.put( "success",false);
                map.put( "message", "Exist");
                System.out.println("该书的借书记录已经存在");
            }
            else{
                rBAddServiceImpl rbService =new rBAddServiceImpl();
                RB rb =new RB();
                rb.setBNo(bNo);
                rb.setUNo(uNo);
                rb.setRbDate(rbDate);
                boolean doInsertOk = rbService.doInsert(rb);
                if(doInsertOk==true) {
                    map.put( "success",true);
                    map.put( "message", "ok");
                    System.out.println("插入借书记录成功");
                }
                else{
                    map.put( "success",false);
                    map.put( "message", "Error");
                    System.out.println("插入借书记录失败");
                }
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
    private void queryByBName(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bName = request.getParameter("bName");
            String  pageSizeStr =  request.getParameter("pageSize");
            List<book> lst = new bookSearchServiceImpl ().getBookJsonByBName(bName);
            int pageSize = 3;
            if(pageSizeStr!=null && !pageSizeStr.equals("") )
                pageSize = Integer.parseInt(pageSizeStr);
            int lstLength = lst.size();
            pageSize = pageSize>lstLength?pageSize:lstLength;
            Map<String, Object> map = new HashMap<>();
            map.put("bookList", lst);
            map.put("totalPage",1);//总页数//
            map.put("pageSize",pageSize);
            map.put("pageNow",1);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bName传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void queryByBAuthor(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
            String bAuthor = request.getParameter("bAuthor");
            String  pageSizeStr =  request.getParameter("pageSize");
            List<book> lst =new bookSearchServiceImpl ().getBookJsonByBAuthor(bAuthor);
            int pageSize =3;
            if(pageSizeStr!=null && !pageSizeStr.equals("") )
                pageSize = Integer.parseInt(pageSizeStr);
            int lstLength = lst.size();
            pageSize = pageSize>lstLength?pageSize:lstLength;
            Map<String, Object> map = new HashMap<>();
            map.put("bookList", lst);
            map.put("totalPage",1);//总页数//
            map.put("pageSize",pageSize);
            map.put("pageNow",1);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bAuthor传值");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void searchJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session =request.getSession();
        String uNo= (String)session.getAttribute("Uno");
        netUser user = new getUserJsonServiceImpl().getUserJsonByUno(uNo).get(0);
        if(user.getUserJob().equals("common"))
            response.sendRedirect("http://localhost:8888/hello/jsp/commonRB.jsp");
        else if(user.getUserJob().equals("special"))
            response.sendRedirect("http://localhost:8888/hello/jsp/specialJsp/myRB.jsp");
    }
}
