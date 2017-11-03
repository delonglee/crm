package com.situ.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.DataDicMapper;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.DataDicExample;
import com.situ.crm.pojo.DataDicExample.Criteria;
import com.situ.crm.service.IDataDicService;
import com.situ.crm.util.Util;

@Service
public class DataDicServiceImpl implements IDataDicService{
	
	@Autowired
	private DataDicMapper dataDicMapper;

	@Override
	public EasyUIDataGrideResult dataDicList(Integer page,Integer rows,DataDic dataDic) {
		// TODO Auto-generated method stub
		
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		DataDicExample dataDicExample = new DataDicExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = dataDicExample.createCriteria();
		if (StringUtils.isNotEmpty(dataDic.getDataDicName())) {
			criteria.andDataDicNameLike(Util.formatLike(dataDic.getDataDicName()));
		}
		if (StringUtils.isNotEmpty(dataDic.getDataDicValue())) {
			criteria.andDataDicValueLike(Util.formatLike(dataDic.getDataDicValue()));
		}
		List<DataDic> dataDiclist = dataDicMapper.selectByExample(dataDicExample);
		//得到total
		PageInfo<DataDic> pageInfo = new PageInfo<DataDic>(dataDiclist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(dataDiclist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			dataDicMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(DataDic dataDic) {
		// TODO Auto-generated method stub
		if (dataDicMapper.insert(dataDic) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(DataDic dataDic) {
		// TODO Auto-generated method stub
		if (dataDicMapper.updateByPrimaryKeySelective(dataDic)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<DataDic> findDataDicName() {
		// TODO Auto-generated method stub
		return dataDicMapper.findDataDicName();
	}

	@Override
	public List<DataDic> findLevel() {
		// TODO Auto-generated method stub
		return dataDicMapper.findLevel();
	}

	@Override
	public List<DataDic> findService() {
		// TODO Auto-generated method stub
		return dataDicMapper.findService();
	}

}
