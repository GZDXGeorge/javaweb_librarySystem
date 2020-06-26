package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "objectFilter")
public class objectFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        resp.setContentType("text/html;");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        String request_uri = req.getRequestURI();
        String ctxPath = req.getContextPath();//读取到项目在服务器的路径
        String uri = request_uri.substring(ctxPath.length());
        if (uri.contains("login.jsp") ) {
            chain.doFilter(request, response);
        }
        else {
            if (session.getAttribute("userJob").equals("special"))
            {
                chain.doFilter(request, response);
            }
            else {
                out.println("您没有访问的权限！3秒后自动回到登录页面。");
                resp.setHeader("refresh", "3;url=" + ctxPath + "/jsp/login.jsp");
                return;
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
