<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE HTML>
<html>
<head>
<meta name='viewport' content="width=device-width, initial-scale=1.0, maximum-scale=1.0, charset=UTF-8">
<title>实验 幼儿园</title>
<link href='../../../css/mobilestyle/style.css' rel='stylesheet'/>
<style>
</style>
</head>
<body>
<div id="container">
<header>
<nav id="topNav">
<a class="navButton"href="../../../index.html">
<img class="navButtonBack" src="../../../moduleimages/home.png"/>
</a>
<div class="navButton" id="navTragger" onclick="switchMenu()" >
<img class="navButtonBack" src="../../../moduleimages/module.png"/>
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
<!-- 详情开始（多选） -->
<div class="article">
<fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
<div class="A-Title">${activity.voteTitle}</div>
<div class="A-date">${startDate}</div>
<div class="A-img">
<img src="../../../userimages/${activity.votePicture}"></div>
<div class="A-singleblock">${activity.voteSummary}</div>

<c:forEach items = "${itemlist}" var = "voteItem" >
    <div class="vote-item">
     <c:if test = "${not empty voteItem.itemImage}">
        <div class="vote-img"><img src="../../../userimages/${voteItem.itemImage}"></div>
     </c:if>
	<div class="vote-text">${voteItem.itemDesc}</div>
	<div class="vote-check">
	<input type="checkbox" id="${voteItem.itemId}" name="cc" value='${voteItem.itemId}'/>
	<label for="${voteItem.itemId}"></label>
	</div>
</div>  
</c:forEach>
<button class="button94" onclick="saveChoices('${openId}')">提交</button>
</div>
<!-- 详情结束（多选） -->
</div>
<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>

<script src="../../../js/common/tools.js"></script>
<script type="text/javascript">
function sendAjax(data, url, successFunc){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		  successFunc(xmlhttp.responseText);
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);	
}
function saveChoices(openId){
	var choices = new Array();
	var checkboxs = document.getElementsByName('cc');
	var count = 0;
	for (var i = 0, length = checkboxs.length; i < length; i++) {
	    if (checkboxs[i].checked) {
	    	var choice = new Object();
	    	choice.openId = openId;
	        choice.itemId = parseInt(checkboxs[i].value, 10);
	    	choices.push(choice);
	    	count = count + 1;
	    }
	}
	var url = getRootPath() + "/vote/user/submit/choice/multi";
	if(count == 0){
		alert('Please select a choice!');
	}else{
		sendAjax(JSON.stringify(choices), url, checkResult);
	}
}
function checkResult(message){
	if(JSON.parse(message).status == true){
		window.location.href = getRootPath() + "/vote/user/activityresult?voteId=" + ${activity.voteId};
	}
}  
</script>
</html>