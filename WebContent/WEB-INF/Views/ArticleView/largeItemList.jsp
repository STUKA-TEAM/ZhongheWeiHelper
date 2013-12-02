<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML> 
<html>
<head>
<title>实验 幼儿园</title>
<meta charset='UTF-8'>
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/> 
<link href='../../css/mobilestyle/style.css' rel='stylesheet'/>
<style>

</style>

</head>
<body>
<div id="container">
<header>
<nav id="topNav">
<a class="navButton"href="index.html">
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
<!-- 大块列表开始 -->
<c:forEach items="${articles}" var="article">
<div class="largeListItem">
<div class="L-itemTitle">${article.title}</div>
<div class="L-itemTime"><fmt:formatDate value="${article.date}"
            pattern="MM/dd/yyyy HH:mm:ss" /></div>
<div class="L-itemImg"><img src = "../..${article.imagePath}" /></div>
<div class="L-link">
<a class="L-link-text" href="articlebyid?articleId=${article.articleId}">查看全文</a>
<div class="L-link-logo"></div>
</div>
</div>
</c:forEach>
<!-- 大块列表结束 -->



</div>

<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>
<script src='../../js/mobile/tools.js'></script>
</html>