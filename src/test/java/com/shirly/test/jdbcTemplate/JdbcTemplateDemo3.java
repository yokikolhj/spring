package com.shirly.test.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.shirly.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcTemplate的CRUD操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //3.执行操作
        //保存
//        jt.update("insert into order(name,money)values(?,?)","eee",3333f);
        //更新
//        jt.update("update order set name=?,money=? where id=?","test",4567,7);
        //删除
//        jt.update("delete from order where id=?",8);
        //查询所有
//        List<Order> orders = jt.query("select * from order where money > ?",new OrderRowMapper(),1000f);
//        List<Order> orders = jt.query("select * from order where money > ?",new BeanPropertyRowMapper<Order>(Order.class),1000f);
//        for(Order order : orders){
//            System.out.println(order);
//        }
        //查询一个
//        List<Order> orders = jt.query("select * from order where id = ?",new BeanPropertyRowMapper<Order>(Order.class),1);
//        System.out.println(orders.isEmpty()?"没有内容":orders.get(0));

        //查询返回一行一列（使用聚合函数，但不加group by子句）
        Long count = jt.queryForObject("select count(*) from order where money > ?",Long.class,1000f);
        System.out.println(count);


    }
}

/**
 * 定义Order的封装策略
 */
class OrderRowMapper implements RowMapper<Order>{
    /**
     * 把结果集中的数据封装到Order中，然后由spring把每个Order加到集合中
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("orderId"));
        order.setUserId(rs.getInt("userId"));
        order.setOrderTime(rs.getDate("orderTime"));
        order.setGroupId(rs.getString("groupId"));
        order.setTotalPrice(rs.getFloat("totalPrice"));
        order.setState(rs.getInt("state"));
        order.setCreateTime(rs.getDate("createTime"));
        order.setUpdateTime(rs.getDate("updateTime"));
        return order;
    }
}