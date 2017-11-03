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
import com.situ.crm.mapper.CustomerServiceMapper;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.pojo.CustomerServiceExample;
import com.situ.crm.pojo.CustomerServiceExample.Criteria;
import com.situ.crm.service.ICustomerServiceService;
import com.situ.crm.util.Util;

@Service
public class CustomerServiceServiceImpl implements ICustomerServiceService{
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Override
	public EasyUIDataGrideResult customerServiceList(Integer page,Integer rows,CustomerService customerService) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerServiceExample customerServiceExample = new CustomerServiceExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerServiceExample.createCriteria();
		if (StringUtils.isNotEmpty(customerService.getCustomer())) {
			criteria.andCustomerLike(Util.formatLike(customerService.getCustomer()));
		}
		if (StringUtils.isNotEmpty(customerService.getOverview())) {
			criteria.andOverviewLike(Util.formatLike(customerService.getOverview()));
		}
		if (StringUtils.isNotEmpty(customerService.getServiceType())) {
			criteria.andServiceTypeLike(Util.formatLike(customerService.getServiceType()));
		}
		if (StringUtils.isNotEmpty(customerService.getStatus())) {
			criteria.andStatusNotLike(Util.formatLike(customerService.getStatus()));
		}
		List<CustomerService> customerServicelist = customerServiceMapper.selectByExample(customerServiceExample);
		//得到total
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(customerServicelist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerServicelist);
		return result;
	}
	
	
	@Override
	public EasyUIDataGrideResult customerServiceListTwo(Integer page,Integer rows,CustomerService customerService) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerServiceExample customerServiceExample = new CustomerServiceExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerServiceExample.createCriteria();
		if (StringUtils.isNotEmpty(customerService.getCustomer())) {
			criteria.andCustomerLike(Util.formatLike(customerService.getCustomer()));
		}
		if (StringUtils.isNotEmpty(customerService.getOverview())) {
			criteria.andOverviewLike(Util.formatLike(customerService.getOverview()));
		}
		if (StringUtils.isNotEmpty(customerService.getServiceType())) {
			criteria.andServiceTypeLike(Util.formatLike(customerService.getServiceType()));
		}
		if (StringUtils.isNotEmpty(customerService.getStatus())) {
			criteria.andStatusLike(Util.formatLike(customerService.getStatus()));
		}
		List<CustomerService> customerServicelist = customerServiceMapper.selectByExample(customerServiceExample);
		//得到total
		PageInfo<CustomerService> pageInfo = new PageInfo<CustomerService>(customerServicelist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerServicelist);
		return result;
	}	

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerServiceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerService customerService) {
		// TODO Auto-generated method stub
		if (customerServiceMapper.insert(customerService) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerService customerService) {
		// TODO Auto-generated method stub
		if (customerServiceMapper.updateByPrimaryKeySelective(customerService)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse updateStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		CustomerService customerService = new CustomerService();
		customerService.setId(id);
		customerService.setStatus(status);
		if (customerServiceMapper.updateByPrimaryKeySelective(customerService)>0) {
			return ServerResponse.createSuccess("修改状态成功");
		}
		return ServerResponse.createError("修改状态失败");
	}

}
