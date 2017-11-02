package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLossMeasure;
import com.situ.crm.service.ICustomerLossMeasureService;

@Controller
@RequestMapping("/customerLossMeasure")
public class CustomerLossMeasureController {
	
	@Autowired
	private ICustomerLossMeasureService customerLossMeasureService;
	
	@RequestMapping("/index")
	public String index(){
		return "customer_loss_measure_manager";
	}
		
	@RequestMapping("/customerLossMeasureManager")
	@ResponseBody
	public EasyUIDataGrideResult customerLossMeasureManager(Integer page,Integer rows,CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.customerLossMeasureList(page, rows,customerLossMeasure);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return customerLossMeasureService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.add(customerLossMeasure);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.update(customerLossMeasure);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id){
		return customerLossMeasureService.deleteById(id);
	}

}
