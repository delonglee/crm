package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Product;

public interface IProductService {
	
	EasyUIDataGrideResult productList(Integer page,Integer rows,Product product);
	
	ServerResponse delete(String ids);

	ServerResponse add(Product product);

	ServerResponse update(Product product);
	
	

}
