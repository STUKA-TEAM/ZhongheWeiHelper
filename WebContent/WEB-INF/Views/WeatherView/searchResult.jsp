<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div>
<c:out value = "${status}" />
<c:out value = "${weatherInfo}" />
<button type="button" onclick = "saveCity()">保存城市</button>
</div>