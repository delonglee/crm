<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	<script type="text/javascript">
	$(function(){
		/*展示数据的datagrid表格*/
		$("#datagrid").datagrid({
			url:'${ctx}/customerLoss/customerLossManager.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'id',title:'编号',width:80,align:'center'},    
			     {field:'customerNo',title:'客户编号',width:100,align:'center'},    
			     {field:'customerName',title:'客户名称',width:80,align:'center'}, 
			     {field:'customerManager',title:'客户经理',width:80,align:'center'},
			     {field:'lastOrderTime',title:'上次下单日期',width:80,align:'center'},
			     {field:'confirmLossTime',title:'确认流失日期',width:80,align:'center'},
			     {field:'lossReason',title:'流失原因',width:80,align:'center'},
			     {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){
			    	 if (value==1) {
							return "确认流失";
						}else{
							return "暂缓流失";
						}
				 }},
			     {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
			    	 if(row.status==1){
			    		 return "用户确认流失";
			    	 }else{
			    		 return "<a href='javascript:openLossMeasure("+row.id+")'>暂缓流失</a>";
			    	 }
			     }}   		
			]]  
		});
	});

		/*查询*/
		function doSearch(){
			/* 执行load方法  {}里是参数，key是field对应的customerLossName,传的参数是value */
			$("#datagrid").datagrid("load",{
				'customerName':$("#customerNames").val(),
				'customerManager':$("#customerManagers").val(),
				'status':$("#statuss").val()
				});
		}
		
		function openLossMeasure(id){
			window.parent.openTab("客户流失暂缓管理",'${ctx}/customerLossMeasure/index.action?lossId='+id,'icon-man');
		}
	</script>
</head>
<body>

	<table id="datagrid"></table>
	
	<!-- toolbar开始 -->
	<div id="toolbar">
		<div>
			客户名称：<input class="easyui-textbox" id="customerNames" data-options="prompt:'客户名称'" style="width:150px"></input>
			客户经理：<input class="easyui-textbox" id="customerManagers" data-options="prompt:'客户经理'" style="width:200px"></input>
			客户状态：<select type="text" id="statuss" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:100px">
		     		<option value="">请选择...</option>	
 					<option value="0">暂缓流失</option>
 					<option value="1">确认流失</option>		
		     	</select>
		 	<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar结束 -->
	
</body>
</html>