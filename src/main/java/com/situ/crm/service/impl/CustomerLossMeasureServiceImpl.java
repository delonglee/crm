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
import com.situ.crm.mapper.CustomerLossMeasureMapper;
import com.situ.crm.pojo.CustomerLossMeasure;
import com.situ.crm.pojo.CustomerLossMeasureExample;
import com.situ.crm.pojo.CustomerLossMeasureExample.Criteria;
import com.situ.crm.service.ICustomerLossMeasureService;
import com.situ.crm.util.Util;

@Service
public class CustomerLossMeasureServiceImpl implements ICustomerLossMeasureService{
	
	@Autowired
	private CustomerLossMeasureMapper customerLossMeasureMapper;

	@Override
	public EasyUIDataGrideResult customerLossMeasureList(Integer page,Integer rows,CustomerLossMeasure customerLossMeasure) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CustomerLossMeasureExample customerLossMeasureExample = new CustomerLossMeasureExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		//PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = customerLossMeasureExample.createCriteria();
		if (customerLossMeasure.getLossId()!=null) {
			criteria.andLossIdEqualTo(customerLossMeasure.getLossId());
		}
		List<CustomerLossMeasure> customerLossMeasurelist = customerLossMeasureMapper.selectByExample(customerLossMeasureExample);
		//得到total
		PageInfo<CustomerLossMeasure> pageInfo = new PageInfo<CustomerLossMeasure>(customerLossMeasurelist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerLossMeasurelist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			customerLossMeasureMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CustomerLossMeasure customerLossMeasure) {
		// TODO Auto-generated method stub
		if (customerLossMeasureMapper.insert(customerLossMeasure) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CustomerLossMeasure customerLossMeasure) {
		// TODO Auto-generated method stub
		if (customerLossMeasureMapper.updateByPrimaryKeySelective(customerLossMeasure)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (customerLossMeasureMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

}
