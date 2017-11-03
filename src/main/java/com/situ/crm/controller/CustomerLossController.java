package com.situ.crm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.service.ICustomerLossService;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController {
	
	@Autowired
	private ICustomerLossService customerLossService;
		
	@RequestMapping("/customerLossManager")
	@ResponseBody
	public EasyUIDataGrideResult customerLossManager(Integer page,Integer rows,CustomerLoss customerLoss,Date beginTime,Date endTime){
		return customerLossService.customerLossList(page, rows,customerLoss,beginTime,endTime);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return customerLossService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerLoss customerLoss){
		return customerLossService.add(customerLoss);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerLoss customerLoss){
		return customerLossService.update(customerLoss);
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ServerResponse findById(Integer id){
		return customerLossService.findById(id);
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ServerResponse updateStatus(Integer id,Integer status){
		return customerLossService.updateStatus(id, status);
	}
	

}
