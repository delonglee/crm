package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLossMeasure;

public interface ICustomerLossMeasureService {
	
	EasyUIDataGrideResult customerLossMeasureList(Integer page,Integer rows,CustomerLossMeasure customerLossMeasure);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerLossMeasure customerLossMeasure);

	ServerResponse update(CustomerLossMeasure customerLossMeasure);

	ServerResponse deleteById(Integer id);
	
	

}
