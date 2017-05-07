$(document).ready(function() {
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).is("td.board-cell") &&  $(this).text().trim() === "") {
		lockBoard();
		$(this).find("h1").html("<i class='material-icons'>clear</i>");
		var boardState = getBoardState();
		var name = $(this).children("h1").attr("id");
		playerMove(name.charAt(name.length - 2), name.charAt(name.length - 1), boardState);
	}
}

function boardClickLocked(e) {
	swal("It's not your turn.", "", "error");
}

function getBoardState() {
	var board = [[], [], []];
	for(var row = 0; row < 3; row++) {
		for(var col = 0; col < 3; col++) {
			var cellName = "cell-" + String(row) + String(col);
			var innerHtml = $("#" + cellName).html();
			if(innerHtml === "<i class=\"material-icons\">clear</i>") {
				board[row][col] = 1;
			}
			else if(innerHtml === "<i class=\"material-icons\">radio_button_unchecked</i>") {
				board[row][col] = 2;
			}
			else {
				board[row][col] = 0;
			}
		}
	}
	return board;
}

function lockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClickLocked);
	$("#msg").text("It's not your turn. Waiting for the other player... (You are X)");
}

function unlockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClick);
	$("#msg").text("It's your turn! Click on a square. (You are X)");
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
		url: "/games/tictactoe",
		data: JSON.stringify(data),
		success: function(data) {
			if(data.won) {
				if(data.move) {
					var cellName = "#cell-" + String(data.move.row) + String(data.move.col);
					$(cellName).html("<i class=\"material-icons\">radio_button_unchecked</i>");
				}
				for(var i = 0; i < data.highlight.length; i++) {
					$("#cell-" + data.highlight[i][0] + data.highlight[i][1]).css("color", "red");
				}
				swal("The Game is Over!", (data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!");
				$("#msg").text("Game over! " + ((data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!"));
			}
			else {
				var cellName = "#cell-" + String(data.move.row) + String(data.move.col);
				$(cellName).html("<i class=\"material-icons\">radio_button_unchecked</i>");
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
	swal("Tic Tac Toe Instructions", "Click on an empty square. Get three X in a row to win!");
}