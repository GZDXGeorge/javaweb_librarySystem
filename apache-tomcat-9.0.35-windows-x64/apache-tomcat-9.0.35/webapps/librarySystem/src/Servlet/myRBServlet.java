package Servlet;

import DB.RBDao.RB;
import DB.RBDao.rBSearchDaoImpl;
import DB.book.book;
import DB.myRBClass;
import DB.netUser.netUser;
import Service.RBService.rBAddServiceImpl;
import Service.RBService.rBDeleteServiceImpl;
import Service.RBService.rBSearchServiceImpl;
import Service.bookService.bookSearchServiceImpl;
import Service.netUserService.LoginServiceImpl;
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
import java.util.regex.Pattern;

@WebServlet(name = "myRBServlet")
public class myRBServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if (task != null)
            System.out.println("service task=" + task);
        else
            System.out.println("service task=" + task);
        if(task!=null){
            switch(task){
                case "queryAll":queryAll(request,response);break;
                case "queryByPage":queryByPage(request,response);break;
                case "delete":delete(request,response);break;
                default:break;
            }
        }

    }
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session =request.getSession();
        String uNo= (String)session.getAttribute("Uno");
        try {
            List<RB>lst = new ArrayList<>();
            List<myRBClass>lst1 = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            if(uNo ==""  || uNo==null) {
                map.put("total", lst.size());
                map.put("rows", lst);
            }
            else {
                    lst = new rBSearchServiceImpl().search_byUNo(uNo);
                    book b = null;
                    myRBClass m = null;
                    for (int i = 0; i < lst.size(); i++) {
                        b = new bookSearchServiceImpl().getBookJsonByBno(lst.get(i).getBNo());
                        m = new myRBClass(lst.get(i).getRbDate(), b.getBNo(), b.getBName(), b.getBAuthor(), b.getPicutreUrl());
                        lst1.add(m);
                    }
                map.put("total", lst1.size());
                map.put("rows", lst1);
            }
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bNo传值");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void queryByPage(HttpServletRequest Request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Request.setCharacterEncoding("UTF-8");
            HttpSession session =Request.getSession();
            String uNo= (String)session.getAttribute("Uno");
            int pageNow, pageSize;
            pageNow  = Integer.parseInt(Request.getParameter("page"));     //第几页的数据
            pageSize = Integer.parseInt(Request.getParameter("rows"));  //每页多少条数据
            System.out.println(pageNow+" "+pageSize);
            List<RB>lst = new rBSearchServiceImpl().getRBJsonByPage(pageNow,pageSize,uNo);
            List<myRBClass>lst1 = new ArrayList<>();
            book b = null;
            myRBClass m = null;
            for (int i = 0; i < lst.size(); i++) {
                b = new bookSearchServiceImpl().getBookJsonByBno(lst.get(i).getBNo());
                m = new myRBClass(lst.get(i).getRbDate(), b.getBNo(), b.getBName(), b.getBAuthor(), b.getPicutreUrl());
                lst1.add(m);
            }
            int total =new rBSearchDaoImpl().getRBSum(uNo);
            Map<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("rows", lst1);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("分页传值");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setCharacterEncoding("UTF-8");
            HttpSession session =request.getSession();
            String uNo= (String)session.getAttribute("Uno");

            String bNo = request.getParameter("BNo");
            boolean aboolean =new rBDeleteServiceImpl().doDelete(uNo,bNo);
            PrintWriter out = response.getWriter();
            if(aboolean == false){
                System.out.println("删值失败");
                out.append("del-failed");
            }
            else {
                out.append("del-ok");
                System.out.println("删值成功");
            }
            out.flush();
            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
