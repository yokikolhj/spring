package com.shirly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;
import com.shirly.service.IOrderService;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 订单的业务层实现类
*/
@Service("orderService")
@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)//只读型事务的配置
public class Order2ServiceImpl implements IOrderService{
	
	@Autowired
	private IOrderDao orderDao;

	public List<Order> findAllOrder() {
		return orderDao.findAllOrder();
	}

	public Order findOrderById(Integer orderId) {
		return orderDao.findOrderById(orderId);
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	public void deleteOrder(Integer orderId) {
		orderDao.deleteOrder(orderId);
	}
	
	//需要的是读写型事务配置
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
	public void transfer(Integer sourceOrderId, Integer targetOrderId, Float money) {
		//2.1根据名称查询转出账户
        Order source = orderDao.findOrderById(sourceOrderId);
        //2.2根据名称查询转入账户
        Order target = orderDao.findOrderById(targetOrderId);
        //2.3转出账户减钱
        source.setTotalPrice(source.getTotalPrice()-money);
        //2.4转入账户加钱
        target.setTotalPrice(target.getTotalPrice()+money);
        //2.5更新转出账户
        orderDao.updateOrder(source);

//        int i=1/0;

        //2.6更新转入账户
        orderDao.updateOrder(target);
	}
	
}
