package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.OrderItem;
import com.situ.crm.service.IOrderItemService;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {
	
	@Autowired
	private IOrderItemService orderItemService;
	
	@RequestMapping("/orderItemManager")
	@ResponseBody
	public EasyUIDataGrideResult orderItemManager(Integer page,Integer rows,OrderItem orderItem){
		return orderItemService.orderItemList(page, rows,orderItem);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return orderItemService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(OrderItem orderItem){
		return orderItemService.add(orderItem);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(OrderItem orderItem){
		return orderItemService.update(orderItem);
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id){
		return orderItemService.deleteById(id);
	}

}
