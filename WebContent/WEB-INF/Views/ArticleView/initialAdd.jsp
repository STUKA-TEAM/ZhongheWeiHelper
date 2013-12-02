<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加文章</title>
<link href="../../css/common/uploadify.css" rel="stylesheet" type="text/css"></link>
</head>
<body>
<input type="text" id="title"/>

 <input id="file-upload" name="file-upload" type="file" />
 <input id="imgPathID" value = " " type="hidden" />
 <img id="uploadedPic" src=" "/>
 
<textarea  id="content"> </textarea>

<select id="articleClassId">
  <option value="1" selected="selected">教师风采</option>
  <option value="2">儿童天地</option>
  <option value="3">教师心得</option>
  <option value="4">园所新闻</option>
  <option value="5">育儿技巧</option>
</select>

<button type="button" onclick="submitArticle()">添加</button>
</body>
<script src="../../js/common/jquery-1.10.2.min.js"></script>
<script src="../../js/common/jquery.uploadify.min.js"></script>
<script src="../../js/common/swfobject.js"></script>
<script src="../../js/common/tools.js"></script>
<script type="text/javascript">
$(document).ready(uploadImg());
function sendAjax(data, url, successFunc){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200){
		  successFunc(xmlhttp.responseText);
	  }
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);	
}
function submitArticle()
{
	var article = new Object();
	article.title = $("#title").val();
	article.imagePath = $('#imgPathID').val();
	article.content = $('#content').val();
	article.articleClassId = $('#articleClassId').val();
	alert(JSON.stringify(article));
	var url = getRootPath() + "/article/store/add";
	sendAjax(JSON.stringify(article), url,notice);	
}
function notice(mes){
	alert(mes);
}

</script>
</html>