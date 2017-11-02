package com.situ.crm.service.impl;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.CusDevPlanMapper;
import com.situ.crm.pojo.CusDevPlan;
import com.situ.crm.pojo.CusDevPlanExample;
import com.situ.crm.pojo.CusDevPlanExample.Criteria;
import com.situ.crm.service.ICusDevPlanService;
import com.situ.crm.util.Util;

@Service
public class CusDevPlanServiceImpl implements ICusDevPlanService{
	
	@Autowired
	private CusDevPlanMapper cusDevPlanMapper;

	@Override
	public EasyUIDataGrideResult cusDevPlanList(Integer page,Integer rows,CusDevPlan cusDevPlan) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		CusDevPlanExample cusDevPlanExample = new CusDevPlanExample();
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = cusDevPlanExample.createCriteria();
		if (cusDevPlan.getSaleChanceId()!= null) {
			criteria.andSaleChanceIdEqualTo(cusDevPlan.getSaleChanceId());
		}
		List<CusDevPlan> cusDevPlanlist = cusDevPlanMapper.selectByExample(cusDevPlanExample);
		//得到total
		PageInfo<CusDevPlan> pageInfo = new PageInfo<CusDevPlan>(cusDevPlanlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(cusDevPlanlist);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			cusDevPlanMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(CusDevPlan cusDevPlan) {
		// TODO Auto-generated method stub
		if (cusDevPlanMapper.insert(cusDevPlan) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(CusDevPlan cusDevPlan) {
		// TODO Auto-generated method stub
		if (cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		// TODO Auto-generated method stub
		if (cusDevPlanMapper.deleteByPrimaryKey(id)>0) {
			return ServerResponse.createSuccess("删除数据成功！");
		}
		return ServerResponse.createError("删除数据失败！");
	}

}
