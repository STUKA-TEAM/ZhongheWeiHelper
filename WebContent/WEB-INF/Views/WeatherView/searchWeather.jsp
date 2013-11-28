<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>天气查询</title>
</head>
<body>
<input type="text" id ="searchInput" />
<button type="button" id = "searchButton" onclick="search()">
查询
</button>
<div id="result">
</div>

<div id="hot">
</div>

<div id="saved">
<c:forEach items="${savedCities}" var="savedcity">
<c:out value="${savedcity.city}" />
</c:forEach>
</div>
</body>

<script src="../../js/common/tools.js"></script>
<script type="text/javascript">
function sendAjax(data, url, successFunc, type){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		  if(type=="saveCity")successFunc();
	      if(type=="search")successFunc(xmlhttp.responseText);
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);
	
}
function saveCity()
{
	var message = new Object();
	message.openId = "123234safdf44";
	message.city = "上海";
	var url = getRootPath() + "/weather/user/savecity";
	sendAjax(JSON.stringify(message), url, addNewSaved, "saveCity");	
}
function addNewSaved(){
	var newCity = document.getElementById("searchInput").value;
	var textnode=document.createTextNode(newCity);
	document.getElementById("saved").appendChild(textnode);
}

function search(){
	var city = document.getElementById("searchInput").value;
	if(city == null ||city.replace(/\s+/g, '')==""){
		alert("请输入要查询的城市！");
	}else{
		var url = getRootPath() + "/weather/user/searchcity";
		sendAjax(city, url, addResult, "search");
	}
	
}
function addResult(result){
	document.getElementById("result").innerHTML=result;
}

</script>

</html>