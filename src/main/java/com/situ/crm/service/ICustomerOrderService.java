package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerOrder;

public interface ICustomerOrderService {
	
	EasyUIDataGrideResult customerOrderList(Integer page,Integer rows,CustomerOrder customerOrder);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerOrder customerOrder);

	ServerResponse update(CustomerOrder customerOrder);

	ServerResponse deleteById(Integer id);
	

	
	

}
