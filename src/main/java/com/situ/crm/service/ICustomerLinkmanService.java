package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLinkman;

public interface ICustomerLinkmanService {
	
	EasyUIDataGrideResult customerLinkmanList(Integer page,Integer rows,CustomerLinkman customerLinkman);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerLinkman customerLinkman);

	ServerResponse update(CustomerLinkman customerLinkman);

	ServerResponse deleteById(Integer id);
	

	
	

}
