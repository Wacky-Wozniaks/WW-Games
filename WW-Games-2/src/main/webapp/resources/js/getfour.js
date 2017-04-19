$(document).ready(function() {
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).is("td.board-cell")) {
		var nextOpenCell = getNextOpenCell($(this));
		
		if(nextOpenCell != -1) {
			lockBoard();
			$("#" + nextOpenCell).addClass("filled");
			$("#" + nextOpenCell).css("background-color", "red");
		}
		
		$(this).find("h1").html("<i class='material-icons'>clear</i>");
	}
}

function getNextOpenCell(cell) {
	var cellName = cell.attr("id");
	var rowNum = cellName.charAt(cellName.length - 1);
	var colNum = cellName.charAt(cellName.length - 2);
	var emptyCell = -1;
	
	for(var i = 0; i < 6; i++) {
		var newCell = "cell" + String(colNum) + String(i);
		
		if(!$("#" + newCell).hasClass("filled")) {
			emptyCell = newCell;
		}
		else {
			break;
		}
	}
	
	return emptyCell;
}

function boardClickLocked(e) {
	swal("It's not your turn.", "", "error");
}

function getBoardState() {
	
}

function lockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClickLocked);
	$("#msg").text("It's not your turn. Waiting for the other player... (You are red)");
}

function unlockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClick);
	$("#msg").text("It's your turn! Click on a space. (You are red)");
}

function playerMove() {
	var data = {};
	data["gameState"] = getBoardState();
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/getfour",
		data: JSON.stringify(data),
		dataType: 'json',
		success: function(data) {
			setBoardState(data["gameState"]);
			unlockBoard();
		},
		error: function(e) {
			console.log("ERROR: ", e);
			alert("Error connecting to server. Please refresh the page.");
		},
		done: function(e) {
			console.log("DONE");
		}
	});
}