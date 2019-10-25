package com.shirly.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;
import com.shirly.util.ConnectionUtils;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 订单的持久层实现类
*/
public class OrderDaoImpl2 extends JdbcDaoSupport implements IOrderDao{

	public List<Order> findAllOrder() {
		try{
            return super.getJdbcTemplate().query("select * from `order`",new BeanPropertyRowMapper<Order>(Order.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public Order findOrderById(Integer orderId) {
		try{
			List<Order> os = super.getJdbcTemplate().query("select * from `order` where orderId = ? ",new BeanPropertyRowMapper<Order>(Order.class),orderId);
            return (os != null && os.size()>0) ? os.get(0) : null;
            
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void saveOrder(Order order) {
		try{
			super.getJdbcTemplate().update("insert into `order`(userId,orderTime,totalPrice,groupId)values(?,?,?,?)",
            		order.getUserId(),order.getOrderTime(),order.getTotalPrice(),order.getGroupId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void updateOrder(Order order) {
		try{
			super.getJdbcTemplate().update("update `order` set totalPrice=? where orderId=?",order.getTotalPrice(),order.getOrderId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void deleteOrder(Integer orderId) {
		try{
			super.getJdbcTemplate().update("delete from `order` where orderId=?",orderId);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
}
