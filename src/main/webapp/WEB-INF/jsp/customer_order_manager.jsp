<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/resources/thirdlib/jquery-easyui/jquery.edatagrid.js"></script>
<script type="text/javascript">
	$(function(){
		//查询指定id的销售机会
		$.post("${ctx}/customer/findById.action", 
				{id : '${param.customerId}'}, 
				function(result) {
					if(result.status==Util.SUCCESS) {
						$("#numId").val(result.data.num);
						$("#nameId").val(result.data.name);
					}
					
				}, 
				"json");
		
		/*展示数据的datagrid表格*/
		$("#datagrid").edatagrid({
			url:'${ctx}/customerOrder/customerOrderManager.action?customerId=${param.customerId}',//只查询已分配咨询师的
			title:'用户历史订单',
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			fit:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'orderNo',title:'订单号',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'orderDate',title:'订购日期',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'address',title:'送货地址',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}, 
			     {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){
			    	 if (value==0) {
							return "未回款";
						}
			    	 if (value==1) {
							return "已回款";
						}			    	 
				     }
			     },
			     {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
			    	 return "<a href='javascript:openOrderItem("+row.id+")'>查看订单明细</a>";
			     }},			     
			]]
		});
	});
	
	function openOrderItem(id){
		$("#dialog").dialog("open").dialog("setTitle","订单详细");
		url =  "${ctx}/orderItem/orderItemManager.action?orderId="+id;
		$("#datagridOrder").edatagrid({
			url:url,
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'productName',title:'产品名称',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'productNum',title:'产品数量',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'unit',title:'产品单位',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},
			     {field:'price',title:'产品价格',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},
			     {field:'sum',title:'总金额',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}	
			]]
		});
	}	
</script>
</head>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="客户基本信息" style="width: 700px;height: 100px;padding: 10px;margin-bottom: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户编号：</td>
	   			<td><input type="text" id="numId" name="num" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称</td>
	   			<td><input type="text" id="nameId" name="name" readonly="readonly"/></td>
	   		</tr>
	   	</table>
	 </div>
	 <!-- 营销机会信息面板  结束  -->
	
	<!-- 客户开发计划项table -->
	<table id="datagrid"></table>
	
		<!-- 添加和修改的dialog 开始 -->
	<div id="dialog" class="easyui-dialog" style="width:650px;height:400px,padding: 10px 20px" closed="true">
		<table id="datagridOrder" style="height: 200px"></table>
	</div>
	<!-- 添加和修改的dialog 结束 -->
</body>
</html>