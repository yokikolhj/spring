package com.shirly.service;

import java.util.List;

import com.shirly.model.Order;

/**
* @author shirly
* @CreateTime 2019年7月21日 下午12:01:18
* @description 订单的业务层接口
*/
public interface IOrderService {

    /**
     * 查询所有
     * @return
     */
    List<Order> findAllOrder();

    /**
     * 查询一个
     * @return
     */
    Order findOrderById(Integer orderId);

    /**
     * 保存
     * @param Order
     */
    void saveOrder(Order order);

    /**
     * 更新
     * @param Order
     */
    void updateOrder(Order order);

    /**
     * 删除
     * @param acccountId
     */
    void deleteOrder(Integer orderId);
    
    /**
     * 转账
     * @param sourceName        转出订单id
     * @param targetName        转入订单id
     * @param money             转账金额
     */
    void transfer(Integer sourceOrderId,Integer targetOrderId,Float money);
    
//	void test();//它只是连接点，但不是切入点，因为没有被增强
}
