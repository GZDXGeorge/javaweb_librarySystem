package Servlet;

//这个只是练习，在项目中没用到
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet  extends HttpServlet {
    @Override
    protected  void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //获取请求信息

        //处理请求信息

        //响应处理结果
        //使用Cookie进行浏览器信息存储
        //1、创建Cookie对象
        Cookie c =new Cookie("","");
        c.setMaxAge(3*24*60*60);//例如三天保存
        //2、设置有效路径
        c.setPath("#");
        //3、响应Cookie信息给客户端
        resp.addCookie(c);  //一个Cookie对象存储一条数据，可以多次创建多个
                            //临时存储： 存储在浏览器，浏览器关就没了
                            //定时存储： 可以设置Cookie信息的有效时间，以秒为单位，存放客户端硬盘，在有效期内符合路径要求的请求会附带Cookie信息，除非设定有效路径
        // 用途
        // 判断请求种是否携带正确的Cookie信息
        //  如果有，则校验Cookies信息是否正确
                //如果校验成功，就直接响应主页面给用户
                //如果校验不成功，则响应登录页面给用户
        //如果没有则转发给登录页面
    }

}
