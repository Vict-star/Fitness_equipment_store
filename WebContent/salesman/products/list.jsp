<%@page import="cn.itcast.itcaststore.domain.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/salesman/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/salesman/js/public.js"></script>
	<script type="text/javascript">
	    //添加商品
		function addProduct() {
			window.location.href = "${pageContext.request.contextPath}/salesman/products/add.jsp";
		}
		//删除商品
		function p_del() {   
			var msg = "您确定要删除该商品吗？";   
			if (confirm(msg)==true){   
			return true;   
			}else{   
			return false;   
			}   
		}   
	</script>
</HEAD>
<body>
	<br />
	<form action="${pageContext.request.contextPath}/findProductByManyCondition" method="post">
	<% User user=(User)request.getSession().getAttribute("user");%>
	<input type="hidden" name="product_category" value="${user.product_category}" /> &nbsp;
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="33"><strong><h2>查 询 条 件</h2></strong></TD>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品编号</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">商品名称：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="name" size="15" value="" id="Form1_userName" class="bg" />
								</td>
							</tr>
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="minprice" size="10" value="" />
									- 
									<input type="text" name="maxprice" size="10" value="" />
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
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" name="reset" value="&#37325;&#32622;" class="button_view" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="33"><strong><h2>商 品 列 表</h2></strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
					    <button><a href="${pageContext.request.contextPath}/Download2">下载榜单</a></button>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
						<button type="button" id="add" name="add" value="&#28155;&#21152;" class="button_add" onclick="addProduct()"><!-- 添加商品 -->
							&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="23%">商品编号</td>
								<td align="center" width="17%">商品名称</td>
								<td align="center" width="7%">商品价格</td>
								<td align="center" width="7%">商品数量</td>
								<td width="8%" align="center">商品类别</td>
								<td width="7%" align="center">好评总数</td>
								<td width="7%" align="center">本月销量</td>
								<td width="8%" align="center">本月销售额</td>
								<td width="5%" align="center">详情</td>
								<td width="5%" align="center">编辑</td>
								<td width="6%" align="center">删除</td>
							</tr>
                            <!--  循环输出所有商品 -->
							<c:forEach items="${ps}" var="p" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="23%">${p.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">${p.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="7%">${p.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="7%">${p.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${p.category}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="7%">${p.evaluate }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${nums[vs.count-1]}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${(p.price*nums[vs.count-1])}</td>
									<td align="center" style="HEIGHT: 22px" width="5%">
									<a href="${pageContext.request.contextPath}/Zxtshangpinyuce?id=${p.id}">
										<img src="${pageContext.request.contextPath}/salesman/images/button_view.gif" border="0" style="CURSOR: hand">
									</a>
								</td>
									<td align="center" style="HEIGHT: 22px" width="5%">
									    <a href="${pageContext.request.contextPath}/findProductById?id=${p.id}&type=admin"><!-- 编辑商品 -->
											<img src="${pageContext.request.contextPath}/salesman/images/i_edit.gif" border="0" style="CURSOR: hand"> 
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="6%">  <!-- 删除商品 -->
										<a href="${pageContext.request.contextPath}/deleteProduct?id=${p.id}" onclick="javascript:return p_del()">
												<img src="${pageContext.request.contextPath}/salesman/images/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
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