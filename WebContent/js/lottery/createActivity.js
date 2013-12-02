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
		var prizes = [];
		var prize = document.getElementById('prizeTable');
		for(var i = 1; i < prize.rows.length; i++){
			var prizeObj = {
				prizeName : prize.rows[i].cells[1].childNodes[0].value,
				prizeContent : prize.rows[i].cells[2].childNodes[0].value,
				luckyNum : parseInt(prize.rows[i].cells[3].childNodes[0].value , 10),
				luckyPercent : parseFloat(prize.rows[i].cells[4].childNodes[0].value , 10)
			};
			prizes[i-1] = prizeObj;
		}
		if(prize.rows.length > 1){
			activity.prizeList = prizes;
		}
		//turn '' to null
		if(activity.startDate == ''){
			activity.startDate = null;
		}
		if(activity.endDate == ''){
			activity.endDate = null;
		}
		if(activity.lotteryName == ''){
			activity.lotteryName = null;
		}
		if(activity.lotterySummary == ''){
			activity.lotterySummary = null;
		}
		if(activity.lotteryPicture == ''){
			activity.lotteryPicture = null;
		}
		
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
	
	$("#save").click(function() {  //lack of validation
		var activity = {
			lotteryName : $('input[name="LotteryName"]').val(),
			lotterySummary : $('input[name="LotterySummary"]').val(),
			lotteryPicture : $('input[name="LotteryPicture"]').val(),
			startDate : $('input[name="StartDate"]').val(),
			endDate : $('input[name="EndDate"]').val(),
			chanceNum : parseInt($('input[name="ChanceNum"]').val() , 10),
		};	
		var prizes = [];
		var prize = document.getElementById('prizeTable');
		for(var i = 1; i < prize.rows.length; i++){
			var prizeObj = {
				prizeName : prize.rows[i].cells[1].childNodes[0].value,
				prizeContent : prize.rows[i].cells[2].childNodes[0].value,
				luckyNum : parseInt(prize.rows[i].cells[3].childNodes[0].value , 10),
				luckyPercent : parseFloat(prize.rows[i].cells[4].childNodes[0].value , 10)
			};
			prizes[i-1] = prizeObj;
		}
	    activity.prizeList = prizes;
	    
		var request = $.ajax({
			type : "POST",
			url : getRootPath() + '/lottery/store/save/activity/create',
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
	
	$("#release").click(function() {  //lack of validation
		var activity = {
			lotteryName : $('input[name="LotteryName"]').val(),
			lotterySummary : $('input[name="LotterySummary"]').val(),
			lotteryPicture : $('input[name="LotteryPicture"]').val(),
			startDate : $('input[name="StartDate"]').val(),
			endDate : $('input[name="EndDate"]').val(),
			chanceNum : parseInt($('input[name="ChanceNum"]').val() , 10),
		};	
		var prizes = [];
		var prize = document.getElementById('prizeTable');
		for(var i = 1; i < prize.rows.length; i++){
			var prizeObj = {
				prizeName : prize.rows[i].cells[1].childNodes[0].value,
				prizeContent : prize.rows[i].cells[2].childNodes[0].value,
				luckyNum : parseInt(prize.rows[i].cells[3].childNodes[0].value , 10),
				luckyPercent : parseFloat(prize.rows[i].cells[4].childNodes[0].value , 10)
			};
			prizes[i-1] = prizeObj;
		}
	    activity.prizeList = prizes;
	    
		var request = $.ajax({
			type : "POST",
			url : getRootPath() + '/lottery/store/release/activity/create',
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