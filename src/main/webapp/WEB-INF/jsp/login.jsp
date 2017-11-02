<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function login(){
		$("#form").submit();			
	}
</script>
</head>
<body class="easyui-layout" style="background-image: url('');" >
	<div style="margin-left: 450px;margin-top: 200px">
		<div style="margin:20px 0;"></div>
		<form action="${ctx}/login/login.action" id="form">
			<div class="easyui-panel" id="panel" title="登录系统" style="width:400px;padding:30px 70px 20px 70px">
				<div style="margin-bottom:10px">
					<input class="easyui-textbox" id="name" name="name" style="width:100%;height:40px;padding:12px" data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox" id="password" name="password" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'密码',iconCls:'icon-lock',iconWidth:38">
				</div>
				<div style="margin-bottom:20px">
					<input type="checkbox" checked="checked">
					<span>记住密码</span>
				</div>
				<div>
<!-- 					<a href="javascript:login();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
						<span style="font-size:14px;">登录</span>
					</a> -->
					<button type="submit">登录</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>