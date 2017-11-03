package com.situ.crm.service;

import java.util.List;import com.mysql.fabric.xmlrpc.base.Data;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;

public interface IDataDicService {
	
	EasyUIDataGrideResult dataDicList(Integer page,Integer rows,DataDic dataDic);
	
	ServerResponse delete(String ids);

	ServerResponse add(DataDic dataDic);

	ServerResponse update(DataDic dataDic);

	List<DataDic> findDataDicName();

	List<DataDic> findLevel();
	
	List<DataDic> findService();
	
	

}
