package Servlet;
import verifyCodeService.VerifyCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class verifyCodeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 生成图片，保存图片上的文本到session域中，把图片响应给客户端
         */
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        // 保存图片上的文本到session域中。
        System.out.println("系统生成的验证码："+vc.getText());
        request.getSession().setAttribute("sessionCode", vc.getText());
        vc.output(image, response.getOutputStream());
    }

}

