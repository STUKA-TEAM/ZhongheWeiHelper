function sendAjax(data, url, successFunc, type){
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.onreadystatechange=function()
	  {		
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		 successFunc();
	  }
	xmlhttp.open("POST",url);
	xmlhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
	xmlhttp.send(data);
	
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

function getNoSpace(obj){
	return obj.replace(/\s+/g, '');
}
function switchAlert(mes){
var backgroud=document.getElementById("alertBack");
	backgroud.style.display ="block";
	backgroud.style.visibility = "visible";
var box=document.getElementById("alertBox");
	box.style.display ="block";
	box.style.visibility = "visible";
var text=document.getElementById("alertContent");
    text.innerHTML = mes;
}

function closeAlert(){
var backgroud=document.getElementById("alertBack");
	backgroud.style.display ="none";
	backgroud.style.visibility = "hidden";
var box=document.getElementById("alertBox");
	box.style.display ="none";
	box.style.visibility = "hidden";
}
