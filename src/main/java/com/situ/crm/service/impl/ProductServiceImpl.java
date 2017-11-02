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
import com.situ.crm.mapper.ProductMapper;
import com.situ.crm.pojo.Product;
import com.situ.crm.pojo.ProductExample;
import com.situ.crm.pojo.ProductExample.Criteria;
import com.situ.crm.service.IProductService;
import com.situ.crm.util.Util;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public EasyUIDataGrideResult productList(Integer page,Integer rows,Product product) {
		// TODO Auto-generated method stub
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		
		ProductExample productExample = new ProductExample();
		
		//1.添加pagehelper插件后 使用pagehelper设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows:执行分页之后的数据
		Criteria criteria = productExample.createCriteria();
		if (StringUtils.isNotEmpty(product.getName())) {
			criteria.andNameLike(Util.formatLike(product.getName()));
		}
		List<Product> productlist = productMapper.selectByExample(productExample);
		//得到total
		PageInfo<Product> pageInfo = new PageInfo<Product>(productlist);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(productlist);
		System.out.println(product.getName());
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		// TODO Auto-generated method stub
		String[] isArry = ids.split(",");
		for (String id : isArry) {
			productMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse add(Product product) {
		// TODO Auto-generated method stub
		if (productMapper.insert(product) > 0) {
			return ServerResponse.createSuccess("添加成功");
			
		}
		return ServerResponse.createError("添加失败");
	}

	@Override
	public ServerResponse update(Product product) {
		// TODO Auto-generated method stub
		if (productMapper.updateByPrimaryKeySelective(product)>0) {
			return ServerResponse.createSuccess("修改成功");
			
		}
		return ServerResponse.createError("修改失败");
	}

}
