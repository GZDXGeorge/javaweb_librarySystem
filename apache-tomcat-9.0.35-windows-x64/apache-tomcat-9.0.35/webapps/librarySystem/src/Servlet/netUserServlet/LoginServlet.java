package Servlet.netUserServlet;
import DB.netUser.netUser;
import Service.netUserService.LoginServiceImpl;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应编码格式
       // resp.setCharacterEncoding("text/html;charset=UTF-8");
        //获取请求信息
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        String verifyCodeResult =(String) req.getSession().getAttribute("sessionCode");
        System.out.println("系统Session存储中验证码信息是"+verifyCodeResult);
        String verifyCode = req.getParameter("verifyCode");
        System.out.println("用户输入的验证码信息是"+verifyCode);
        if(!verifyCode.equalsIgnoreCase(verifyCodeResult)){
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            out = resp.getWriter();
            out.print("<script>alert('用户输入的验证码不正确，请检查重新输入!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
            return;
        }
        String uName = req.getParameter("Uname");
        String uNo = req.getParameter("Uno");
        String uPassWord = req.getParameter("Upassword");
        //处理请求信息
            //获取业务层对象
            LoginServiceImpl ls = new LoginServiceImpl();
            netUser user = ls.checkLoginService(uNo,uName,uPassWord);
            System.out.println(user);
        //响应处理结果
        if(user == null){
            System.out.println("用户不存在于数据库");
            //转发
            //处理意见
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            out = resp.getWriter();
            out.print("<script>alert('用户不存在或信息填不正确，请检查重新登录!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
            return;
        }
        else{
            //创建Cookie信息实现三天免登录
            //1、创建Cookie
            Cookie c= new Cookie("uNo",uNo);
            //2、设置Cookie的生命时长
            c.setMaxAge(3*24*60*60);//设置3天
            c.setPath("http://localhost:8888/hello/jsp/login.jsp");
            //3、保存Cookie
            resp.addCookie(c);
            System.out.println("欢迎登录成功");
            //4、成功登录则信息保存Session中，重定向到successLogin.jsp
            HttpSession session = req.getSession();
            //5、向Session存储用户名
            session.setAttribute("userName",user.getUserName());
            session.setAttribute("userJob",user.getUserJob());
            session.setAttribute("Uno",user.getUNo());
            //重定向
            if(user.getUserJob().equals("common"))
                resp.sendRedirect("http://localhost:8888/hello/jsp/successLogin.jsp");
            else if(user.getUserJob().equals("special"))
                resp.sendRedirect("http://localhost:8888/hello/jsp/specialJsp/userTable.jsp");
            return;
        }
    }

}
