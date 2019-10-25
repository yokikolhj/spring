package com.shirly.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shirly.config.SpringConfiguration;
import com.shirly.model.Order;
import com.shirly.service.IOrderService;

/**
* @author shirly
* @CreateTime 2019年7月22日 上午11:08:07
* @description 使用Junit单元测试：测试我们的配置
*/
/**
 * 使用Junit单元测试：测试我们的配置
 * Spring整合junit的配置
 *      1、导入spring整合junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *             @Runwith
 *      3、告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *                  locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在地位置
 *
 *   当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
//1.xml配置：
@ContextConfiguration(locations = "classpath:bean.xml")
//2.配置文件配置：
//@ContextConfiguration(classes = SpringConfiguration.class)
public class OrderServiceTest {
	
	@Autowired
    @Qualifier("orderService")
    private  IOrderService as;
	
	/*@Autowired
    @Qualifier("proxyOrderService")
    private  IOrderService os;*/
	
	@Test
    public void testFindAll() {
        List<Order> orders = as.findAllOrder();
        for(Order order : orders){
            System.out.println(order);
        }
    }
	
    @Test
    public void testFindOne() {
        Order order = as.findOrderById(1);
        System.out.println(order);
    }

    @Test
    public void testSave() {
        Order order = new Order();
        order.setUserId(1);
        order.setOrderTime(new Date());
        order.setTotalPrice(30f);
        order.setGroupId("111111");
        
        as.saveOrder(order);
    }

    @Test
    public void testUpdate() {
        Order order = as.findOrderById(1);
        order.setTotalPrice(20f);
        as.updateOrder(order);
    }

    @Test
    public void testDelete() {
        as.deleteOrder(3);
    }
    
    @Test
    public void testTransfer(){
    	// 通过代理器切面添加事务
//        os.transfer(1,4,1f);
        // 自己本身添加事务
    	as.transfer(1,4,1f);
    }
}
