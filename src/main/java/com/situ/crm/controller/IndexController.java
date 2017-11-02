package com.situ.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "user_manager";
	}
	
	@RequestMapping("/product")
	public String product(){
		return "product_manager";
	}
	
	@RequestMapping("/dataDic")
	public String dataDic(){
		return "dataDic_manager";
	}
	
	@RequestMapping("/saleChance")
	public String saleChance(){
		return "saleChance_manager";
	}
	
	@RequestMapping("/cusdevplanManage")
	public String cusdevplanManage(){
		return "cusdevplanManage";
	}
	
	@RequestMapping("/customer")
	public String customerManager(){
		return "customer_manager";
	}
	
	@RequestMapping("customerLoss")
	public String customerLoss(){
		return "customer_loss_manager";
	}
	

}
