package com.shirly.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.shirly.service.IOrderService;
import com.shirly.util.TransactionManager;

/**
 * 用于创建Service的代理对象的工厂
 */
public class ProxyFactory {

    private IOrderService orderService;

    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }


    public final void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 获取Service代理对象
     * @return
     */
    public IOrderService getOrderService() {
        return (IOrderService)Proxy.newProxyInstance(orderService.getClass().getClassLoader(),
                orderService.getClass().getInterfaces(),
                new InvocationHandler() {

					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						if("test".equals(method.getName())){
                            return method.invoke(orderService,args);
                        }

                        Object rtValue = null;
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            rtValue = method.invoke(orderService, args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return rtValue;
                        } catch (Exception e) {
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //6.释放连接
                            txManager.release();
                        }
					}
                   
                });
    }
}
