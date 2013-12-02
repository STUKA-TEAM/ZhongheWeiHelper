<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="widget-box">
<div class="widget-title">
<span class="icon"><i class="icon-edit"></i></span>
<h5>编辑活动 </h5>
</div>
<div class="widget-content nopadding form-horizontal">
<div class="control-group">
   <label class="control-label">活动标题</label>
   <div class="controls">
       <input type="text" name="LotteryName" value="${activity.voteTitle}" />
   </div>
</div>
<div class="control-group">
   <label class="control-label">活动简介</label>
   <div class="controls">
       <textarea name="LotterySummary" placeholder="不超过200字" maxlength="200">${activity.voteSummary}</textarea>
   </div>
</div>
<div class="control-group">
   <label class="control-label">主题图片</label>
   <div class="controls">
       <input type="file" id="file-upload" name="file-upload" accept="image/*"/>
       <input id="imgPathID" value = " " type="hidden" name="LotteryPicture"/>
       <img id="uploadedPic" src="${activity.votePicture}"/>
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
   <label class="control-label">单选/多选</label>
   <div class="controls">
        <label><input type="radio" name="radios" /> 单选</label>
		<label><input type="radio" name="radios" /> 多选</label>
   </div>
</div>
<div class="control-group">
   <label class="control-label">选项设置</label>
   <div class="controls">
        <a href="javascript:addRow2('voteTable')" class="tip-top" data-original-title="添加"><i class="icon-plus-sign"></i></a>
        <a href="javascript:deleteRow('voteTable')" class="tip-top" data-original-title="删除"><i class="icon-minus-sign"></i></a>
        <table class="table table-striped with-check" id="voteTable">
         <tr>
            <th><i class="icon-list-alt"></i></th>
            <th>图片信息</th>
            <th>描述内容</th>
         </tr>
      <c:forEach items = "${votelist}" var = "voteitem" >
         <tr>
            <td><input type="checkbox" name="checkBox"/></td>
            <td>
                <input type="file" id="file-upload" name="file-upload" accept="image/*"/>
                <input id="imgPathID" value = " " type="hidden" name="ItemPicture"/>
                <img id="uploadedPic" src="${voteitem.itemImage}"/>
            </td>
            <td><input type="text" value="${voteitem.itemDesc}" name="ItemPicture"/></td>
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