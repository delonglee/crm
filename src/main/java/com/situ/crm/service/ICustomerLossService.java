package com.situ.crm.service;

import java.util.Date;
import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.CustomerLoss;

public interface ICustomerLossService {
	
	EasyUIDataGrideResult customerLossList(Integer page,Integer rows,CustomerLoss customerLoss,Date beginTime,Date endTime);
	
	ServerResponse delete(String ids);

	ServerResponse add(CustomerLoss customerLoss);

	ServerResponse update(CustomerLoss customerLoss);
	
	
	List<CustomerLoss> findCustomerLossStatus();

	ServerResponse findById(Integer id);
	
	

}
