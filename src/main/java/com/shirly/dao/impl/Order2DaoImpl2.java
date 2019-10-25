package com.shirly.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 订单的持久层实现类
*/
@Repository("orderDao")
public class Order2DaoImpl2 implements IOrderDao{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public List<Order> findAllOrder() {
		try{
            return jdbcTemplate.query("select * from `order`",new BeanPropertyRowMapper<Order>(Order.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public Order findOrderById(Integer orderId) {
		try{
			List<Order> os = jdbcTemplate.query("select * from `order` where orderId = ? ",new BeanPropertyRowMapper<Order>(Order.class),orderId);
            return (os != null && os.size()>0) ? os.get(0) : null;
            
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void saveOrder(Order order) {
		try{
			jdbcTemplate.update("insert into `order`(userId,orderTime,totalPrice,groupId)values(?,?,?,?)",
            		order.getUserId(),order.getOrderTime(),order.getTotalPrice(),order.getGroupId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void updateOrder(Order order) {
		try{
			jdbcTemplate.update("update `order` set totalPrice=? where orderId=?",order.getTotalPrice(),order.getOrderId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void deleteOrder(Integer orderId) {
		try{
			jdbcTemplate.update("delete from `order` where orderId=?",orderId);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
}
