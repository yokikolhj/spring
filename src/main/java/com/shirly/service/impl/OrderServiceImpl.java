package com.shirly.service.impl;

import java.util.List;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.shirly.dao.IOrderDao;
import com.shirly.model.Order;
import com.shirly.service.IOrderService;
import com.shirly.util.TransactionManager;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 订单的业务层实现类
*/
public class OrderServiceImpl implements IOrderService{
	
	private IOrderDao orderDao;
	// 直接添加事务
	/*private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }*/
	// 事务模板添加事务
	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	    this.transactionTemplate = transactionTemplate;
	}
	    
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

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

	/*public void transfer(Integer sourceOrderId, Integer targetOrderId, Float money) {
		System.out.println("transfer....");
		//1.当前开启事务
		try {
            txManager.beginTransaction();
            //2.执行操作

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

            int i=1/0;

            //2.6更新转入账户
            orderDao.updateOrder(target);
            //3.提交事务
            txManager.commit();

        }catch (Exception e){
            //4.回滚操作
//            txManager.rollback();
            e.printStackTrace();
        }finally {
            //5.释放连接
            txManager.release();
        }
		
		//2.此处不开启事务，由代理器一起做事务控制
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
	}*/
	
	public void transfer(final Integer sourceOrderId, final Integer targetOrderId, final Float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
			public Object doInTransaction(TransactionStatus status) {
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

		        int i=1/0;

		        //2.6更新转入账户
		        orderDao.updateOrder(target);
				return null;
			}
        });
    }
	
}
