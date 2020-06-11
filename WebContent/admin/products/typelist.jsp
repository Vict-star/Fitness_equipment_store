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
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="33"><strong><h2>商 品 列 表</h2></strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
					    <a href="${pageContext.request.contextPath}/Download3"><button>下载榜单</button></a>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 28px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="20%">商品类别</td>
								<td width="13%" align="center">销售人员编号</td>
								<td width="17%" align="center">销售人员姓名</td>
								<td align="center" width="17%">本月销售总数量</td>
								<td align="center" width="20%">本月销售总金额</td>
								<td width="13%" align="center">详细查看</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">蛋白粉</td>
								<td align="center" width="13%">${salesIds[0] }</td>
								<td align="center" width="17%">${salesnames[0] }</td>
								<td align="center" width="17%">${nums[0] }</td>
								<td align="center" width="20%">${moneys[0] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=1">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">跑步机</td>
								<td align="center" width="13%">${salesIds[1] }</td>
								<td align="center" width="17%">${salesnames[1] }</td>
								<td align="center" width="17%">${nums[1] }</td>
								<td align="center" width="20%">${moneys[1] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=2">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">哑铃</td>
								<td align="center" width="13%">${salesIds[2] }</td>
								<td align="center" width="17%">${salesnames[2] }</td>
								<td align="center" width="17%">${nums[2] }</td>
								<td align="center" width="20%">${moneys[2] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=3">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">动感单车</td>
								<td align="center" width="13%">${salesIds[3] }</td>
								<td align="center" width="17%">${salesnames[3] }</td>
								<td align="center" width="17%">${nums[3] }</td>
								<td align="center" width="20%">${moneys[3] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=4">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">仰卧起坐椅</td>
								<td align="center" width="13%">${salesIds[4] }</td>
								<td align="center" width="17%">${salesnames[4] }</td>
								<td align="center" width="17%">${nums[4] }</td>
								<td align="center" width="20%">${moneys[4] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=5">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">力量训练器</td>
								<td align="center" width="13%">${salesIds[5] }</td>
								<td align="center" width="17%">${salesnames[5] }</td>
								<td align="center" width="17%">${nums[5] }</td>
								<td align="center" width="20%">${moneys[5] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=6">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">自行车</td>
								<td align="center" width="13%">${salesIds[6] }</td>
								<td align="center" width="17%">${salesnames[6] }</td>
								<td align="center" width="17%">${nums[6] }</td>
								<td align="center" width="20%">${moneys[6] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=7">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
							<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"onmouseout="this.style.backgroundColor = '#F5FAFE';">
							    <td align="center" width="20%">运动服</td>
								<td align="center" width="13%">${salesIds[7] }</td>
								<td align="center" width="17%">${salesnames[7] }</td>
								<td align="center" width="17%">${nums[7] }</td>
								<td align="center" width="20%">${moneys[7] }</td>
								<td align="center" style="HEIGHT: 22px" width="13%">
										<a href="${pageContext.request.contextPath}/ZxtYuce?category=8">
											<img src="${pageContext.request.contextPath}/admin/images/button_view.gif" border="0" style="CURSOR: hand">
										</a>
									</td>
							</tr>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
</body>
</HTML>