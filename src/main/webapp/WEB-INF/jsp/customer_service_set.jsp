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
 function save(){
	 $("#form").form('submit',{
		 url:'${ctx}/customerService/add.action',
		 success:function(data){
			 var data = eval('('+data+')');
			 if (data.status==Util.SUCCESS) {
				$.messager.alert("系统提示",data.msg)
			}
		 }
	 });
 }
 
 function agin(){
	 $("#form").form("clear");
 }
 $(function(){
	 $("#createTimeId").dateBox('setValue','2014545')
 });
</script>
</head>
<body>
	<!-- 服务创建面板  开始 -->
	<div id="p" class="easyui-panel" title="客户服务创建" style="width: 700px;height: 350px;padding: 10px;margin-bottom: 10px">
		 <form id="form" action="">
		 	<table cellspacing="8px">
		   		<tr>
		   			<td>服务类型：</td>
		   			<td><input type="text" id="serviceTypeId" name="serviceType" class="easyui-combobox"
					 data-options="
					 	url:'${ctx}/dataDic/findService.action',
					 	valueField: 'dataDicValue',
					 	textField: 'dataDicValue',
					 	panelHeight:'auto',
					 	editable:false  "/></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>客户：</td>
		   			<td><input type="text" id="customerId" name="customer" /></td>
		   		</tr>
		   		<tr>
		   			<td>概要：</td>
		   			<td colspan="4">
		   				<textarea rows="5" cols="50" id="overviewId" name="overview" ></textarea>
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>服务请求：</td>
		   			<td colspan="4">
		   				<textarea rows="5" cols="50" id="serviceRequestId" name="serviceRequest" ></textarea>
		   			</td>
		   		</tr>	   		
		   		<tr>
		   			<td>创建人：</td>
		   			<td><input type="text" value="${user.trueName}" id="createPeopleId" name="createPeople" /></td>
		   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   			<td>创建时间：</td>
		   			<td><input type="text" id="createTimeId" name="createTime" class="easyui-datebox"/></td>
		   		</tr>
		   		<tr>
		   			<td>
		   				<a class="easyui-linkbutton" icon='icon-save' href="javascript:save();">保存</a>
		   				<a class="easyui-linkbutton" icon='icon-save' href="javascript:agin();">重置</a>
		   			</td>		   		
		   		</tr>
		   	</table>
		 </form>	  	
	 </div>
	 <!-- 服务创建  结束  -->
</body>
</html>