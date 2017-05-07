var highlight2;
var winner;

$(document).ready(function() {
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).is("td.board-cell")) {
		var nextOpenCell = getNextOpenCell($(this));
		
		if(nextOpenCell != -1) {
			lockBoard();
			$(nextOpenCell).addClass("filled");
			$(nextOpenCell).css("background-color", "red");
			var boardState = getBoardState();
			var name = $(this).attr("id");
			playerMove(name.charAt(name.length - 2), name.charAt(name.length - 1), boardState);
		}
	}
}

function getBoardState() {
	var board = [[], [], [], [], [], []];
	for(var row = 0; row < 6; row++) {
		for(var col = 0; col < 7; col++) {
			var cellName = "#cell-" + String(row) + String(col);
			var innerHtml = $(cellName).css("background-color");
			if(innerHtml === "rgb(255, 0, 0)") {
				board[row][col] = 1;
			}
			else if(innerHtml === "rgb(255, 255, 0)") {
				board[row][col] = 2;
			}
			else {
				board[row][col] = 0;
			}
		}
	}
	console.log(board);
	return board;
}

function getNextOpenCell(cell) {
	var cellName = cell.attr("id");
	var colNum = cellName.charAt(cellName.length - 1);
	var rowNum = cellName.charAt(cellName.length - 2);
	var emptyCell = -1;
	
	for(var i = 0; i < 6; i++) {
		var newCell = "#cell-" + String(i) + String(colNum);
		
		if(!$(newCell).hasClass("filled")) {
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

function highlight() {
	for(var i = 0; i < highlight2.length; i++) {
		var cell = $("#cell-" + highlight2[i][0] + highlight2[i][1]);
		if(cell.css("background-color") === "rgb(0, 128, 0)") {
			cell.css("background-color", (winner === 1) ? "red" : "yellow");
		}
		else {
			cell.css("background-color", "green");
		}
	}
}

function playerMove(row, col, boardState) {
	var data = {
			"boardState": boardState,
			"row": row,
			"col": col
	};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/getfour",
		data: JSON.stringify(data),
		success: function(data) {
			if(data.won) {
				if(data.move) {
					var cellName = "#cell-" + String(data.move.row) + String(data.move.col);
					$(cellName).addClass("filled");
					$(cellName).css("background-color", "yellow");
				}
				highlight2 = data.highlight;
				winner = data.winner;
				interval = setInterval(highlight, 1000);
				swal("The Game is Over!", (data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!");
				$("#msg").text("Game over! " + ((data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!"));
			}
			else {
				var cellName = "#cell-" + String(data.move.row) + String(data.move.col);
				$(cellName).addClass("filled");
				$(cellName).css("background-color", "yellow");
				unlockBoard();
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error");
		}
	});
}

function instructions() {
	swal("GetFour Instructions", "Click on an empty space. Get four red pieces in a row to win!");
}