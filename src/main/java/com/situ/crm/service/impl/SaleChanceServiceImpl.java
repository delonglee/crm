package com.situ.crm.service.impl;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.mapper.SaleChanceMapper;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.SaleChanceExample;
import com.situ.crm.pojo.SaleChanceExample.Criteria;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.util.Util;

@Service
public class SaleChanceServiceImpl implements ISaleChanceService{
	
	@Autowired
	private SaleChanceMapper saleChanceMapper;

	@Override
	public EasyUIDataGrideResult saleChanceList(Integer page,Integer rows,SaleChance saleChance,Date beginTime,Date endTime) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		SaleChanceExample saleChanceExample = new SaleChanceExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = saleChanceExample.createCriteria();
		if (StringUtils.isNotEmpty(saleChance.getCustomerName())) {
			criteria.andCustomerNameLike(Util.formatLike(saleChance.getCustomerName()));
		}
		if (StringUtils.isNotEmpty(saleChance.getCreateMan())) {
			criteria.andCreateManLike(Util.formatLike(saleChance.getCreateMan()));
		}
		if (saleChance.getStatus() != null) {
			criteria.andStatusEqualTo(saleChance.getStatus());
		}
		if (saleChance.getDevResult() != null) {
			criteria.andDevResultEqualTo(saleChance.getDevResult());
		}
		if (beginTime != null && endTime != null) {
			criteria.andCreateTimeBetween(beginTime, endTime);
		}
		
		List<SaleChance> saleChancelist = saleChanceMapper.selectByExample(saleChanceExample);
		//得到total
		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(saleChancelist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(saleChancelist);
		System.out.println(saleChance.getCustomerName());
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(SaleChance saleChance) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(saleChance.getAssignMan())){
			saleChance.setStatus(1);//已分配
		}else{
			saleChance.setStatus(0);//0：:未分配
		}
		saleChance.setDevResult(0);
		if (saleChanceMapper.insert(saleChance) > 0) {
			return ServerResponse.createSuccess("添加成功");			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(SaleChance saleChance) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(saleChance.getAssignMan())){
			saleChance.setStatus(1);//已分配
		}else{
			saleChance.setStatus(0);//0：:未分配
		}
		if (saleChanceMapper.updateByPrimaryKeySelective(saleChance)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

	@Override
	public List<SaleChance> findSaleChanceStatus() {
		// TODO Auto-generated method stub
		return saleChanceMapper.findSaleChanceStatus();
	}

	@Override
	public ServerResponse findById(Integer id) {
		// TODO Auto-generated method stub
		SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
		if(saleChance != null){
			//前台需要用所以也要传过去data
			return ServerResponse.createSuccess("查找成功",saleChance);
		}		
		return ServerResponse.createError("查找失败!");
	}

	@Override
	public ServerResponse updateDevResult(Integer saleChanceId, Integer devResult) {
		// TODO Auto-generated method stub
		SaleChance saleChance = new SaleChance();
		
		saleChance.setId(saleChanceId);
		saleChance.setDevResult(devResult);
		if (saleChanceMapper.updateByPrimaryKeySelective(saleChance)>0) {
			return ServerResponse.createSuccess("开发成功！");
		}
		return ServerResponse.createError("开发失败！");
	}

}
