<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</HEAD>
<body>
	<br />
	<form id="Form1" name="Form1" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				 <tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="35" style="FONT-SIZE: 15pt">
						查询条件
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">用户id：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">用户名：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="name" size="15" value="" id="Form1_userName" class="bg" />
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
					<td class="ta_01" align="center" bgColor="#afd1f3" height="35" style="FONT-SIZE: 15pt">
						用户列表
					</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
							    <td align="center" width="8%">用户评分</td>
								<td align="center" width="8%">用户编号</td>
								<td align="center" width="12%">用户名</td>
								<td align="center" width="10%">用户性别</td>
								<td align="center" width="14%">用户电话</td>
								<td align="center" width="22%">用户邮箱</td>
								<td align="center" width="18%">用户注册时间</td>
								<td align="center" width="8%">用户画像</td>
							</tr>
                            <!--  循环输出所有客户 -->
							<c:forEach items="${uList}" var="u" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="FONT-WEIGHT: bold; FONT-SIZE: 10pt; CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${u.score}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${u.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${u.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${u.gender }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">${u.telephone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="22%">${u.email}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${u.registTime }</td>
									<td align="center" style="HEIGHT: 22px" width="8%">
									   <a href="${pageContext.request.contextPath}/CustomerPortrait?id=${u.id }">
									     	<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
									   </a>
								    </td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>