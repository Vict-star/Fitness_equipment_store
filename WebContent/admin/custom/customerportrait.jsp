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
					<td class="ta_01" align="center" bgColor="#afd1f3" height="35" style="FONT-SIZE: 16pt">
						用户画像
					</td>
				</tr>
				<tr><td><br></td></tr>
				<tr>
					<td class="ta_01" align="center"  height="28" style="FONT-SIZE: 14pt">
						用户评分：&nbsp;&nbsp;&nbsp;&nbsp;${score}
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center"  height="28" style="FONT-SIZE: 11pt">
						（根据近期登录时长，购买金额，购买数量等一系列规则打分，将有限资源投入到最重要的 20% 客户上，以最小投入获取最大收益）
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<br>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 32px; BACKGROUND-COLOR: #afd1f3">
								<td width="32%" align="center">用户编号：&nbsp;&nbsp;&nbsp;&nbsp;${user.id }</td>
								<td width="33%" align="center">用户名：&nbsp;&nbsp;&nbsp;&nbsp;${user.username }</td>
								<td width="35%" align="center">用户性别：&nbsp;&nbsp;&nbsp;&nbsp;${user.gender }</td>
							</tr>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 32px; BACKGROUND-COLOR: #afd1f3">
							    <td width="32%" align="center">用户电话：&nbsp;&nbsp;&nbsp;&nbsp;${user.telephone }</td>
								<td width="33%" align="center">用户邮箱： &nbsp;&nbsp;&nbsp;&nbsp;${user.email }</td>
								<td width="35%" align="center">用户注册时间：&nbsp;&nbsp;&nbsp;&nbsp;${user.registTime }</td>
							</tr>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 32px; BACKGROUND-COLOR: #afd1f3">
								<td width="32%" align="center">购买总数量：&nbsp;&nbsp;&nbsp;&nbsp;${arr[0] }&nbsp;本</td>
								<td width="33%" align="center">购买总金额：&nbsp;&nbsp;&nbsp;&nbsp;${arr[1] }&nbsp;元</td>
								<td width="35%" align="center">购买商品平均价格：&nbsp;&nbsp;&nbsp;&nbsp;${arr[2] }&nbsp;元/本</td>
							</tr>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 32px; BACKGROUND-COLOR: #afd1f3">
								<td width="32%" align="center">最常购买商品种类：&nbsp;&nbsp;&nbsp;&nbsp;${MostBuyCategory }</td>
								<td width="33%" align="center">最常浏览商品种类：&nbsp;&nbsp;&nbsp;&nbsp;${MostBrowserCategory }</td>
								<td width="35%" align="center">未来将买商品种类（近一周常浏览商品种类）：&nbsp;&nbsp;&nbsp;&nbsp;${WeekMostBrowserCategory }</td>
							</tr>
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 32px; BACKGROUND-COLOR: #afd1f3">
							    <td width="32%" align="center">购买商品平均浏览次数:&nbsp;&nbsp;&nbsp;&nbsp;${count}&nbsp;次</td>
								<td width="33%" align="center">最常上线IP地址：&nbsp;&nbsp;&nbsp;&nbsp;${IP}</td>
								<td width="35%" align="center">近2周登录总时长：&nbsp;&nbsp;&nbsp;&nbsp;${time }&nbsp;秒</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="30" style="FONT-SIZE: 14pt">
						消费金额统计与预测
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="30" style="FONT-SIZE: 14pt">
						<img src="${ChartLineURL}" > <!-- 折线图图片 -->>
					</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="30" style="FONT-SIZE: 14pt">
						购买记录列表
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 28px; BACKGROUND-COLOR: #afd1f3">
								<td width="12%" align="center">用户编号</td>
								<td width="30%" align="center">商品编号</td>
								<td width="12%" align="center">商品单价</td>
								<td width="12%" align="center">购买数量</td>
								<td width="14%" align="center">金额小计</td>
								<td width="20%" align="center">购买时间</td>
							</tr>
							 <!--  循环输出所有用户购买记录 -->
							<c:forEach items="${objects}" var="object" varStatus="vs">
								<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr2[vs.count-1][0] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="30%">${arr2[vs.count-1][1]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr2[vs.count-1][2]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">${arr2[vs.count-1][3]  }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">${arr2[vs.count-1][4] }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${arr2[vs.count-1][5] }</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr><td><br><br></td></tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" height="30" style="FONT-SIZE: 14pt">
						浏览记录列表
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 14pt; HEIGHT: 28px; BACKGROUND-COLOR: #afd1f3">
								<td width="14%" align="center">用户编号</td>
								<td width="32%" align="center">商品编号</td>
								<td width="18%" align="center">商品类别</td>
								<td width="22%" align="center">浏览时间</td>
								<td width="14%" align="center">停留时长/秒</td>
							</tr>
							 <!--  循环输出所有用户浏览记录 -->
							<c:forEach items="${bList}" var="b">
								<tr style="HEIGHT: 23px" onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">${b.user_id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="32%">${b.product_id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">${b.product_category }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="22%">${b.browse_time }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="14%">${b.stay_time}</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
</body>
</HTML>