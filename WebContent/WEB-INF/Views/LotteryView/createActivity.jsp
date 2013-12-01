<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<html>
<body>
<script src="../../../js/common/jquery-1.10.2.min.js"></script>
<script src="../../../js/lottery/createActivity.js"></script>
<script src="../../../js/common/tools.js"></script>

<div class="widget-box">
<div class="widget-title">
<span class="icon"><i class="icon-align-justify"></i></span>
<h5>活动详情 </h5>
</div>
<div class="widget-content nopadding form-horizontal">
<form method="post" action="tools/upload" enctype="multipart/form-data">
<div class="control-group">
   <label class="control-label">主题图片</label>
   <div class="controls">
       <input type="file" id="file-upload" name="LotteryPicture" accept="image/*"/>
       <input type="submit" class="btn btn-primary"/>
   </div>
</div>
</form>
<div class="control-group">
   <label class="control-label">活动标题</label>
   <div class="controls">
       <input type="text" name="LotteryName" value="${activity.lotteryName}" />
   </div>
</div>
<div class="control-group">
   <label class="control-label">活动简介</label>
   <div class="controls">
       <input type="text" name="LotterySummary" placeholder="不超过200字" maxlength="200" value="${activity.lotterySummary}"/>
   </div>
</div>
<div class="control-group">
   <label class="control-label">活动日期</label>
   <div class="controls">
      <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${activity.startDate}" var="startDate"/>
      <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${activity.endDate}" var="endDate"/>
      <span class="help-block">起始时间: </span>
      <input type="datetime-local" name="StartDate" step="600" value="${startDate}"/> 
      <span class="help-block">结束时间: </span>
      <input type="datetime-local" name="EndDate" step="600" value="${endDate}"/> 
   </div>
</div>
<div class="control-group">
   <label class="control-label">最大抽奖次数</label>
   <div class="controls">
        <input type="text" name="ChanceNum" value="${activity.chanceNum}"/>
   </div>
</div>
<div class="control-group">
   <label class="control-label">奖品设置</label>
   <div class="controls">
        <a href="javascript:addRow('prizeTable')" class="tip-top" data-original-title="添加"><i class="icon-plus-sign"></i></a>
        <a href="javascript:deleteRow('prizeTable')" class="tip-top" data-original-title="删除"><i class="icon-minus-sign"></i></a>
<!--         <input type="button" value="Add Row" onclick="addRow('prizeTable')" /> 
        <input type="button" value="Delete Row" onclick="deleteRow('prizeTable')" /> -->
        <table class="table table-bordered table-striped with-check" id="prizeTable">
         <tr>
            <th><i class="icon-list-alt"></i></th>
            <th>奖项名称</th>
            <th>奖品内容</th>
            <th>奖品数量</th>
            <th>中奖概率</th>
         </tr>
      <c:forEach items = "${prizelist}" var = "prizeitem" >
         <tr>
            <td><input type="checkbox" name="checkBox"/></td>
            <td><input type="text" value="${prizeitem.prizeName}"/></td>
            <td><input type="text" value="${prizeitem.prizeContent}"/></td>
            <td><input type="text" value="${prizeitem.luckyNum}"/></td>
            <td><input type="text" value="${prizeitem.luckyPercent}"/></td>
         </tr>
      </c:forEach>
         </table>
  </div>
</div>
<div class="form-actions">
   <button type="button" id="draft" class="btn btn-primary">保存为草稿</button>
   <button type="button" id="save" class="btn btn-primary">保存为待发布</button>
   <button type="button" id="release" class="btn btn-primary">立即发布</button>
   <button type="button" id="cancel" class="btn btn-primary">取消</button>
</div>
</div>
</div>

</body>
</html>