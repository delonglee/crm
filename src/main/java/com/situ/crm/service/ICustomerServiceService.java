package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerService;

public interface ICustomerServiceService {
	
	EasyUIDataGrideResult customerServiceList(Integer page,Integer rows,CustomerService customerService);
	
	EasyUIDataGrideResult customerServiceListTwo(Integer page,Integer rows,CustomerService customerService);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerService customerService);

	ServerResponse update(CustomerService customerService);

	ServerResponse updateStatus(Integer id, String status);
	
	

}
