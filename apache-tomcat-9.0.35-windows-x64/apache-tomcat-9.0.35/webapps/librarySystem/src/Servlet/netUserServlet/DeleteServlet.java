package Servlet.netUserServlet;
import DB.netUser.netUser;
import Service.netUserService.DeleteServiceImpl;
import Service.netUserService.LoginServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应编码格式
        // resp.setCharacterEncoding("text/html;charset=UTF-8");
        //获取请求信息
        req.setCharacterEncoding("UTF-8");
        String uName = req.getParameter("Uname");
        String uNo = req.getParameter("Uno");
        String uPassWord = req.getParameter("Upassword");
        //处理请求信息
        //获取业务层对象
        LoginServiceImpl ls = new LoginServiceImpl();
        netUser user = ls.checkLoginService(uNo);
        PrintWriter out =null;
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        out = resp.getWriter();
        System.out.println(user);
        //响应处理结果
        if(user != null){
            System.out.println("用户已存在数据库");
            boolean doDeleteOK= new DeleteServiceImpl().doDelete(uNo,uName,uPassWord);
            if(doDeleteOK==true){

                Cookie cookie =new Cookie("uNo","");
                cookie.setMaxAge(0);
                cookie.setMaxAge(0);//删除
                cookie.setPath("http://localhost:8888/hello/jsp/login.jsp");
                resp.addCookie(cookie);
                System.out.println("用户注销成功");
                out.print("<script>alert('用户"+uName+"已经注销成功，请返回登录!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
            }else{
                System.out.println("用户注销异常");
                out.print("<script>alert('用户删除异常，请重新删除!');window.location.href='http://localhost:8888/hello/jsp/ResetAccount.jsp'</script>\"");
            }
        }
        else{
            System.out.println("用户不存在数据库");
            out.print("<script>alert('用户不存在数据库，请重新删除!');window.location.href='http://localhost:8888/hello/jsp/ResetAccount.jsp'</script>\"");
        }
    }

}
