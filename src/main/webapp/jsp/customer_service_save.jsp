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
			url:'${ctx}/customerService/customerServiceManagerTwo.action?status=已归档',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'id',title:'编号',width:80,align:'center'},    
			     {field:'customer',title:'客户名称',width:100,align:'center'},    
			     {field:'overview',title:'概要',width:80,align:'center'}, 
			     {field:'serviceType',title:'服务类型',width:80,align:'center'},
			     {field:'createPeople',title:'创建人',width:80,align:'center'},
			     {field:'createTime',title:'创建时间',width:80,align:'center'},	     
			]]  
		});
		
		/*添加和修改弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
			buttons:[
				{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#dialog").dialog("close");
					}
				}
				
			]
			
		});
		
		//点击指派人，触发时间
		$(function(){
			$("#assignMan").combobox({
				onSelect:function(record){
					if(record.trueName!=''){
						$("#assignTime").val(Util.getCurrentDateTime());
					}else{
						$("#assignTime").val("");
					}
				}
			}); 
		 });
	});


		/*查询*/
		function doSearch(){
			/* 执行load方法  {}里是参数，key是field对应的customerServiceName,传的参数是value */
			$("#datagrid").datagrid("load",{
				'customer':$("#customerId").val(),
				'overview':$("#overviewId").val(),
				'serviceType':$("#serviceTypeId").val(),
				'beginTime':$("#beginTime").val(),
				'endTime':$("#endTime").val()
				});
		}
		
		/* 删除 */
		function doDelete(){
			//getSelections none 返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。得到的是object对象 
			var selectedIds = $("#datagrid").datagrid("getSelections");
	/* 		//输出selectedIds
			console.log(selectedIds); */
			var ids = [];//[1,2,3]
			//遍历
			for(var i in selectedIds){
				ids.push(selectedIds[i].id);
			}
			ids = ids.join(",");// 1,2,3
			if (ids.length == 0) {
				$.messager.alert("系统提示", "请选择要删除的数据");
				return;
			}
			$.messager.confirm("系统提示", "您确认要删除么", function(r){
				if (r){
					$.post(
						"${ctx}/customerService/delete.action",
						{ids:ids}, 
						function(result) {
							$.messager.alert("系统提示", result.msg);
							if(result.status == 0) {
								$("#datagrid").datagrid("reload");
							}
						},
						"json"
					);
				}
			})
		}
		
		var url;
		
		/* 打开修改dialog */
		function openAllot(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			$("#dialog").dialog("open").dialog("setTitle","修改信息");
			//$("#assignTime").datebox('setValue',Util.getCurrentDateTime());
			url = "${ctx}/customerService/update.action";
			$("#form").form("load",row);
		}
		
		/* 关闭dialog */
		function closeDialog(){
			$("#dialog").dialog("close");
		}
		
		/* 添加和修改的保存 */
		function doSave(){
			$("#form").form('submit',{
				url:url,
				success:function (data){
					//json字符串转化成json格式
					var data = eval('('+data+')');
					if (data.status == Util.SUCCESS) {
						$.messager.alert("系统提示",data.msg);
						$("#dialog").dialog("close");
						//reload 重载行。等同于'load'方法，但是它将保持在当前页。
						$("#datagrid").datagrid("reload");
						
					}
				}
			});
		}
	</script>
</head>
<body>

	<table id="datagrid"></table>
	
	<!-- toolbar开始 -->
	<div id="toolbar">
		<div>
			<a class="easyui-linkbutton" href="javascript:openAllot()" iconCls="icon-add">查看客户服务详情</a>
		</div>
		<div>
			客户名称<input class="easyui-textbox" id="customerId" data-options="prompt:'客户名称'" style="width:150px"></input>
			概要：<input class="easyui-textbox" id="overviewId" data-options="prompt:'概要'" style="width:200px"></input>
			服务类型：<select type="text" id="serviceTypeId" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:100px">
		     		<option value="">请选择...</option>	
 					<option value="咨询">咨询</option>
 					<option value="建议">建议</option>
 					<option value="投诉">投诉</option>		
		     	</select>
		  	   创建日期：<input class="easyui-datebox" id="beginTime" name="beginTime"></input>至<input class="easyui-datebox" id="endTime" name="endTime"></input>
		 		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>	
	</div>
	<!-- toolbar结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog"  style="width:800;height:400;padding: 10px 20px" >
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>服务类型：</td>
					<td><input type="text" id="serviceType" readonly="true" name="serviceType" class="easyui-validate"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户名称：</td>
					<td><input type="text" id="customer" readonly="true"  name="customer" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td><input type="text" id="overview" readonly="true" name="overview" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>服务请求：</td>
					<td><input id="serviceRequest" readonly="true"  name="serviceRequest"  class="easyui-validate" data-options="multiline:true" style="width:300px;height:60px"/></td>
				</tr>
				<tr>
					<td>创建人：</td>
		   			<td><input type="text" editable="false" readonly="true" id="createPeople" name="createPeople" class="easyui-validatebox"/>&nbsp;<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>创建时间：</td>
		   			<td><input type="text" readonly="true" id="createTime" name="createTime"/>&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td>分配给：</td>
					<td><input type="text" editable="false" readonly="true" id="assigner" name="assigner" class="easyui-validatebox"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>指派时间：</td>
					<td><input type="text" id="assignTime" name="assignTime" readonly="true"></td>
				</tr>
				<tr>
					<td>服务处理：</td>
					<td><input id="serviceDeal" readonly="true"  name="serviceDeal" class="easyui-validatebox" data-options="multiline:true" style="width:300px;height:60px"/></td>
				</tr>				
				<tr>
					<td>处理人：</td>
		   			<td><input type="text" editable="false" readonly="true" id="serviceDealPeople" name="serviceDealPeople" class="easyui-validatebox"/></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>处理时间：</td>
		   			<td><input type="text" readonly="true" id="serviceDealTime" name="serviceDealTime"  />&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td>处理结果：</td>
		   			<td><input type="text" editable="false" readonly="true" id="serviceDealResult" name="serviceDealResult" class="easyui-validatebox"/>&nbsp;<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>满意度：</td>
		   			<td><input type="text" editable="false" readonly="true" id="satisfy" name="satisfy" class="easyui-validatebox"/>&nbsp;<font color="red">*</font></td>
				</tr>																								
			</table>
		</form>	
	</div>
	<!-- 添加和修改的dialog 结束 -->
	
</body>
</html>