package org.example;

import org.example.entity.User;
import org.example.service.MenuService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        MenuService menuService = (MenuService) context.getBean("menuService");
        menuService.menuPrincipal();

    }
}