$(document).ready(function() {
	$("#draft").click(function() {
		var activity = {
			lotteryName : $('input[name="LotteryName"]').val(),
			lotterySummary : $('input[name="LotterySummary"]').val(),
			lotteryPicture : $('input[name="LotteryPicture"]').val(),
			startDate : $('input[name="StartDate"]').val(),
			endDate : $('input[name="EndDate"]').val(),
			chanceNum : parseInt($('input[name="ChanceNum"]').val() , 10),
		};
		if(activity.startDate == ''){
			activity.startDate = null;
		}
		if(activity.endDate == ''){
			activity.endDate = null;
		}
		alert(JSON.stringify(activity));
		var request = $.ajax({
			type : "POST",
			url : getRootPath() + '/lottery/store/draft/activity/create',
			contentType: 'application/json',
			data : JSON.stringify(activity), 
	        cache: false, 	
		});
		request.done(function(msg) {
			alert("Data Saved: " + msg);
		});
		request.fail(function(jqXHR, textStatus) {
			alert("Request failed: " + textStatus);
		});	
	});
});