package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerContact;

public interface ICustomerContactService {
	
	EasyUIDataGrideResult customerContactList(Integer page,Integer rows,CustomerContact customerContact);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerContact customerContact);

	ServerResponse update(CustomerContact customerContact);

	ServerResponse deleteById(Integer id);
	

	
	

}
