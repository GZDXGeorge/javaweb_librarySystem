package Servlet.RBJsonServlet;

import DB.RBDao.RB;
import DB.RBDao.rBSearchDaoImpl;
import DB.book.book;
import DB.netUser.netUser;
import Service.RBService.rBAddServiceImpl;
import Service.RBService.rBDeleteServiceImpl;
import Service.RBService.rBSearchServiceImpl;
import Service.bookService.bookSearchServiceImpl;
import Service.netUserService.LoginServiceImpl;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "rBJsonServlet")
public class rBJsonServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if(task!=null)
            System.out.println("service task="+task);
        else
            System.out.println("service task="+task);
        if(task!=null){
            switch(task){
                case "insert": insert(request,response);break;
                case "queryAll":queryAll(request,response);break;
                case "queryByPage":queryByPage(request,response);break;
                case "queryByBNo":queryByBNo(request,response);break;
                case "queryByUNo":queryByUNo(request,response);break;
                case "queryByRBDate":queryByRBDate(request,response);break;
                case "delete":delete(request,response);break;
                default:break;
            }
        }
    }
    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存更新的数据
        try {
            req.setCharacterEncoding("UTF-8");
            String uNo = req.getParameter("uNo");
            String bNo = req.getParameter("bNo");
            String rbDate = req.getParameter("rBDate")==null?" ":req.getParameter("rBDate");

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
                    rBAddServiceImpl  rbService =new rBAddServiceImpl();
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
    private void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            List<RB> lst = new rBSearchServiceImpl().getRBJson();
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("书籍全部传值");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void queryByPage(HttpServletRequest Request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Request.setCharacterEncoding("UTF-8");
            int pageNow, pageSize;
            pageNow  = Integer.parseInt(Request.getParameter("page"));     //第几页的数据
            pageSize = Integer.parseInt(Request.getParameter("rows"));  //每页多少条数据
            System.out.println(pageNow+" "+pageSize);
            List<RB>lst = new rBSearchServiceImpl().getRBJsonByPage(pageNow,pageSize);
            int total =new bookSearchServiceImpl().getBookSum();
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
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            request.setCharacterEncoding("UTF-8");
            String uNo = request.getParameter("UNo");
            String bNo = request.getParameter("BNo");
            boolean aboolean =new rBDeleteServiceImpl().doDelete(uNo,bNo);
            PrintWriter out = response.getWriter();
            if(aboolean == false){
                System.out.println("删值失败");
                out.append("del-failed");
            }else
                out.append("del-ok");
            out.flush();
            out.close();
            System.out.println("删值");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void queryByBNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            String bNo = request.getParameter("bNo");
            System.out.println("bNo="+bNo);
            List<RB>lst = new ArrayList<>();
            lst.add( new rBSearchServiceImpl().search_byBNo(bNo));
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bNo传值");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private void queryByUNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            String uNo = request.getParameter("uNo");

            List<RB>lst = new rBSearchServiceImpl().search_byUNo(uNo);
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
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
    private void queryByRBDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            request.setCharacterEncoding("UTF-8");
            String rbDate = request.getParameter("rBDate");
            System.out.println(rbDate);
            String  pattern = "^d{4}-d{2}-d{2}$";
            boolean isMatch = Pattern.matches(pattern,rbDate);
            List<RB> lst =null;
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            if(!isMatch){
                System.out.println("不正确的日期格式");
                lst = new ArrayList<>();
            }else {
                 lst = new rBSearchServiceImpl().search_byRBDate(rbDate);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("total", lst.size());
            map.put("rows", lst);
            JSONObject json = JSONObject.fromObject(map);
            out.append(json.toString());
            out.flush();
            out.close();
            System.out.println("bNo传值");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
