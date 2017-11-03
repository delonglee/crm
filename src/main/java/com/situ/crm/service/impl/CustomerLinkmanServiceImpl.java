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
import com.situ.crm.mapper.CustomerLinkmanMapper;
import com.situ.crm.pojo.CustomerLinkman;
import com.situ.crm.pojo.CustomerLinkmanExample;
import com.situ.crm.pojo.CustomerLinkmanExample.Criteria;
import com.situ.crm.service.ICustomerLinkmanService;
import com.situ.crm.util.Util;

@Service
public class CustomerLinkmanServiceImpl implements ICustomerLinkmanService{
	
	@Autowired
	private CustomerLinkmanMapper customerLinkmanMapper;

	@Override
	public EasyUIDataGrideResult customerLinkmanList(Integer page,Integer rows,CustomerLinkman customerLinkman) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerLinkmanExample customerLinkmanExample = new CustomerLinkmanExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		//PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerLinkmanExample.createCriteria();
		if (customerLinkman.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerLinkman.getCustomerId());
		}
		List<CustomerLinkman> customerLinkmanlist = customerLinkmanMapper.selectByExample(customerLinkmanExample);
		//得到total
		PageInfo<CustomerLinkman> pageInfo = new PageInfo<CustomerLinkman>(customerLinkmanlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerLinkmanlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerLinkmanMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerLinkman customerLinkman) {
		// TODO Auto-generated method stub
		if (customerLinkmanMapper.insert(customerLinkman) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerLinkman customerLinkman) {
		// TODO Auto-generated method stub
		if (customerLinkmanMapper.updateByPrimaryKeySelective(customerLinkman)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (customerLinkmanMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

	@Override
	public List<CustomerLinkman> findLinkMan() {
		// TODO Auto-generated method stub
		return customerLinkmanMapper.findLinkMan();
	}

}
