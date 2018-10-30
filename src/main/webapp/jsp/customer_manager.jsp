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
			url:'${ctx}/customer/customerManager.action',
			method:'get',
			fit:true,
			singleSelect:false,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'num',title:'客户编号',width:80,align:'center'},    
			     {field:'name',title:'客户名称',width:100,align:'center'},    
			     {field:'managerName',title:'客户经理',width:80,align:'center'}, 
			     {field:'level',title:'客户等级',width:80,align:'center'},
			     {field:'phone',title:'联系电话',width:80,align:'center'},
			     {field:'address',title:'客户地址',width:80,align:'center'},
			     {field:'satisfy',title:'客户满意度',width:80,align:'center'},
			     {field:'credit',title:'客户信誉度',width:80,align:'center'}		     
			]]  
		});
		
		/*添加和修改弹出的dialog */
		$("#dialog").dialog({
			closed:'true',
			buttons:[
				{
					text:'保存',
					iconCls:'icon-ok',
					handler:function(){
						doSave();
					}
				},
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
			/* 执行load方法  {}里是参数，key是field对应的customerName,传的参数是value */
			$("#datagrid").datagrid("load",{
				'num':$("#numId").val(),
				'name':$("#nameId").val(),
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
						"${ctx}/customer/delete.action",
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
		/* 打开添加的dialog */
		function openAddDialog() {
			$("#dialog").dialog("open").dialog("setTitle","添加客户信息");
			$('#form').form("clear");
			url = "${ctx}/customer/add.action";
			
		}
		
		/* 打开修改dialog */
		function openUpdateDialog(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			$("#dialog").dialog("open").dialog("setTitle","修改客户信息");
			url = "${ctx}/customer/update.action";
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
		function openLinkMan(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			window.parent.openTab('客户联系人管理','${ctx}/customerLinkman/indexLink.action?customerId='+row.id,'icon-man');
		}
		function openContact(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			window.parent.openTab('客户交往记录管理','${ctx}/customerContact/indexContact.action?customerId='+row.id,'icon-man');
		}
		function openOrder(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			window.parent.openTab('客户历史订单查询','${ctx}/customerOrder/indexOrder.action?customerId='+row.id,'icon-man');
		}		
	</script>
</head>
<body>

	<table id="datagrid"></table>
	
	<!-- toolbar开始 -->
	<div id="toolbar">
		<div>
			<a class="easyui-linkbutton" href="javascript:openAddDialog()" plain='true' style=" margin-right: 20px" iconCls="icon-add">创建</a>
			<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" plain='true' style=" margin-right: 20px" iconCls="icon-edit">修改</a>
			<a class="easyui-linkbutton" href="javascript:doDelete()" plain='true' style=" margin-right: 20px" iconCls="icon-remove">删除</a>
			<a class="easyui-linkbutton" href="javascript:openLinkMan()" plain='true' style="margin-right: 20px" iconCls="icon-man">联系人管理</a>
			<a class="easyui-linkbutton" href="javascript:openContact()" plain='true' style="margin-right: 20px" iconCls="icon-tip">交往记录管理</a>
			<a class="easyui-linkbutton" href="javascript:openOrder()" plain='true' iconCls="icon-filter">历史订单管理</a>
		</div>
		<div>
			客户编号：<input class="easyui-textbox" id="numId" data-options="prompt:'客户编号'" style="width:150px"></input>
			客户名称：<input class="easyui-textbox" id="nameId" data-options="prompt:'客户名称'" style="width:200px"></input>		    
		 	<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog"  style="width:800;height: 500;padding: 10px 20px">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>客户名称：</td>
					<td><input type="text" id="name" name="name" class="easyui-validate" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>地区：</td>
					<td><input type="text" id="region" name="region" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>客户经理：</td>
					<td><input id="assignMan" id="managerName" name="manageName" class="easyui-combobox" data-options="    
			        valueField: 'trueName',    
			        textField: 'trueName',    
			        url: '${ctx}/user/findManageName.action',    
			        panelHeight:'auto'" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户等级：</td>
					<td><input id="assignMan" id="managerName" name="manageName" class="easyui-combobox" data-options="    
			        valueField: 'dataDicValue',    
			        textField: 'dataDicValue',    
			        url: '${ctx}/dataDic/findLevel.action',    
			        panelHeight:'auto'" /></td>
				</tr>
				<tr>
					<td>客户满意度：</td>
					<td><select type="text" id="satisfy" name="satisfy" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:173px">
		     		<option value="">请选择...</option>	
 					<option value="☆">☆</option>
 					<option value="☆☆">☆☆</option>	
 					<option value="☆☆☆">☆☆☆</option>	
 					<option value="☆☆☆☆">☆☆☆☆</option>
 					<option value="☆☆☆☆☆">☆☆☆☆☆</option>		
		     		</select></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户信用度：</td>
					<td><select type="text" id="credit" name="credit" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:173px">
		     		<option value="">请选择...</option>	
 					<option value="☆">☆</option>
 					<option value="☆☆">☆☆</option>	
 					<option value="☆☆☆">☆☆☆</option>	
 					<option value="☆☆☆☆">☆☆☆☆</option>
 					<option value="☆☆☆☆☆">☆☆☆☆☆</option>		
		     		</select></td>
				</tr>
				<tr>
					<td>邮政编码：</td>
					<td><input type="text" id="postCode" name="postCode" class="easyui-validate" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="phone" name="phone" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>传真：</td>
					<td><input type="text" id="fax" name="fax" class="easyui-validate" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>网址：</td>
					<td><input type="text" id="webSite" name="webSite" class="easyui-validate" /></td>
				</tr>												
				<tr>
					<td>客户地址：</td>
					<td><input type="text" id="address" name="address" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>营业执照注册号：</td>
					<td><input type="text" id="licenceNo" name="licenceNo" class="easyui-validate" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>法人：</td>
					<td><input type="text" id="legalPerson" name="legalPerson" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>注册资金(万元)：</td>
					<td><input type="text" id="bankroll" name="bankroll" class="easyui-validate" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>年营业额(万元)：</td>
					<td><input type="text" id="turnover" name="turnover" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>开户银行：</td>
		   			<td><input type="text"  id="bankName" name="bankName" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>开户账号：</td>
		   			<td><input type="text"  id="bankAccount" name="bankAccount"/>&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td>地税登记号：</td>
		   			<td><input type="text"  id="localTaxNo" name="localTaxNo" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>国税登记号：</td>
		   			<td><input type="text"  id="nationalTaxNo" name="nationalTaxNo"/>&nbsp;<font color="red">*</font></td>
				</tr>																				
			</table>
		</form>	
	</div>
	<!-- 添加和修改的dialog 结束 -->
	
</body>
</html>