package com.situ.crm.service.impl;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CustomerContactMapper;
import com.situ.crm.pojo.CustomerContact;
import com.situ.crm.pojo.CustomerContactExample;
import com.situ.crm.pojo.CustomerContactExample.Criteria;
import com.situ.crm.service.ICustomerContactService;
import com.situ.crm.util.Util;

@Service
public class CustomerContactServiceImpl implements ICustomerContactService{
	
	@Autowired
	private CustomerContactMapper customerContactMapper;

	@Override
	public EasyUIDataGrideResult customerContactList(Integer page,Integer rows,CustomerContact customerContact) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerContactExample customerContactExample = new CustomerContactExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		//PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerContactExample.createCriteria();
		if (customerContact.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerContact.getCustomerId());
		}
		List<CustomerContact> customerContactlist = customerContactMapper.selectByExample(customerContactExample);
		//得到total
		PageInfo<CustomerContact> pageInfo = new PageInfo<CustomerContact>(customerContactlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerContactlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerContactMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerContact customerContact) {
		// TODO Auto-generated method stub
		if (customerContactMapper.insert(customerContact) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerContact customerContact) {
		// TODO Auto-generated method stub
		if (customerContactMapper.updateByPrimaryKeySelective(customerContact)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (customerContactMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

}
