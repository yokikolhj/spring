package com.shirly.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shirly.service.IAccountService;
import com.shirly.service.IUserService;

/**
 * 测试AOP的配置
 */
public class AOPTest {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        //bean.xml
//        IUserService as = (IUserService)ac.getBean("userService");
        //3.执行方法
//        as.saveUser();
        //注解
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();
    }
}
