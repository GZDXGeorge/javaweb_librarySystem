package Servlet.netUserServlet;
import DB.netUser.RegisterDaoImpl;
import DB.netUser.netUser;
import Service.netUserService.LoginServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterServlet extends HttpServlet {
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
        int uAge=0;
        PrintWriter out = null;
        try {
            uAge = Integer.valueOf(req.getParameter("Uage"));           // NumberFormatException
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        String uJob = req.getParameter("Ujob");
        String uSex = req.getParameter("Usex");
        //处理请求信息
        //获取业务层对象
        LoginServiceImpl ls = new LoginServiceImpl();
        netUser user = ls.checkLoginService(uNo);
        System.out.println(user);
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        out = resp.getWriter();
        //响应处理结果
        if(user != null){
            System.out.println("用户已存在数据库");
            out.print("<script>alert('用户已经存在，请重新注册!');window.location.href='http://localhost:8888/hello/jsp/Register.jsp'</script>\"");
            return ;
        }else{
            boolean doInsertOk = new RegisterDaoImpl().doInsert(uNo,uName,uPassWord,uJob,uSex,uAge);
            if(doInsertOk==true){
                System.out.println("用户已经存入数据库");
                out.print("<script>alert('用户"+uName+"已经注册成功，请返回登录!');window.location.href='http://localhost:8888/hello/jsp/login.jsp'</script>\"");
                return ;
            }else{
                System.out.println("用户存入数据库发送异常，请重新检查信息");
                out.print("<script>alert('注册失败!请重新注册!');window.location.href='http://localhost:8888/hello/jsp/Register.jsp'</script>\"");
                return;
            }
        }
    }

}
