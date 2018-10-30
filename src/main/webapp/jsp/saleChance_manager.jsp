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
			url:'${ctx}/saleChance/saleChanceManager.action',
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
			     {field:'customerName',title:'客户名称',width:100,align:'center'},    
			     {field:'overview',title:'概要',width:80,align:'center'}, 
			     {field:'linkMan',title:'联系人',width:80,align:'center'},
			     {field:'linkPhone',title:'联系电话',width:80,align:'center'},
			     {field:'createMan',title:'创建人',width:80,align:'center'},
			     {field:'createTime',title:'创建时间',width:80,align:'center'},
			     {field:'status',title:'状态',width:80,align:'center',formatter:function(value,row,index){
			    	 if (value==1) {
							return "已分配";
						}else{
							return "未分配";
						}
				     }}			     
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
			/* 执行load方法  {}里是参数，key是field对应的saleChanceName,传的参数是value */
			$("#datagrid").datagrid("load",{
				'customerName':$("#customerNames").val(),
				'createMan':$("#createMans").val(),
				'status':$("#statuss").val(),
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
						"${ctx}/saleChance/delete.action",
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
			$("#dialog").dialog("open").dialog("setTitle","添加信息");
			$('#form').form("clear");
 			$("#createMan").val("${user.trueName}");
			$("#createTime").val(Util.getCurrentDateTime());
			url = "${ctx}/saleChance/add.action";
			
		}
		
		/* 打开修改dialog */
		function openUpdateDialog(){
			var selections = $("#datagrid").datagrid("getSelections");
			if (selections.length == 0) {
				$.messager.alert('系统提示',"请选择要修改的数据");
				return;				
			}
			var row = selections[0];
			$("#dialog").dialog("open").dialog("setTitle","修改信息");
			url = "${ctx}/saleChance/update.action";
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
		function doExportExcel(){
			$.messager.confirm('确认','确认导出该表格',function(r){
				if (r) {
					window.location.href="${ctx}/saleChance/exportExcel.action";
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
			<a class="easyui-linkbutton" href="javascript:openAddDialog()" iconCls="icon-add">创建</a>
			<a class="easyui-linkbutton" href="javascript:openUpdateDialog()" iconCls="icon-edit">修改</a>
			<a class="easyui-linkbutton" href="javascript:doDelete()" iconCls="icon-remove">删除</a>
			<a class="easyui-linkbutton" href="javascript:doExportExcel()" iconCls="icon-add">导出表格</a>
		</div>
		<div>
			客户名称：<input class="easyui-textbox" id="customerNames" data-options="prompt:'客户名称'" style="width:150px"></input>
			创建人：<input class="easyui-textbox" id="createMans" data-options="prompt:'创建人'" style="width:200px"></input>
			状态：<select type="text" id="statuss" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:100px">
		     		<option value="">请选择...</option>	
 					<option value="0">未分配</option>
 					<option value="1">已分配</option>		
		     	</select>
		     时间：<input class="easyui-datebox" id="beginTime" name="beginTime"></input>至<input class="easyui-datebox" id="endTime" name="endTime"></input>
		 	<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog"  style="width:800;height: 400;padding: 10px 20px" >
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>客户名称：</td>
					<td><input type="text" id="customerName" name="customerName" class="easyui-validate" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>机会来源：</td>
					<td><input type="text" id="chanceSource" name="chanceSource" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td><input type="text" id="linkMan" name="linkMan" class="easyui-validate" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="linkPhone" name="linkPhone" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>成功率(%)：</td>
					<td><input type="text" id="successRate" name="successRate" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td><input type="text" id="overview" name="overview" class="easyui-validate" /></td>
				</tr>
				<tr>
					<td>机会描述：</td>
					<td><input id="description" id="description" name="description"  class="easyui-validate" data-options="multiline:true" style="width:300px;height:60px"/></td>
				</tr>
				<tr>
					<td>创建人：</td>
		   			<td><input type="text" editable="false" id="createMan" name="createMan" class="easyui-validatebox" required="true"/>&nbsp;<font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<<td>创建时间：</td>
		   			<td><input type="text" readonly="true" id="createTime" name="createTime"/>&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td>指派给：</td>
					<td><input id="assignMan" id="assignMan" name="assignMan" class="easyui-combobox" data-options="    
			        valueField: 'trueName',    
			        textField: 'trueName',    
			        url: '${ctx}/user/findByRole.action',    
			        panelHeight:'auto'" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>指派时间：</td>
					<td><input type="text" id="assignTime" name="assignTime"  readonly="readonly"></td>
				</tr>																
			</table>
		</form>	
	</div>
	<!-- 添加和修改的dialog 结束 -->
	
</body>
</html>