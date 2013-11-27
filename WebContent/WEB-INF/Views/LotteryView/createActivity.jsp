<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<html>
<body>
<script src="../../../js/common/jquery-1.10.2.min.js"></script>
<script src="../../../js/lottery/createActivity.js"></script>

<div id = "createActivity">
<form method="post" action="tools/upload" enctype="multipart/form-data">
<div>主题图片
<input type="file" id="file-upload" name="LotteryPicture" accept="image/*"/>
<input type="submit"/>
</div>
</form>
<div>活动标题
<input type="text" name="LotteryName" />
</div>
<div>活动简介<br/>
<input type="text" name="LotterySummary" placeholder="不超过200字" maxlength="200"/>
</div>
<div>投票时间
<input type="datetime-local" name="StartDate" step="600"/> ~
<input type="datetime-local" name="EndDate" step="600"/> 
</div>
<div>最大抽奖次数
<input type="text" name="ChanceNum" />
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