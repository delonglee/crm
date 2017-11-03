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
	
	//用户管理
	@RequestMapping("/user")
	public String user(){
		return "user_manager";
	}
	//产品管理
	@RequestMapping("/product")
	public String product(){
		return "product_manager";
	}
	//数据字典管理
	@RequestMapping("/dataDic")
	public String dataDic(){
		return "dataDic_manager";
	}
	//营销机会管理
	@RequestMapping("/saleChance")
	public String saleChance(){
		return "saleChance_manager";
	}
	
	//
	@RequestMapping("/cusdevplanManage")
	public String cusdevplanManage(){
		return "cusdevplanManage";
	}
	
	//客户信息管理
	@RequestMapping("/customer")
	public String customerManager(){
		return "customer_manager";
	}
	
	//顾客流失管理
	@RequestMapping("/customerLoss")
	public String customerLoss(){
		return "customer_loss_manager";
	}
	
	//服务创建
	@RequestMapping("/customerServiceSet")
	public String customerServiceSet(){
		return "customer_service_set";
	}
	
	//服务分配
	@RequestMapping("/customerServceAllot")
	public String customerServceAllot(){
		return "customer_service_allot";
	}
	
	//服务处理
	@RequestMapping("/customerServceDispose")
	public String customerServceDispose(){
		return "customer_service_dispose";
	}
	
	//服务反馈
	@RequestMapping("/customerServceBack")
	public String customerServceBack(){
		return "customer_service_back";
	}
	
	//服务归档
	@RequestMapping("/customerServceSave")
	public String customerServceSave(){
		return "customer_service_save";
	}
	
	//客户贡献分析
	@RequestMapping("/customerContribute")
	public String customerContribute(){
		return "customer_contribute";
	}

}
