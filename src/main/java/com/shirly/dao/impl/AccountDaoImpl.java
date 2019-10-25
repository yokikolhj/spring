package com.shirly.dao.impl;

import org.springframework.stereotype.Repository;

import com.shirly.dao.IAccountDao;

/**
* @author shirly
* @CreateTime 2019年7月21日 下午12:02:16
* @description 账户的持久层实现类
*/
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

	public  void saveAccount(){
        System.out.println("保存了账户1111111111111");
    }

}
