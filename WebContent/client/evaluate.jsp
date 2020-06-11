<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.itcast.cn/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>
<body class="main">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/client/myAccount.jsp">我的账户</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/findOrderByUser">订单查询</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
							  <form action="${pageContext.request.contextPath}/evaluate" method="post">
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:${order.id}</p>
										</td>
										<td><input type="hidden" name="orderid" value=${order.id}></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="8%">序号</td>
													<td width="38%">商品名称</td>
													<td width="10%">价格</td>
													<td width="10%">数量</td>
													<td width="20%">小计</td>
													<td width="25%">评价</td>
												</tr>
											</table> 
											<c:forEach items="${order.orderItems}" var="item" varStatus="vs">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="7%">${vs.count}</td>
														<td width="39%">${item.p.name}</td>
														<td width="10%">${item.p.price }</td>
														<td width="10%">${item.buynum }</td>
														<td width="12%">${item.buynum*item.p.price }</td>
														<td width="35%"">
										                      <input type="radio" name=${vs.count} value="好评" checked="checked"/>好评
											                   &nbsp;&nbsp;&nbsp;
											                  <input type="radio" name=${vs.count} value="中评"/>中评
											                  &nbsp;&nbsp;&nbsp;
											                  <input type="radio" name=${vs.count} value="差评"/>差评
										                </td>
										                <td><input type="hidden" name=${vs.count+50} value=${item.p.id}></td>
													</tr>
												</table>
											</c:forEach>
											<hr>
										</td>
									</tr>
								  </table>
								  <p style="text-align:center">
									  <input type="image" src="${pageContext.request.contextPath }/client/images/botton_gif_025.gif" name="submit" border="0" width="140" height="35"/>
								  </p>
								  <p style="text-align:center">&nbsp;</p>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
