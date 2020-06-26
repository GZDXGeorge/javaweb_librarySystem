package Listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class Listener2 implements ServletContextAttributeListener,HttpSessionListener{

    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("全局作用域对象添加了新的共享数据"+scae.getName());
    }

    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("全局作用域对象共享数据"+scae.getName()+"被删除");
    }

    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("全局作用域对象"+scae.getName()+"更新了新的共享数据");
    }

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("会话作用域对象添加了新的共享数据"+se.getSession());
    }

    public  void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("会话作用域对象删除共享数据"+se.getSession());
    }
}