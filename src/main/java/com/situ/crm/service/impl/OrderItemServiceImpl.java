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
import com.situ.crm.mapper.OrderItemMapper;
import com.situ.crm.pojo.OrderItem;
import com.situ.crm.pojo.OrderItemExample;
import com.situ.crm.pojo.OrderItemExample.Criteria;
import com.situ.crm.service.IOrderItemService;
import com.situ.crm.util.Util;

@Service
public class OrderItemServiceImpl implements IOrderItemService{
	
	@Autowired
	private OrderItemMapper orderItemMapper;

	@Override
	public EasyUIDataGrideResult orderItemList(Integer page,Integer rows,OrderItem orderItem) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		OrderItemExample orderItemExample = new OrderItemExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		//PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = orderItemExample.createCriteria();
		if (orderItem.getOrderId() != null) {
			criteria.andOrderIdEqualTo(orderItem.getOrderId());
		}
		List<OrderItem> orderItemlist = orderItemMapper.selectByExample(orderItemExample);
		//得到total
		PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(orderItemlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(orderItemlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			orderItemMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(OrderItem orderItem) {
		// TODO Auto-generated method stub
		if (orderItemMapper.insert(orderItem) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		if (orderItemMapper.updateByPrimaryKeySelective(orderItem)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (orderItemMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

}
