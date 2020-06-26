package Servlet.netUserServlet;
import DB.netUser.netUser;
import Service.netUserService.LoginServiceImpl;
import Service.netUserService.UpdateServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


public class UpdateServlet extends HttpServlet {
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
            boolean doUpdateOK= new UpdateServiceImpl().doUpdate(uNo,uName,uPassWord);
            if(doUpdateOK==true){
                System.out.println("用户密码修改成功");
                out.print("<script>alert('用户"+uName+"已经成功修改，请登录!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
                return ;
            }else{
                System.out.println("用户修改出错，请重修修改");
                out.print("<script>alert('用户修改出错，请重新修改!');window.location.href='http://localhost:8888/hello/jsp/ResetAccount.jsp'</script>\"");
                return ;
            }
        }
        else{
            System.out.println("用户不存在数据库");
            out.print("<script>alert('用户不存在数据库，请重新修改!');window.location.href='http://localhost:8888/hello/jsp/ResetAccount.jsp'</script>\"");
            return;
        }

    }

}
