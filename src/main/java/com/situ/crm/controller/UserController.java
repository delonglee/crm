package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.User;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping("/userManager")
	@ResponseBody
	public EasyUIDataGrideResult userManager(Integer page,Integer rows,User user){
		return userService.userList(page, rows,user);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return userService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(User user){
		return userService.add(user);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(User user){
		return userService.update(user);
	}
	
	@RequestMapping("updatePassword")
	@ResponseBody
	public ServerResponse updatePassword(User user){
		return userService.updateById(user);
	}
	
	@RequestMapping("/findByRole")
	@ResponseBody
	public List<User> findByRole(){
		return userService.findByRole();
	}
	/*
	 * 客户经理
	 */
	@RequestMapping("/findManageName")
	@ResponseBody
	public List<User> findManageName(){
		return userService.findManageName();
	}

}
