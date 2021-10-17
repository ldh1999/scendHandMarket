package com.ldh.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserListen implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("12");
    }
}
