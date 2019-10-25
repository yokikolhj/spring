package com.shirly.dao.impl;

import com.shirly.dao.IUserDao;

/**
* @author shirly
* @CreateTime 2019年7月21日 下午12:02:16
* @description 账户的持久层实现类
*/
public class UserDaoImpl implements IUserDao {

	public void saveUser() {
		System.out.println("保存了账户");
	}

}
