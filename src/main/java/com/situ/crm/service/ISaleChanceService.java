package com.situ.crm.service;

import java.util.Date;
import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.SaleChance;

public interface ISaleChanceService {
	
	EasyUIDataGrideResult saleChanceList(Integer page,Integer rows,SaleChance saleChance,Date beginTime,Date endTime);
	
	ServerResponse delete(String ids);

	ServerResponse add(SaleChance saleChance);

	ServerResponse update(SaleChance saleChance);
	
	
	List<SaleChance> findSaleChanceStatus();

	ServerResponse findById(Integer id);
	
	ServerResponse updateDevResult(Integer id, Integer devResult);
	
	

}
