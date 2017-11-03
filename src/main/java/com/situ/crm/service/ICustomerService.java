package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;
import com.situ.crm.vo.CustomerContribute;

public interface ICustomerService {
	
	EasyUIDataGrideResult customerList(Integer page,Integer rows,Customer customer);
	
	ServerResponse delete(String ids);

	ServerResponse add(Customer customer);

	ServerResponse update(Customer customer);
	
	ServerResponse findById(Integer id);
	
	EasyUIDataGrideResult findCustomerContribute(Integer page,Integer rows,CustomerContribute customerContribute);
	

}
