package Servlet;

//这个只是练习，在项目中没用到
import DB.netUser.LoginDaoImpl;
import DB.netUser.netUser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCookieServlet  extends HttpServlet {
    @Override
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //获取请求信息
            //获取Cookie信息
            Cookie []cks =req.getCookies();
            if(cks != null ) {
                //遍历Cookie信息
                String uNo="";
                for (Cookie c : cks) {
                    if( "uNo".equals (c.getName())) {
                       uNo = c.getValue();
                        System.out.println( "uNo"+ ":" + uNo);
                    }
                }
                //检验uNo是否存在
                if("".equals(uNo)){
                    //请求转发
                    req.getRequestDispatcher("http://localhost:8888/hello/jsp/login.jsp").forward(req,resp);
                }else{
                    //校验uNo用户信息
                    LoginDaoImpl loginDao =new LoginDaoImpl();
                    netUser  user = loginDao.checkLoginDao(uNo);
                    if(user != null){
                        //重定向
                        resp.sendRedirect("http://localhost:8888/hello/jsp/login.jsp");
                    }
                }
            }
            else{
                    //请求转发
                req.getRequestDispatcher("http://localhost:8888/hello/jsp/login.jsp").forward(req,resp);
            }
    }

}
