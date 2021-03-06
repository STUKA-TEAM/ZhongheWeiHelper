<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE HTML>
<html>
<head>
<meta name='viewport' content="width=device-width, initial-scale=1.0, maximum-scale=1.0, charset=UTF-8">
<title>实验 幼儿园</title>
<link href='../../css/mobilestyle/style.css' rel='stylesheet'/>
<style>
</style>
</head>
<body>
<div id="container">
<header>
<nav id="topNav">
<a class="navButton"href="../../index.html">
<img class="navButtonBack" src="../../moduleimages/home.png"/>
</a>
<div class="navButton" id="navTragger" onclick="switchMenu()" >
<img class="navButtonBack" src="../../moduleimages/module.png"/>
	<div id="moduleList">
      <a class="navModule">园所概况</a>
      <a class="navModule">教师风采</a>
      <a class="navModule">儿童天地</a>
	  <a class="navModule">调查问卷</a>
      <a class="navModule">参与投票</a>
      <a class="navModule">留言信箱</a>
	  <a class="navModule">教师心得</a>
      <a class="navModule">园所新闻</a>
      <a class="navModule">育儿技巧</a>
	  <a class="navModule">今日菜谱</a>
      <a class="navModule">招生入学</a>
      <a class="navModule">联系我们</a>
	</div>
</div>
<script type="text/javascript">
function switchMenu(){
var tragger=document.getElementById("moduleList");
if(tragger.style.display =="block"){
tragger.style.display ="none";
tragger.style.visibility = "hidden";
}else{
	
	tragger.style.display ="block";
	tragger.style.visibility = "visible";
	
}
}
function closeMenu(){
var tragger=document.getElementById("moduleList");
tragger.style.display ="none";
tragger.style.visibility = "hidden";
}
</script>
</nav>
</header>

<div id = "mainBody" onclick="closeMenu()">
<!-- 投票结果开始 -->
<div class="article">
<fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
<div class="A-Title">${activity.voteTitle}</div>
<div class="A-date">${startDate}</div>
<div class="A-img">
<img src="../../${activity.votePicture}"></div>
<div class="A-singleblock">${activity.voteSummary}</div>

<c:forEach items = "${activityresult}" var = "itemResult" >
   <div class="A-singleblock">
      <fmt:formatNumber type="number" maxFractionDigits="1" value="${itemResult.percent}" var="percent"/>
      <c:if test = "${not empty itemResult.voteItem.itemDesc}">
         <div class="vote-result-name">${itemResult.voteItem.itemDesc}(${percent}%)</div>
      </c:if>
      <c:if test = "${not empty itemResult.voteItem.itemImage}">
         <div class="vote-result-name">
            <img src="../../${itemResult.voteItem.itemImage}" />
         </div>
      </c:if>
      <div class="vote-result-bar" style="width:${percent}%"></div>
   </div>
</c:forEach>

<div class="message-container">
<textarea class="message-content" id="advice" placeholder="您的留言将会令我们将工作做得更好！"></textarea>

<button class="message-submit" onclick="saveAdvice('${activity.voteId}', '${openId}')">发送</button>
</div>
</div>
<!-- 投票结果结束 -->

<!-- 弹窗开始-->
<div id="alertBack"></div>
<div id="alertBox">
<div id="alertTitle">提示: </div>
<div id="alertContent"></div>
<button class="button45" onclick="closeAlert()">确定</button>
</div>
<!-- 弹窗结束-->
</div>
<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>

<script src="../../js/common/tools.js"></script>
<script src="../../js/mobile/tools.js"></script>
<script type="text/javascript">
function sendAjax(data, url, successFunc){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200){
		  successFunc(xmlhttp.responseText);
	  }else{
		  switchAlert('抱歉，网络较差请重新提交！');
	  }		  
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);	
}
function saveAdvice(voteId, openId){
	var advice = new Object();
	advice.openId = openId;
	advice.voteId = voteId;
	advice.adviceContent = document.getElementById('advice').value;
	if(advice.adviceContent == ''){
		switchAlert('说点儿什么吧..');
	}
	else{
		var url = getRootPath() + "/vote/user/submit/advice";
	    sendAjax(JSON.stringify(advice), url, checkResult);
	}
}
function checkResult(message){
	if(JSON.parse(message).status == true){
		switchAlert('留言上传成功！');
	}else{
		switchAlert('您的留言太多了..');
	}
}  
</script>
</html>