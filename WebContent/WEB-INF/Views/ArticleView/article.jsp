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

<div class="article">
<div class="A-Title"><c:out value="${article.title}"></c:out></div>
<div class="A-date"><fmt:formatDate value="${article.date}"
            pattern="MM/dd/yyyy HH:mm:ss" /></div>
<div class="A-img">
<img src="../..${article.imagePath}"></div>
<div class="A-singleblock">你常和自己的孩子谈心吗?也许你会说:小毛孩子，啥也不懂，有什么好谈的?或者认为，一天到晚忙个不休，哪有功夫和小家伙解嘴皮才还有一些家长。生怕有损于自己的‘“尊严“，对孩子，开口便训，更不用说谈心了、其实，谈心是对孩子进行教育的一种行之有效的方法，做父毋的都应该学会运用这种方法。
<br/>
怎样和孩子谈心呢?<br/>
第一，要明确谈心的作用语言是思维的信号，通过谈心，父毋可以了解孩子想些什么，便于有针对性地进行教育，通过谈心，父母可以和孩子交换思想，增进感情，有些家长强调工作、家务忙，没有时间同孩子谈心，如果他们懂得了谈心的作用，一定能够找到机会的。<br/>
第二，要了解孩子的心理特点上小学的孩子，心直口快，喜怒衰乐都挂在脸上。他们在学校碰到高兴的事或不愉快的事，往往不等父母问，就会一古脑儿倒出来，这个时期，孩子的思想是向家长敞开的、但是，随着年龄的增长，孩子的心理也相应地会起变化，上中学的孩子就不再象上小学时那么直爽了，话也说得少了，似乎和爸爸妈妈不那么贴心了，常常不肯向父母吐露直情，国外有的心理学家称这一时期为“青少年闭锁期”。处于这一时期的孩子，虽然沉默寡言，把秘密埋藏在心里。但同时又希望被父毋和其他人理解了解了孩子的这些矛盾的心理特点，家长就应主动接近孩子，经常有意识地找孩子谈心，及时消除他们的思想顾虑，使孩子有话愿意和父母讲、只有这样，家长才能掌握孩子的思想脉络，有针对性地进行教育，<br/>
第三，父母要用平等的态度和孩子谈心。孩子年龄小，不容易理解深奥的道理，当孩子对一咎问题理解不了时，家长不能训斥，要做到”三心”。即“，细心”、，‘耐心”和“恒心”。细心，就是要仔细观察分析孩子的言行，善于发现孩子身上的优点和缺点;耐心，就是要态度和蔼，循循许诱，不搞“一言堂”，更不能搞“审讯式”，恒心。就是要有打“持久战”的精神，反复给孩子作思想工作，直到做通。总之，家长只有用平等的态度和孩子谈心，才可能有成效。
</div>

</div>



</div>

<footer><small>&copy; 上海市实验幼儿园</small></footer>
</div>
</body>
<script src='../../js/mobile/tools.js'></script>
</html>