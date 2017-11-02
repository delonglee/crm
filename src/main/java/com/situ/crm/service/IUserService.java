package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

public interface IUserService {
	
	EasyUIDataGrideResult userList(Integer page,Integer rows,User user);
	
	ServerResponse delete(String ids);

	ServerResponse add(User user);

	ServerResponse update(User user);
	
	User login(String name, String password);
	
	ServerResponse updateById(User user);
	
	/*
	 * 查询可以创建营销机会的人
	 */
	List<User> findByRole();

	List<User> findManageName();

	
	

}
