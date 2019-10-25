package com.shirly.test.jdbcTemplate;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        IOrderDao orderDao = ac.getBean("orderDao",IOrderDao.class);

        Order order = orderDao.findOrderById(1);
        System.out.println(order);

        order.setTotalPrice(25f);
        orderDao.updateOrder(order);
    }
}
