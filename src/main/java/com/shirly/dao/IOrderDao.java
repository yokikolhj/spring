package com.shirly.dao;

import java.util.List;

import com.shirly.model.Order;

/**
* @author shirly
* @CreateTime 2019年7月21日 下午12:01:18
* @description 订单的持久层接口
*/
public interface IOrderDao {

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
}
