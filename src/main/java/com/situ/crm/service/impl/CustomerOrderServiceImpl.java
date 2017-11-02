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
import com.situ.crm.mapper.CustomerOrderMapper;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerOrderExample;
import com.situ.crm.pojo.CustomerOrderExample.Criteria;
import com.situ.crm.service.ICustomerOrderService;
import com.situ.crm.util.Util;

@Service
public class CustomerOrderServiceImpl implements ICustomerOrderService{
	
	@Autowired
	private CustomerOrderMapper customerOrderMapper;

	@Override
	public EasyUIDataGrideResult customerOrderList(Integer page,Integer rows,CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerOrderExample customerOrderExample = new CustomerOrderExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		//PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerOrderExample.createCriteria();
		if (customerOrder.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerOrder.getCustomerId());
		}
		List<CustomerOrder> customerOrderlist = customerOrderMapper.selectByExample(customerOrderExample);
		//得到total
		PageInfo<CustomerOrder> pageInfo = new PageInfo<CustomerOrder>(customerOrderlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerOrderlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerOrderMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		if (customerOrderMapper.insert(customerOrder) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerOrder customerOrder) {
		// TODO Auto-generated method stub
		if (customerOrderMapper.updateByPrimaryKeySelective(customerOrder)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (customerOrderMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

}
