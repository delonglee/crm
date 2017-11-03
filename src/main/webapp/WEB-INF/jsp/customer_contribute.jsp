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
			url:'${ctx}/customer/customerContribute.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'name',title:'客户名称',width:80,align:'center'},    
			     {field:'contribute',title:'客户订单总金额',width:100,align:'center'},    		     
			]]  
		});	


		
	});	
	/*查询*/
	function doSearch(){
		/* 执行load方法  {}里是参数，key是field对应的customerName,传的参数是value */
		$("#datagrid").datagrid("load",{
			'name':$("#name").val(),
			});
	}
	</script>
</head>
<body>

	<table id="datagrid"></table>
	
	<!-- toolbar开始 -->
	<div id="toolbar">
		<div>
			客户名称：<input class="easyui-textbox" id="name" data-options="prompt:'客户名称'" style="width:150px"></input>    
		 	<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar结束 -->
</body>
</html>