<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

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
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${draftlist}" var = "activity" >
      <tr>
          <td class="taskName"><c:out value = "${activity.lotteryName}"/></td>
          <td class="taskOptions"><a href="http://localhost:8080/ZhongheWeiHelper/lottery/store/newactivity/create" class="btn btn-primary btn-mini">编辑</a></td>
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
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${savedlist}" var = "activity" >
      <tr>
          <td class="taskName"><c:out value = "${activity.lotteryName}"/></td>
          <td class="taskOptions"><a href="http://localhost:8080/ZhongheWeiHelper/lottery/store/newactivity/create" class="btn btn-primary btn-mini">编辑</a></td>
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
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${releasedlist}" var = "activity" >
      <tr>
          <td class="taskName"><c:out value = "${activity.lotteryName}"/></td>
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
       <th>操作 </th>
   </tr>
</thead>
<tbody>
   <c:forEach items = "${closedlist}" var = "activity" >
      <tr>
          <td class="taskName"><c:out value = "${activity.lotteryName}"/></td>
          <td class="taskOptions"><a href="#" class="btn btn-primary btn-mini">编辑</a></td>
      </tr>
   </c:forEach>
</tbody>
</table>
</div>
</div>