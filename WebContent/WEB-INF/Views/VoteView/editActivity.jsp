<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="widget-box">
<div class="widget-title">
<span class="icon"><i class="icon-edit"></i></span>
<h5>编辑活动信息 </h5>
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
       <input type="file" id="topicPicture" name="file-upload" accept="image/*"/>
       <input id="topicPicture-path" value = " " type="hidden" name="LotteryPicture"/>
       <img id="topicPicture-img" src="${activity.votePicture}"/>
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
      <c:if test="${activity.isMultiChoice == 0}">
         <label><input type="radio" name="radios" value=0 checked/> 单选</label>
		 <label><input type="radio" name="radios" value=1 /> 多选</label>
      </c:if>
      <c:if test="${activity.isMultiChoice == 1}">
         <label><input type="radio" name="radios" value=0 /> 单选</label>
		 <label><input type="radio" name="radios" value=1 checked/> 多选</label>
      </c:if>       
		<div id="maxChoice" style="display:none;">
		    <span class="help-block">最大可选数: </span>
		    <input type="number" name="MaxChoice" min="1" max="10" value="${activity.maxChoice}">
		</div>
   </div>
</div>
<div class="control-group">
   <label class="control-label">选项设置</label>
   <div class="controls">
        <a href="javascript:addRow2('voteTable')" class="tip-top" data-original-title="添加"><i class="icon-plus-sign"></i></a>
        <a href="javascript:deleteRow('voteTable')" class="tip-top" data-original-title="删除"><i class="icon-minus-sign"></i></a>
        <table class="table table-striped table-bordered with-check" id="voteTable">
         <tr>
            <th><i class="icon-list-alt"></i></th>
            <th style="width:300px">图片信息</th>
            <th>描述内容</th>
         </tr>
      <c:forEach items = "${votelist}" var = "voteitem" >
         <tr>
            <td><input type="checkbox" name="checkBox"/></td>
            <td>
                <input type="file" id="${voteitem.itemId}-static" name="file-upload" accept="image/*"/>
                <input id="${voteitem.itemId}-static-path" value = " " type="hidden" name="ItemPicture"/>
                <img id="${voteitem.itemId}-static-img" src="${voteitem.itemImage}"/>
            </td>
            <td><input type="text" value="${voteitem.itemDesc}" name="ItemDesc"/></td>
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
<script type="text/javascript">
 /* radio控制区开始 */
 if($('input[name=radios]:checked').val() == '0'){  //initial check
    $('#maxChoice').hide();    	   
 }
  else{
	$('#maxChoice').show();
 }   	   
	
 $("input:radio[name='radios']").change(function()
 { 
    if(this.value == '1' && this.checked){
        $('#maxChoice').show();
    }else{
        $('#maxChoice').hide();
        $('input[name="MaxChoice"]').val(1);
    }
 });
 /* radio控制区结束 */
</script>
