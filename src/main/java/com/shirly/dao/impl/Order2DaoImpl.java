package com.shirly.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;
import com.shirly.util.ConnectionUtils2;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 订单的持久层实现类
*/
//@Repository("orderDao")
public class Order2DaoImpl implements IOrderDao{

	@Autowired //在xml中配置
	private QueryRunner runner;
	
	@Autowired
    private ConnectionUtils2 connectionUtils;

	public List<Order> findAllOrder() {
		try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from `order`",new BeanListHandler<Order>(Order.class));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public Order findOrderById(Integer orderId) {
		try{
            return runner.query(connectionUtils.getThreadConnection(),"select * from `order` where orderId = ? ",new BeanHandler<Order>(Order.class),orderId);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void saveOrder(Order order) {
		try{
            runner.update(connectionUtils.getThreadConnection(),"insert into `order`(userId,orderTime,totalPrice,groupId)values(?,?,?,?)",
            		order.getUserId(),order.getOrderTime(),order.getTotalPrice(),order.getGroupId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void updateOrder(Order order) {
		try{
            runner.update(connectionUtils.getThreadConnection(),"update `order` set totalPrice=? where orderId=?",order.getTotalPrice(),order.getOrderId());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void deleteOrder(Integer orderId) {
		try{
            runner.update(connectionUtils.getThreadConnection(),"delete from `order` where orderId=?",orderId);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
}
