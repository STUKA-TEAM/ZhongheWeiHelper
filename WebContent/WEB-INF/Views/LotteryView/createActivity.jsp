<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div id = "createActivity">
<form method="post" action="tools/upload" enctype="multipart/form-data">
<p>主题图片
<input type="file" name="file" accept="image/*"/>
<input type="submit"/>
</p>
</form>
<p>活动标题
<input type="text" id="LotteryName" />
</p>
<p>活动简介<br/>
<input type="text" id="LotterySummary" placeholder="不超过200字" maxlength="200"/>
</p>
<p>投票时间
<input type="date" id="StartDate" /> <input type="time" id="StartTime"> ~
<input type="date" id="EndDate" /> <input type="time" id="EndTime">
</p>
<p>最大抽奖次数
<input type="text" id="ChanceNum" />
</p>
<div>
<button type="button">保存为草稿</button>
<button type="button">保存为待发布</button>
<button type="button">立即发布</button>
<button type="button">取消</button>
</div>
</div>