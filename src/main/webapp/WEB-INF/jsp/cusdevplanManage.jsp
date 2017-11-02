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
			checkOnSelect:false,
			columns:[[    
			     {field:'cb',checkbox:true,align:'center'},    
			     {field:'id',title:'编号',width:30,align:'center'},    
			     {field:'customerName',title:'客户名称',width:80,align:'center'},    
			     {field:'overview',title:'概要',width:80,align:'center'}, 
			     {field:'linkMan',title:'联系人',width:50,align:'center'},			     
			     {field:'createMan',title:'创建人',width:80,align:'center'},
			     {field:'createTime',title:'创建时间',width:80,align:'center'},
			     {field:'assignMan',title:'指派人',width:50,align:'center'},
			     {field:'assignTime',title:'指派时间',width:80,align:'center'},
			     {field:'devResult',title:'客户开发状态',width:80,align:'center',formatter:function(value,row,index){
			    	 if (value==0) {
							return "未开发";
						}
			    	 if (value==1) {
							return "开发中";
						}
			    	 if (value==2) {
							return "开发成功";
						}
			    	 if (value==3) {
							return "开发失败";
						}			    	 
				     }
			     },
			     {field:'a',title:'操作',width:80,align:'center',formatter:function(value,row,index){
			    	 if(row.devResult==0||row.devResult==1){
			    		 return "<a href='javascript:openCusDevPlanTab("+row.id+")'>开发</a>";
			    	 }else{
			    		 return "<a href='javascript:openCusDevPlanInfoTab("+row.id+")'>查看详细信息</a>";
			    	 }
			     }},    
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
		
	});
		/*查询*/
		function doSearch(){
			/* 执行load方法  {}里是参数，key是field对应的saleChanceName,传的参数是value */
			$("#datagrid").datagrid("load",{
				'customerName':$("#customerNames").val(),
				'overview':$("#overviews").val(),
				'devResult':$("#devResults").val(),
			})
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
		function openAddDialog(){
			$("#dialog").dialog("open").dialog("setTitle","添加信息");
			url =  "${ctx}/saleChance/add.action";
			$("#form").form("clear");
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
				/* 在提交之前触发，返回false可以终止提交。 */
				onSubmit:function(){
					//做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。
					return $(this).form("validate");
				},
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
		
		//可以修改添加开发项
		function openCusDevPlanTab(id){
			 window.parent.openTab('客户开发计划项管理','${ctx}/cusDevPlan/index.action?saleChanceId='+id,'icon-khkfjh');
		}
		 
		//只能查看开发信息
		function openCusDevPlanInfoTab(id){
			window.parent.openTab('查看客户开发计划项','${ctx}/cusDevPlan/index.action?saleChanceId='+id+'&show=true','icon-khkfjh');
		}
	</script>
</head>
<body>
	<table id="datagrid"></table>
	
	<!-- toolbar开始 -->
	<div id="toolbar">
		<div>
			客户名称：<input class="easyui-textbox" id="customerNames" data-options="prompt:'客户名称'" style="width:150px"></input>
			概要：<input class="easyui-textbox" id="overviews" data-options="prompt:'概要'" style="width:200px"></input>
			开发状态：<select type="text" id="devResults" class="easyui-combobox" 
		     		panelHeight="auto" editable="false" style="width:100px">
		     		<option value="">请选择...</option>	
 					<option value="0">未开发</option>
 					<option value="1">开发中</option>	
 					<option value="2">开发成功</option>	
 					<option value="3">开发失败</option>	
		     	</select>
		 	<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		</div>
	</div>
	<!-- toolbar结束 -->
	
	<!-- 添加和修改的dialog 开始 -->
	<div id="dialog" class="easyui-dialog" style="width:800;height: 400;padding: 10px 20px" buttons="#dialog-button" closed="true">
		<form action="" id="form" method="post">
			<input type="hidden" id="id" name="id"/>
			<table cellspacing="8px">
				<tr>
					<td>客户名称：</td>
					<td><input type="text" id="customerName" name="customerName" class="easyui-textbox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>机会来源：</td>
					<td><input type="text" id="chanceSource" name="chanceSource" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>联系人：</td>
					<td><input type="text" id="linkMan" name="linkMan" class="easyui-textbox" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>联系电话：</td>
					<td><input type="text" id="linkPhone" name="linkPhone" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>成功率(%)：</td>
					<td><input type="text" id="successRate" name="successRate" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td><input type="text" id="overview" name="overview" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>机会描述：</td>
					<td><input id="description" name="description" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:60px"/></td>
				</tr>
				<tr>
					<td>创建人：</td>
					<td><input type="text" id="createMan" value="${user.trueName}" name="createMan" class="easyui-textbox" required="true"/><font color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>创建时间：</td>
					<td><input class="easyui-datebox"></input><font color="red">*</font></td>
				</tr>
				<tr>
					<td>指派给：</td>
					<td><input id="assignMan" name="assignMan" class="easyui-combobox" data-options="    
			        valueField: 'trueName',    
			        textField: 'trueName',    
			        url: '${ctx}/user/findByRole.action',    
			        panelHeight:'auto'" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>指派时间：</td>
					<td><input class="easyui-datebox" id="assignTime" name="assignTime"></td>
				</tr>																
			</table>
		</form>	
	</div>
	<!-- 添加和修改的dialog 结束 -->
	
</body>
</html>