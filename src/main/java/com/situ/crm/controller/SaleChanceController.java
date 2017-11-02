package com.situ.crm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.service.ISaleChanceService;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {
	
/*	@InitBinder
	protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }*/

	@Autowired
	private ISaleChanceService saleChanceService;
		
	@RequestMapping("/saleChanceManager")
	@ResponseBody
	public EasyUIDataGrideResult saleChanceManager(Integer page,Integer rows,SaleChance saleChance,Date beginTime,Date endTime){
		return saleChanceService.saleChanceList(page, rows,saleChance,beginTime,endTime);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return saleChanceService.delete(ids);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(SaleChance saleChance){
		return saleChanceService.add(saleChance);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(SaleChance saleChance){
		return saleChanceService.update(saleChance);
	}
	
	@RequestMapping("/findSaleChanceStatus")
	@ResponseBody
	public List<SaleChance> findSaleChanceStatus(){
		return saleChanceService.findSaleChanceStatus();
	}
	
	@RequestMapping("/findSaleChanceDevResult")
	@ResponseBody
	public List<SaleChance> findSaleChanceDevResult(){
		return saleChanceService.findSaleChanceStatus();
	}
	
	@RequestMapping("/findById")
	@ResponseBody
	public ServerResponse findById(Integer id){
		System.out.println("传的id"+id);
		return saleChanceService.findById(id);
	}
	
	@RequestMapping("/updateDevResult")
	public ServerResponse updateDevResult(Integer saleChanceId, Integer devResult){
		System.out.println(saleChanceId+devResult);
		return saleChanceService.updateDevResult(saleChanceId, devResult);
	}

}
