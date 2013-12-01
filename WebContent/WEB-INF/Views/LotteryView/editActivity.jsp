<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<html>
<body>
<script src="../../../js/common/jquery-1.10.2.min.js"></script>
<script src="../../../js/lottery/createActivity.js"></script>
<script src="../../../js/common/tools.js"></script>

<div id = "reuseActivity">
<form method="post" action="tools/upload" enctype="multipart/form-data">
<div>主题图片
<input type="file" id="file-upload" name="LotteryPicture" accept="image/*"/>
<input type="submit"/>
</div>
</form>
<div>活动标题
<input type="text" name="LotteryName" value="${activity.lotteryName}" />
</div>
<div>活动简介<br/>
<input type="text" name="LotterySummary" placeholder="不超过200字" maxlength="200" value="${activity.lotterySummary}"/>
</div>
<div>投票时间
<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${activity.startDate}" var="startDate"/>
<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${activity.endDate}" var="endDate"/>
<input type="datetime-local" name="StartDate" step="600" value="${startDate}"/> ~
<input type="datetime-local" name="EndDate" step="600" value="${endDate}"/> 
</div>
<div>最大抽奖次数
<input type="text" name="ChanceNum" value="${activity.chanceNum}"/>
</div>
<div >奖项配置
<input type="button" value="Add Row" onclick="addRow('prizeTable')" />
<input type="button" value="Delete Row" onclick="deleteRow('prizeTable')" />
<table id="prizeTable">
<tr>
<th></th>
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
<div>
<button type="button" id="draft">保存为草稿</button>
<button type="button" id="save">保存为待发布</button>
<button type="button" id="release">立即发布</button>
<button type="button" id="cancel">取消</button>
</div>
</div>

</body>
</html>