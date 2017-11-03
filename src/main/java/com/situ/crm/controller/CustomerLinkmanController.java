package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLinkman;
import com.situ.crm.service.ICustomerLinkmanService;

@Controller
@RequestMapping("/customerLinkman")
public class CustomerLinkmanController {
	
	@Autowired
	private ICustomerLinkmanService customerLinkmanService;
	
	@RequestMapping("/indexLink")
	public String index(){
		return "customer_linkman_manager";
	}
		
	@RequestMapping("/customerLinkmanManager")
	@ResponseBody
	public EasyUIDataGrideResult customerLinkmanManager(Integer page,Integer rows,CustomerLinkman customerLinkman){
		return customerLinkmanService.customerLinkmanList(page, rows,customerLinkman);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return customerLinkmanService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerLinkman customerLinkman){
		return customerLinkmanService.add(customerLinkman);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerLinkman customerLinkman){
		return customerLinkmanService.update(customerLinkman);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id){
		return customerLinkmanService.deleteById(id);
	}
	
	@RequestMapping("/findLinkMan")
	@ResponseBody
	public List<CustomerLinkman> findLinkMan(){
		return customerLinkmanService.findLinkMan();
	}

}
