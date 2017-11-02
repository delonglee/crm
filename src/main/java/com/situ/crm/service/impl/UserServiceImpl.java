package com.situ.crm.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.UserMapper;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.pojo.UserExample.Criteria;
import com.situ.crm.service.IUserService;
import com.situ.crm.util.Util;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public EasyUIDataGrideResult userList(Integer page,Integer rows,User user) {
		// TODO Auto-generated method stub
		
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		UserExample userExample = new UserExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = userExample.createCriteria();
		if (StringUtils.isNotEmpty(user.getName())) {
			criteria.andNameLike(Util.formatLike(user.getName()));
		}
		List<User> userlist = userMapper.selectByExample(userExample);
		//得到total
		PageInfo<User> pageInfo = new PageInfo<User>(userlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(userlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(User user) {
		// TODO Auto-generated method stub
		if (userMapper.insert(user) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(User user) {
		// TODO Auto-generated method stub
		if (userMapper.updateByPrimaryKey(user)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(name, password);
		
	}

	@Override
	public ServerResponse updateById(User user) {
		// TODO Auto-generated method stub
		System.out.println(user);
		if (userMapper.updateByPrimaryKeySelective(user) > 0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<User> findByRole() {
		
		return userMapper.findByRole();
	}

	@Override
	public List<User> findManageName() {
		// TODO Auto-generated method stub
		return userMapper.findManageName();
	}

}
