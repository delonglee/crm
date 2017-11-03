package com.situ.crm.service.impl;

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
import com.situ.crm.mapper.CustomerLossMapper;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.pojo.CustomerLossExample;
import com.situ.crm.pojo.CustomerLossExample.Criteria;
import com.situ.crm.service.ICustomerLossService;
import com.situ.crm.util.Util;

@Service
public class CustomerLossServiceImpl implements ICustomerLossService{
	
	@Autowired
	private CustomerLossMapper customerLossMapper;

	@Override
	public EasyUIDataGrideResult customerLossList(Integer page,Integer rows,CustomerLoss customerLoss,Date beginTime,Date endTime) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerLossExample customerLossExample = new CustomerLossExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerLossExample.createCriteria();
		if (StringUtils.isNotEmpty(customerLoss.getCustomerName())) {
			criteria.andCustomerNameLike(Util.formatLike(customerLoss.getCustomerName()));
		}
		if (StringUtils.isNotEmpty(customerLoss.getCustomerManager())) {
			criteria.andCustomerManagerLike(Util.formatLike(customerLoss.getCustomerManager()));
		}
		if (customerLoss.getStatus()!=null) {
			criteria.andStatusEqualTo(customerLoss.getStatus());
		}
		List<CustomerLoss> customerLosslist = customerLossMapper.selectByExample(customerLossExample);
		//得到total
		PageInfo<CustomerLoss> pageInfo = new PageInfo<CustomerLoss>(customerLosslist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerLosslist);
		System.out.println(customerLoss.getCustomerName());
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerLossMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerLoss customerLoss) {
		// TODO Auto-generated method stub
/*		if(StringUtils.isNotEmpty(customerLoss.getAssignMan())){
			customerLoss.setStatus(1);//已分配
		}else{
			customerLoss.setStatus(0);//0：:未分配
		}
		customerLoss.setDevResult(0);*/
		if (customerLossMapper.insert(customerLoss) > 0) {
			return ServerResponse.createSuccess("添加成功");			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerLoss customerLoss) {
		// TODO Auto-generated method stub
		if (customerLossMapper.updateByPrimaryKeySelective(customerLoss)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}
	@Override
	public ServerResponse findById(Integer id) {
		// TODO Auto-generated method stub
		CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
		if(customerLoss != null){
			//前台需要用所以也要传过去data
			return ServerResponse.createSuccess("查找成功",customerLoss);
		}		
		return ServerResponse.createError("查找失败!");
	}

	@Override
	public ServerResponse updateStatus(Integer id, Integer status) {
		// TODO Auto-generated method stub
		CustomerLoss customerLoss = new CustomerLoss();
		customerLoss.setId(id);
		customerLoss.setStatus(status);
		if (customerLossMapper.updateByPrimaryKeySelective(customerLoss)>0) {
			return ServerResponse.createSuccess("修改成功");
		}
		return ServerResponse.createError("修改失败");
	}
}
