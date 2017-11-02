package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.OrderItem;

public interface IOrderItemService {
	
	EasyUIDataGrideResult orderItemList(Integer page,Integer rows,OrderItem orderItem);
	
	ServerResponse delete(String ids);

	ServerResponse add(OrderItem orderItem);

	ServerResponse update(OrderItem orderItem);

	ServerResponse deleteById(Integer id);
	

	
	

}
