<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>力动康体</title>
	<%-- 导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<!-- 导入首页轮播图css和js脚本 -->
	<link type="text/css" href="${pageContext.request.contextPath }/client/css/autoplay.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/client/js/autoplay.js"></script>
</head>

<body class="main">
	<%@include file="head.jsp"%>
	<%@include file="menu_search.jsp" %>
	<%-- <div id="divad">
		<img src="${pageContext.request.contextPath}/client/ad/index_ad.jpg"/>
	</div> --%>
	
	<!-- 图书商场首页轮播图  start -->
	<div id="box_autoplay">
    	<div class="list">
        	<ul>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad1.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad2.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad3.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad4.jpg" width="900" height="335" /></li>
            	<li><img src="${pageContext.request.contextPath }/client/ad/index_ad5.jpg" width="900" height="335" /></li>
        	</ul>
    	</div>
	</div>
	<!-- 图书商场首页轮播图  end -->	
	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td width="497">
					<img src="${pageContext.request.contextPath}/client/images/billboard.gif" width="497" height="38" />
					<table cellspacing="0" class="ctl">
						<tr>
							<td width="485" height="100%">${n.details }</td>
						</tr>
					</table>
				</td>
				<td style="padding:5px 15px 10px 40px">
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/client/images/hottitle.gif" width="126" height="29" />
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0">
						<tr>
						<c:forEach items="${pList }" var="pArray">
							<td style="width:80; text-align:center">
								<a href="${pageContext.request.contextPath}/findProductById?id=${pArray[0]}">
									<img src="${pageContext.request.contextPath }${pArray[2]}" width="125" height="130" border="0" /> 
								</a>
								<br/> 
								<a href="${pageContext.request.contextPath}/findProductById?id=${pArray[0]}">${pArray[1]}</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
						</c:forEach>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td style="padding:5px 15px 10px 40px">
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td>
								<font size="4">猜你喜欢</font> 
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0">
						<tr>
							<td style="width:80; text-align:center">
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">
									<img src="${pageContext.request.contextPath}/client/ProductPicture/aa.jpg" width="130" height="130" border="0" /> 
								</a>
								<br/> 
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">汤臣倍健蛋白粉</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
							<td style="width:80; text-align:center">
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">
									<img src="${pageContext.request.contextPath}/client/ProductPicture/bb.jpg" width="130" height="130" border="0" /> 
								</a>
								<br/> 
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">上海永久牌山地自行车</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
							<td style="width:80; text-align:center">
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">
									<img src="${pageContext.request.contextPath}/client/ProductPicture/cc.jpg" width="130" height="130" border="0" /> 
								</a>
								<br/> 
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">KELME卡尔美足球服</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
							<td style="width:80; text-align:center">
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">
									<img src="${pageContext.request.contextPath}/client/ProductPicture/ea.jpg" width="130" height="130" border="0" /> 
								</a>
								<br/> 
								<a href="${pageContext.request.contextPath}/findProductById?id=a233a604-3518-4ee1-818e-6ace2e4c7b83">欧洲HEAD海德哑铃</a>
								<%-- <br />作者:${pArray[2] } --%>
							</td>
							
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>
