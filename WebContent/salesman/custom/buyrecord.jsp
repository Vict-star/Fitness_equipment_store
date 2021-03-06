<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/salesman/css/Style.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="${pageContext.request.contextPath}/salesman/js/public.js"></script>
</HEAD>
<body>
	<br />
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
			<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong><h2>查 询 条 件</h2></strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品编号</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">用户编号</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff">
									<font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br /><br />
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;
									</button> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<input type="reset" name="reset" value="&#37325;&#32622;" class="button_view" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			    <tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="33"><strong><h2>用户购买记录</h2></strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<br><br>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 28px; BACKGROUND-COLOR: #afd1f3">
								<td width="12%" align="center">用户编号</td>
								<td width="30%" align="center">商品编号</td>
								<td width="12%" align="center">商品单价</td>
								<td width="12%" align="center">购买数量</td>
								<td width="14%" align="center">金额小计</td>
								<td width="20%" align="center">购买时间</td>
							</tr>
							 <!--  循环输出所有用户购买记录 -->
							<c:forEach items="${oList}" var="oList" varStatus="vs">
								<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr[vs.count-1][0] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="30%">${arr[vs.count-1][1]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr[vs.count-1][2]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr[vs.count-1][3]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">${arr[vs.count-1][4] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${arr[vs.count-1][5] }</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
</body>
</HTML>