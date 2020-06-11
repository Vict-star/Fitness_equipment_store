<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div  style="text-align:center">  
     <h2>分类销售折线统计图  </h2>
     <a href="${pageContext.request.contextPath}/FindProductByCategory">返回商品分类</a><br><br> 
     <img src="${chartLineURL}" >  
  </div>
</body>
</html>