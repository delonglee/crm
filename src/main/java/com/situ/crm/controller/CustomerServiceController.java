package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.service.ICustomerServiceService;

@Controller
@RequestMapping("/customerService")
public class CustomerServiceController {
	
	@Autowired
	private ICustomerServiceService customerServiceService;
		
	@RequestMapping("/customerServiceManager")
	@ResponseBody
	public EasyUIDataGrideResult customerServiceManager(Integer page,Integer rows,CustomerService customerService){
		return customerServiceService.customerServiceList(page, rows,customerService);
	}
	
	@RequestMapping("/customerServiceManagerTwo")
	@ResponseBody
	public EasyUIDataGrideResult customerServiceManagerTwo(Integer page,Integer rows,CustomerService customerService){
		return customerServiceService.customerServiceListTwo(page, rows,customerService);
	}	
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return customerServiceService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerService customerService){
		System.out.println("CustomerServiceController.add()");
		return customerServiceService.add(customerService);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerService customerService){
		return customerServiceService.update(customerService);
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ServerResponse updateStatus(Integer id,String status){
		return customerServiceService.updateStatus(id,status);
	}

}
