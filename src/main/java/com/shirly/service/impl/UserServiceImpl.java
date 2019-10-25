package com.shirly.service.impl;

import java.util.Date;

import com.shirly.dao.IUserDao;
import com.shirly.factory.BeanFactory;
import com.shirly.service.IUserService;

/**
* @author shirly
* @CreateTime 2019年7月21日 上午11:59:51
* @description 账户的业务层实现类
*/
public class UserServiceImpl implements IUserService{
	
//  private IAccountDao accountDao = new AccountDaoImpl();

	private IUserDao userDao = (IUserDao) BeanFactory.getBean("userDao");
	
	//如果是经常变化的数据，并不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public UserServiceImpl(String name,Integer age,Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
	public UserServiceImpl() {
		System.out.println("对象1创建了");
	}

	//  private int i = 1;
	/*public void saveUser() {
		int i = 1;
        userDao.saveUser();
        System.out.println(i);
        i++;
	}*/
	
	public void  saveUser(){
        System.out.println("service中的saveUser方法执行了。。。");
    }
	
	public void  init(){
        System.out.println("对象初始化了。。。");
    }
	
    public void  destroy(){
        System.out.println("对象销毁了。。。");
    }
    
	public void getUser() {
		System.out.println("service中的getUser方法执行了。。。"+name+","+age+","+birthday);
	}
}
