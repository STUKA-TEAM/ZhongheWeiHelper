<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html>
<head>
<title>实验 幼儿园</title>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/> 
<link href='../css/mobilestyle/style.css' rel='stylesheet'/>
</head>
<body>
<div id="container">
<header>
<nav id="topNav">
<a class="navButton"href="index.html">
<img class="navButtonBack" src="../moduleimages/home.png"/>
</a>

<div class="navButton" id="navTragger" onclick="switchMenu()" >
<img class="navButtonBack" src="../moduleimages/module.png"/>
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
<script src='../js/mobile/tools.js'></script>
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

<!-- 留言开始 -->
<div class="message-container">
<input id="username" class="message-name" type="text" placeholder="请输入您的称呼" />
<input id="contact" class="message-contact" type="text" placeholder="请输入联系方式" />
<textarea id="content" class="message-content" placeholder="请输入留言内容"></textarea>
<button class="message-submit" onclick="addMessage()">发送</button>
</div>
<!-- 留言结束 -->

<!-- 弹窗开始-->
<div id="alertBack"></div>
<div id="alertBox">
<div id="alertTitle">提示</div>
<div id="alertContent">
成功！
</div>
<button class="button45" onclick="closeAlert()">
确定
</button>
</div>
<!-- 弹窗结束-->


<script type="text/javascript">
function sendAjax(data, url, successFunc){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200){
		  if(xmlhttp.responseText == "y"){
			  successFunc("谢谢您的参与！");
		  }else{
			  successFunc("抱歉，网络较差请重新提交！");
		  }		
	  }    
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);	
}

function addMessage()
{
	var message = new Object();
	message.userName = document.getElementById("username").value;
	message.contact = document.getElementById("contact").value;
	message.content = document.getElementById("content").value;
	var url = getRootPath() + "/message/addmessage";
	if(message.content.length<10){
		switchAlert("请再多写点吧！");
	}else{
		sendAjax(JSON.stringify(message), url, notice);	
	}
	
}
function notice(mes){
	switchAlert(mes);
}
</script>
</div>

<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>
</html>