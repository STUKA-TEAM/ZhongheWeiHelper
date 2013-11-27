<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<div id = "showActivityList">
<div id = "draftlist">
   <p>草稿</p>
   <ul>
      <c:forEach items = "${draftlist}" var = "activity" >
        <li><c:out value = "${activity.lotteryName}"/>
        </li>
      </c:forEach>
   </ul>
</div>
<div id = "savedlist">
   <p>待发布</p>
   <ul>
      <c:forEach items = "${savedlist}" var = "activity" >
        <li><c:out value = "${activity.lotteryName}"/></li>
      </c:forEach>
   </ul>
</div>
<div id = "releasedlist">
   <p>正在进行</p>
   <ul>
      <c:forEach items = "${releasedlist}" var = "activity" >
        <li><c:out value = "${activity.lotteryName}"/></li>
      </c:forEach>
   </ul>
</div>
<div id = "closedlist">
   <p>已结束</p>
   <ul>
      <c:forEach items = "${closedlist}" var = "activity" >
        <li><c:out value = "${activity.lotteryName}"/></li>
      </c:forEach>
   </ul>
</div>
</div>
