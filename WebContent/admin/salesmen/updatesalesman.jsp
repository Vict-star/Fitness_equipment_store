<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath}/admin/css/Style.css" type="text/css" rel="stylesheet">
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/check.js"></script>
	<script type="text/javascript">
		//设置类别的默认值
		function setProductCategory(t) {
			var category = document.getElementById("category");
	
			var ops = category.options;
			for ( var i = 0; i < ops.length; i++) {
	
				if (ops[i].value == t) {
					ops[i].selected = true;
					return;
				}
			}
		};
	</script>
</HEAD>
<body onload="setProductCategory('${u.product_category}')">
	<form action="${pageContext.request.contextPath}/UpdateSalesman" method="post">
		<input type="hidden" name="id" value="${u.id}" /> &nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="38">
					<h1><strong>编辑销售人员</strong></h1>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01" height="34">销售人员：</td>
				<td class="ta_01" bgColor="#ffffff"><h3>${u.username }</h3></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01" height="34">修改密码：</td>
				<td class="ta_01" bgColor="#ffffff">
				     <input type="password" class="textinput"  id="password" name="password" onkeyup="checkPassword();" value="${u.password}"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01" height="34">销售人员性别：</td>
				<td class="ta_01" bgColor="#ffffff">&nbsp;&nbsp;
				<input type="radio" name="gender" value="男" ${u.gender=='男'?'checked':'' }/> 男
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="gender" value="女"  ${u.gender=='女'?'checked':'' }/> 女
				</td>
			</tr>
			<tr>
			    <td align="center" bgColor="#f5fafe" class="ta_01" height="34">销售人员电话：</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" name="telephone" value="${u.telephone}" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" height="34">负责的商品类别：</td>
				<td class="ta_01" bgColor="#ffffff">
					<select name="product_category" id="category">
						<option value="">--选择商品类加--</option>
						<option value="">无</option>
						<option value="蛋白粉">蛋白粉</option>
						<option value="跑步机">跑步机</option>
						<option value="哑铃">哑铃</option>
						<option value="动感单车">动感单车</option>
						<option value="仰卧起坐椅">仰卧起坐椅</option>
						<option value="力量训练器">力量训练器</option>
						<option value="自行车">自行车</option>
						<option value="运动服">运动服</option>
					</select>
				</td>
			</tr>
			<TR>
				<td align="center" colSpan="4" class="sep1" height="20">
					<img src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</TR>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4" height="40">
					<input type="submit" value="确定"> 
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<input type="reset" value="重置" /> 
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> 
					<INPUT type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span>
				</td>
			</tr>
		</table>
	</form>
</body>
