<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta name='viewport' content="width=device-width; initial-scale=1.0; maximum-scale=1.0; charset=UTF-8">
<title>实验 幼儿园</title>
<link href='../../../css/mobilestyle/style.css' rel='stylesheet'/>
<style></style>
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
<!-- 投票开始 -->
<!-- 列表开始 -->
<div class="smallList">
<a class="S-item">
<div class="S-itemContent">
<div class="S-itemText-T">下面哪个新年活动最有趣？</div>
<div class="S-itemText-D">2013-11-20</div>
</div>
</a>

<a class="S-item">
<div class="S-itemContent">
<div class="S-itemText-T">您是否赞成开设外教课？</div>
<div class="S-itemText-D">2013-11-23</div>
</div>
</a>

</div>
<!-- 列表结束 -->
<!-- 详情开始（多选） -->
<div class="article">
<div class="A-Title">下面哪个新年亲子活动您比较感兴趣？</div>
<div class="A-date">2013-11-23</div>
<div class="A-img">
<img src="userimages/1d2c7b431d874c78984edffa9f4cfab6_standard.jpg"></div>
<div class="A-singleblock">为迎接新年，我们将举行一年一度的亲子活动，您对下面哪个活动比较感兴趣？
</div>

<div class="vote-item">
<!-- <div class="vote-img"><img src=""></div> -->
	<div class="vote-text">歌唱比赛</div>
	<div class="vote-check">
	<input type="checkbox" id="1" name="cc" />
	<label for="1"></label>
	</div>
</div>

<div class="vote-item">
<!-- <div class="vote-img"><img src=""></div> -->
	<div class="vote-text">做游戏</div>
	<div class="vote-check">
	<input type="checkbox" id="2" name="cc" />
	<label for="2"></label>
	</div>
</div>

<div class="vote-item">
<!-- <div class="vote-img"><img src=""></div> -->
	<div class="vote-text">读故事</div>
	<div class="vote-check">
	<input type="checkbox" id="3" name="cc" />
	<label for="3"></label>
	</div>
</div>

<button class="button94">
提交</button>
</div>
<!-- 详情结束（多选） -->

<!-- 详情开始（单选） -->
<div class="article">
<div class="A-Title">您是否赞成开设外教课？</div>
<div class="A-date">2013-11-23</div>
<div class="A-img">
<img src="userimages/1d2c7b431d874c78984edffa9f4cfab6_standard.jpg"></div>
<div class="A-singleblock">为了给孩子在英语学习方面有一个比较高的起点，我们准备开设外教课程，不知道您对此有何看法？</div>

<div class="vote-item">
<!-- <div class="vote-img"><img src=""></div> -->
	<div class="vote-text">赞成</div>
	<div class="vote-check">
	<input type="radio" id="r1" name="rr" />
	<label for="r1"></label>
	</div>
</div>

<div class="vote-item">
<!-- <div class="vote-img"><img src=""></div> -->
	<div class="vote-text">反对</div>
	<div class="vote-check">
	<input type="radio" id="r2" name="rr" />
	<label for="r2"></label>
	</div>
</div>

<button class="button94">
提交</button>
</div>
<!-- 详情结束（单选） -->

<!-- 投票结果开始 -->
<div class="article">
<div class="A-Title">您是否赞成开设外教课？</div>
<div class="A-date">2013-11-23</div>
<div class="A-img">
<img src="userimages/1d2c7b431d874c78984edffa9f4cfab6_standard.jpg"></div>
<div class="A-singleblock">为了给孩子在英语学习方面有一个比较高的起点，我们准备开设外教课程，不知道您对此有何看法？</div>
<div class="A-singleblock">
<div class="vote-result-name">反对(25%)</div>
<div class="vote-result-bar" style="width:25%"></div>
</div>

<div class="A-singleblock">
<div class="vote-result-name">赞成(75%)</div>
<div class="vote-result-bar" style="width:75%"></div>
</div>
<div class="message-container">
<textarea class="message-content" type="text" placeholder="您的留言将会令我们将工作做得更好！"></textarea>
<button class="message-submit" onclick="">发送</button>
</div>
</div>

<!-- 投票结果结束 -->
<!-- 投票结束 -->

</div>
<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>
</html>