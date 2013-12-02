<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<div class="widget-box">
<div class="widget-title">
   <span class="icon">
      <i class="icon-th-list"></i>
   </span>
   <h5>草稿箱</h5>
</div>
<div class="widget-content nopadding">
<table class="table table-striped table-hover table-bordered">
<thead>
   <tr>
       <th>活动标题 </th>
       <th>起始时间 </th>
       <th>结束时间</th>
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${draftlist}" var = "activity" >
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.endDate}" var="endDate"/>
      <tr>          
          <td class="taskName"><c:out value = "${activity.voteTitle}"/></td>
          <td class="taskName"><c:out value = "${startDate}"/></td>
          <td class="taskName"><c:out value = "${endDate}"/></td>
          <td class="taskOptions"><a href="javascript:loadActivity('${activity.voteId}')" class="btn btn-primary btn-mini">编辑</a></td>
      </tr>
   </c:forEach>
</tbody>
</table>
</div>
</div>

<div class="widget-box">
<div class="widget-title">
   <span class="icon">
      <i class="icon-th-list"></i>
   </span>
   <h5>待发布活动</h5>
</div>
<div class="widget-content nopadding">
<table class="table table-striped table-hover table-bordered">
<thead>
   <tr>
       <th>活动标题 </th>
       <th>起始时间 </th>
       <th>结束时间</th>
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${savedlist}" var = "activity" >
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.endDate}" var="endDate"/>
      <tr>          
          <td class="taskName"><c:out value = "${activity.voteTitle}"/></td>
          <td class="taskName"><c:out value = "${startDate}"/></td>
          <td class="taskName"><c:out value = "${endDate}"/></td>
          <td class="taskOptions"><a href="http://localhost:8080/ZhongheWeiHelper/vote/store/newactivity/create" class="btn btn-primary btn-mini">编辑</a></td>
      </tr>
   </c:forEach>
</tbody>
</table>
</div>
</div>

<div class="widget-box">
<div class="widget-title">
   <span class="icon">
      <i class="icon-th-list"></i>
   </span>
   <h5>正在进行的活动</h5>
</div>
<div class="widget-content nopadding">
<table class="table table-striped table-hover table-bordered">
<thead>
   <tr>
       <th>活动标题 </th>
       <th>起始时间 </th>
       <th>结束时间</th>
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${releasedlist}" var = "activity" >
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.endDate}" var="endDate"/>
      <tr>          
          <td class="taskName"><c:out value = "${activity.voteTitle}"/></td>
          <td class="taskName"><c:out value = "${startDate}"/></td>
          <td class="taskName"><c:out value = "${endDate}"/></td>
          <td class="taskOptions"><a href="#" class="btn btn-primary btn-mini">编辑</a></td>
      </tr>
   </c:forEach>
</tbody>
</table>
</div>
</div>

<div class="widget-box">
<div class="widget-title">
   <span class="icon">
      <i class="icon-th-list"></i>
   </span>
   <h5>已结束的活动</h5>
</div>
<div class="widget-content nopadding">
<table class="table table-striped table-hover table-bordered">
<thead>
   <tr>
       <th>活动标题 </th>
       <th>起始时间 </th>
       <th>结束时间</th>
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${closedlist}" var = "activity" >
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.startDate}" var="startDate"/>
      <fmt:formatDate pattern="yyyy-MM-dd' 'HH:mm" value="${activity.endDate}" var="endDate"/>
      <tr>          
          <td class="taskName"><c:out value = "${activity.voteTitle}"/></td>
          <td class="taskName"><c:out value = "${startDate}"/></td>
          <td class="taskName"><c:out value = "${endDate}"/></td>
          <td class="taskOptions"><a href="#" class="btn btn-primary btn-mini">编辑</a></td>
      </tr>
   </c:forEach>
</tbody>
</table>
</div>
</div>