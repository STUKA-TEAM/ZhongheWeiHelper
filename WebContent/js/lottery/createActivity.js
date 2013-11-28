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
		if(activity.startDate == ''){
			activity.startDate = null;
		}
		if(activity.endDate == ''){
			activity.endDate = null;
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
	
	$("#save").click(function() {
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
		if(activity.startDate == ''){
			activity.startDate = null;
		}
		if(activity.endDate == ''){
			activity.endDate = null;
		}
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
});

function addRow(tableID) {	 
    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    element1.name="checkBox";
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    var element2 = document.createElement("input");
    element2.type = "text";
    cell2.appendChild(element2);

    var cell3 = row.insertCell(2);
    var element3 = document.createElement("input");
    element3.type = "text";
    cell3.appendChild(element3);
    
    var cell4 = row.insertCell(3);
    var element4 = document.createElement("input");
    element4.type = "text";
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(4);
    var element5 = document.createElement("input");
    element5.type = "text";
    cell5.appendChild(element5);
}

function deleteRow(tableID) {
    try {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;

    for(var i=0; i<rowCount; i++) {
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked) {
            table.deleteRow(i);
            rowCount--;
            i--;
        }
    }
    }catch(e) {
        alert(e);
    }
}