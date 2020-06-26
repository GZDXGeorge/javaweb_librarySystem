package Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class Listener implements ServletContextListener ,HttpSessionListener{
    //全局作用域对象，可以为所有的Servlet提供共享数据

    // Public constructor is required by servlet spec
    public Listener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        System.out.println("全局作用域对象被初始化");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        System.out.println("全局作用域对象被销毁");
    }
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话作用域对象"+se.getSession()+"被初始化");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("全局作用域对象"+se.getSession()+"被销毁");
    }
}
