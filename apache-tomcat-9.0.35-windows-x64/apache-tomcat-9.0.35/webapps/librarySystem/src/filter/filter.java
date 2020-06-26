package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "filter")
public class filter implements Filter {
    public void destroy() {
    }

    /**
     * 对拦截的请求合法性判断
     * 对拦截的请求进行增强处理
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(true);
            resp.setContentType("text/html;");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = resp.getWriter();
            String request_uri = req.getRequestURI();
            String ctxPath = req.getContextPath();
            String uri = request_uri.substring(ctxPath.length());
            if (uri.contains("login.jsp") ) {
                chain.doFilter(request, response);
            }
            else {
                if (session.getAttribute("userName") != null)
                {
                    chain.doFilter(request, response);
                }
                else {
                    out.println("您没有登录，请先登录！3秒后自动回到登录页面。");
                    resp.setHeader("refresh", "3;url=" + ctxPath + "/jsp/login.jsp");
                    return;
                }
            }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}