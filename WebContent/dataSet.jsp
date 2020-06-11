<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>3大统计图</title>
<!--
<script type="text/javascript" src="jquery.cookie.js"></script>
<script type="text/javascript" src="jquery-3.3.1.js"></script>
<script type="text/javascript" src="jquery-3.3.1.min.js"></script> 
 -->
 
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script type="text/javascript">
var second = 0;
window.setInterval(function () {
    second ++;
}, 1000);
var tjArr = localStorage.getItem("jsArr") ? localStorage.getItem("jsArr") : '[{}]';
$.cookie('tjRefer', getReferrer() ,{expires:1,path:'/'});
function getReferrer() {
    var referrer = '';
    try {
        referrer = window.top.document.referrer;
    } catch(e) {
        if(window.parent) {
            try {
                referrer = window.parent.document.referrer;
            } catch(e2) {
                referrer = '';
            }
        }
    }
    if(referrer === '') {
        referrer = document.referrer;
    }
    return referrer;
}
function bb(){
	window.localStorage.clear()
}
function aa(){
	if($.cookie('tjRefer') == ''){
        var tjT = eval('(' + localStorage.getItem("jsArr") + ')');
        if(tjT){
            tjT[tjT.length-1].time += second;
            var jsArr= JSON.stringify(tjT);
            alert("1:"+jsArr);
            console.log("4:"+jsArr);
        	document.write("5:"+jsArr);
            localStorage.setItem("jsArr", jsArr);
        }
    } else {
        var tjArr = localStorage.getItem("jsArr") ? localStorage.getItem("jsArr") : '[{}]';
        var dataArr = {
            'url' : location.href,
            'time' : second,
            'refer' : getReferrer(),
            'timeIn' : Date.parse(new Date()),
            'timeOut' : Date.parse(new Date()) + (second * 1000)
        };
        tjArr = eval('(' + tjArr + ')');
        tjArr.push(dataArr);
        tjArr= JSON.stringify(tjArr);
        alert("2:"+tjArr);
        console.log("6:"+tjArr);
    	document.write("7:"+tjArr);
        localStorage.setItem("jsArr", tjArr);
        $.ajax({
            type: "POST",
            url : "${pageContext.request.contextPath }/testAjax",
            data:  {"tjArr":tjArr},
            success: function(msg){}
         }); 
    }
}
window.onbeforeunload = function() {
	
};
</script>
<style type="text/css">
.img{
width:800px;
height:600px;
border: 0;
color: gray;
} 
</style>
</head>
<!-- 得到3大统计图 -->
<body>
<div  style="text-align:center">  
                    柱状图 <br><br>  
                     点击生成柱状图<a href="Zzt" >getMajorChart</a>  
     <br><br>  
     <img src="${chartColumnURL}" >  
  </div>
  <div  style="text-align:center">  
                饼状图 <br><br>  
                     点击生成饼状图<a href="BztServlet" >getMajorChart</a>  
     <br><br>  
     <img src="${chartPieURL}" >  
  </div>  
   <div  style="text-align:center">  
               折线图 <br><br>  
                     点击生成折线图<a href="ZxtServlet">getMajorChart</a>  
     <br><br>  
     <img src="${chartLineURL}" >  
  </div>
  <a href="${pageContext.request.contextPath}/ZxtYuce?category=文学">预测销量</a> 
</body>
</html>