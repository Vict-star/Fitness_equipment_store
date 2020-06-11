<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script type="text/javascript">
	    //添加商品
		function addProduct() {
			window.location.href = "${pageContext.request.contextPath}/admin/salesmen/register.jsp";
		}
		//删除销售人员
		function p_del() {   
			var msg = "您确定要删除该销售人员吗？";   
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
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
			    <tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="33"><strong><h2>销售人员 列 表</h2></strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
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
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 28px; BACKGROUND-COLOR: #afd1f3">
								<td width="10%" align="center">编号</td>
								<td align="center" width="20%">账号名</td>
								<td align="center" width="10%">性别</td>
								<td align="center" width="20%">电话</td>
								<td align="center" width="20%">负责商品类别</td>
								<td width="10%" align="center">编辑</td>
								<td width="10%" align="center">删除</td>
							</tr>
							 <!--  循环输出所有销售人员 -->
							<c:forEach items="${uList}" var="u">
								<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="200">${u.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${u.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${u.gender }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">${u.telephone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">${u.product_category}</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
									    <a href="${pageContext.request.contextPath}/FindSalesmanById?id=${u.id}"><!-- 编辑销售 -->
											<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0" style="CURSOR: hand"> 
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">  <!-- 删除销售 -->
										<a href="${pageContext.request.contextPath}/deleteProduct?id=${p.id}" onclick="javascript:return p_del()">
												<img src="${pageContext.request.contextPath}/admin/images/i_del.gif"
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
</body>
</HTML>