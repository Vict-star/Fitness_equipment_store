<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
.height1{width:100%; height:74px; }
.img_logo{
	min-width:100%;
	max-width:100%;
	height:74px;
}

</style>
<link href="${pageContext.request.contextPath}/salesman/css/Style.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">
//退出确认框3
function exitSystem(){
	var msg = "您确定要退出吗？";   
    if (confirm(msg)==true){  
    	window.top.location.href=$("#contextPath").val()+"/index.jsp";
    	return true;   
    }else{   
    	return false;   
    }
	
}

</script>
</HEAD>
<body>
	<div class="height1">
		<img class="img_logo" src="${pageContext.request.contextPath}/salesman/images/top_11.png">
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="1">
		<tr>
			<td height="15" valign="bottom"
				background="${pageContext.request.contextPath}/salesman/images/mis_01.jpg">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" height="38">
					<tr>
						<td width="70%" align="left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<font color="#000000" size="3">
								<script language="JavaScript">	
									tmpDate = new Date();
									date = tmpDate.getDate();
									month = tmpDate.getMonth() + 1;
									year = tmpDate.getFullYear();
									document.write(year);
									document.write("年");
									document.write(month);
									document.write("月");
									document.write(date);
									document.write("日 ");

									myArray = new Array(6);
									myArray[0] = "星期日"
									myArray[1] = "星期一"
									myArray[2] = "星期二"
									myArray[3] = "星期三"
									myArray[4] = "星期四"
									myArray[5] = "星期五"
									myArray[6] = "星期六"
									weekday = tmpDate.getDay();
									if (weekday == 0 | weekday == 6) {
										document.write(myArray[weekday])
									} else {
										document.write(myArray[weekday])
									};								
								</script> 
							</font>
						</td>
						<td width="30%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="70" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/salesman/images/mis_05a.jpg" width="6" height="30">
									</td>
									<td width="155" valign="bottom" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<font color="blue" ><a href="${pageContext.request.contextPath}/index.jsp" target="_top" ><h2>返回首页</h2></a> </font>
									</td>
									<td width="10" align="right" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/salesman/images/mis_05c.jpg" width="6" height="30">
									</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td width="70" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/salesman/images/mis_05a.jpg" width="6" height="30">
									</td>
									<td width="155" valign="bottom" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<font color="blue" ><a href="${pageContext.request.contextPath}/logout" target="_top" onclick="javascript:return exitSystem()"><h2>退出系统</h2></a> </font>
									</td>
									<td width="10" align="right" background="${pageContext.request.contextPath}/salesman/images/mis_05b.jpg">
										<img src="${pageContext.request.contextPath}/salesman/images/mis_05c.jpg" width="6" height="30">
									</td>
								</tr>
							</table>
						</td>
						<td align="right" width="5%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>