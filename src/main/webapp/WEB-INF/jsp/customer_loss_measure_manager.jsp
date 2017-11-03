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
		$.post("${ctx}/customerLoss/findById.action", 
				{id : '${param.lossId}'}, 
				function(result) {
					if(result.status==Util.SUCCESS) {
						$("#customerNoId").val(result.data.customerNo);
						$("#customerNameId").val(result.data.customerName);
						$("#customerManagerId").val(result.data.customerManager);
						$("#lastOrderTimeId").val(result.data.lastOrderTime);
					}
					
				}, 
				"json");
		
		/*展示数据的datagrid表格*/
		$("#datagrid").edatagrid({
			url:'${ctx}/customerLossMeasure/customerLossMeasureManager.action?lossId=${param.lossId}',//只查询已分配咨询师的
			saveUrl:'${ctx}/customerLossMeasure/add.action?lossId=${param.lossId}',
			updateUrl:'${ctx}/customerLossMeasure/update.action?lossId=${param.lossId}',
			destroyUrl:'${ctx}/customerLossMeasure/deleteById.action',
			title:'交往记录信息管理',
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			fit:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'measure',title:'暂缓措施',width:200,align:'center',editor:{type:'validatebox',options:{required:true}}}
			]]
		});
	});
	
	//更新销售机会客户流失状态
	function updatecustomerLossStatus(status) {
		$.post("${ctx}/customerLoss/updateStatus.action",
			{id:'${param.lossId}',status:status},
			function(result){
				if (result.status === Util.SUCCESS) {
					$.messager.alert("系统提示",'执行成功')
				}else{
					 $.messager.alert("系统提示","执行失败！");
				}
			}
		);
				
	}
	
		
</script>
</head>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="客户基本信息" style="width: 700px;height:200px;padding: 10px;margin-bottom: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户编号：</td>
	   			<td><input type="text" id="customerNoId" name="customerNo" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称</td>
	   			<td><input type="text" id="customerNameId" name="customerName" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>客户经理：</td>
	   			<td><input type="text" id="customerManagerId" name="customerManager" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>上次下单日期：</td>
	   			<td><input type="text" id="lastOrderTimeId" name="lastOrderTime" readonly="readonly"/></td>
	   		</tr>	   		
	   	</table>
	 </div>
	 <!-- 营销机会信息面板  结束  -->
	
	<!-- 客户开发计划项table -->
	<table id="datagrid"></table>
	
	<!-- toolbar 开始 -->
	 <div id="toolbar">
	 	<c:if test="${param.show!='true' }">
		 	<a href="javascript:$('#datagrid').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		 	<a href="javascript:$('#datagrid').edatagrid('destroyRow')" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		 	<a href="javascript:$('#datagrid').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true">撤销行</a>
		 	<a href="javascript:$('#datagrid').edatagrid('saveRow');$('#datagrid').edatagrid('reload');" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
		 	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" onclick="updatecustomerLossStatus(1)">确认流失</a>
	 	</c:if>
	 </div>
	<!-- toolbar 结束 -->
</body>
</html>