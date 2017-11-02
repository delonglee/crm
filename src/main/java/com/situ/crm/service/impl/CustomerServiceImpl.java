package com.situ.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerMapper;
import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import com.situ.crm.pojo.CustomerExample.Criteria;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.util.Util;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public EasyUIDataGrideResult customerList(Integer page,Integer rows,Customer customer) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerExample customerExample = new CustomerExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerExample.createCriteria();
		if (StringUtils.isNotEmpty(customer.getNum())) {
			criteria.andNumLike(Util.formatLike(customer.getNum()));
		}
		if (StringUtils.isNotEmpty(customer.getName())) {
			criteria.andNameLike(Util.formatLike(customer.getName()));
		}
		List<Customer> customerlist = customerMapper.selectByExample(customerExample);
		//得到total
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(customerlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerlist);
		System.out.println(customer.getName());
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(Customer customer) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间字符串产生方式
        String nummber = "KH" + format.format(new Date()); // 组合流水号前一部分，NO+时间字符串，如：NO20160126
        customer.setNum(nummber);
		if (customerMapper.insert(customer) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(Customer customer) {
		// TODO Auto-generated method stub
		if (customerMapper.updateByPrimaryKeySelective(customer)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse findById(Integer id) {
		// TODO Auto-generated method stub
		Customer customer = customerMapper.selectByPrimaryKey(id);
		if (customer != null) {
			return ServerResponse.createSuccess("查询成功", customer);
		}else {
			return ServerResponse.createError("查询失败");
		}
	}
}
