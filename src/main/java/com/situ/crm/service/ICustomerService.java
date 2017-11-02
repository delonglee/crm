package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;

public interface ICustomerService {
	
	EasyUIDataGrideResult customerList(Integer page,Integer rows,Customer customer);
	
	ServerResponse delete(String ids);

	ServerResponse add(Customer customer);

	ServerResponse update(Customer customer);
	
	ServerResponse findById(Integer id);
	

}
