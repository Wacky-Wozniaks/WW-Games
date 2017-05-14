var SELECTED_PIECE = "/resources/images/checkers/selected.svg";
var SELECTED_RED_KING_PIECE = "/resources/images/checkers/redkingselected.svg";
var RED_PIECE = "/resources/images/checkers/red.svg";
var RED_KING_PIECE = "/resources/images/checkers/redking.svg";
var WHITE_PIECE = "/resources/images/checkers/white.svg";
var WHITE_KING_PIECE = "/resources/images/checkers/whiteking.svg";
var EMPTY = 0;
var RED = 1;
var RED_KING = 2;
var WHITE = 3;
var WHITE_KING = 4;
var RED_PLAYER = 1;
var WHITE_PLAYER = 2;
var legalMoves;

$(document).ready(function() {
	$("img.game-piece").click(pieceClick);
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).has("img").length > 0) {
		pieceClick(e, $(this).children("img"));
	}
	else if($("img.selected-piece").length > 0 && $(this).hasClass("board-cell-highlight")) {
		$(this).append("<img class=\"red-piece\" src=\"/resources/images/checkers/red.svg\">");
		$("img.selected-piece").remove();
		$(".board-cell-highlight").removeClass("board-cell-highlight");
		lockBoard();
		playerMove(getBoardState());
	}
}

function boardClickLocked(e) {
	swal("It's not your turn.", "", "error");
}

function pieceClick(e, piece) {
	if($("img.selected-piece").length <= 0 && piece.is("img.red-piece")) {
		piece.attr("src", SELECTED_PIECE);
		piece.removeClass("red-piece");
		piece.addClass("selected-piece");
		var possibleMoves = legalMoves[piece.parent().attr("id")];
		for(var i = 0; i < possibleMoves.length; i++) {
			$("#cell-" + possibleMoves[i].row + possibleMoves[i].col).addClass("board-cell-highlight");
		}
	}
	else if(piece.is("img.selected-piece")) {
		piece.attr("src", RED_PIECE);
		piece.removeClass("selected-piece");
		piece.addClass("red-piece");
		var possibleMoves = legalMoves[piece.parent().attr("id")];
		for(var i = 0; i < possibleMoves.length; i++) {
			$("#cell-" + possibleMoves[i].row + possibleMoves[i].col).removeClass("board-cell-highlight");
		}
	}
}

function lockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClickLocked);
	$("#msg").text("It's not your turn. Waiting for the other player... (You are red)");
}

function unlockBoard() {
	$("td.board-cell").off('click');
	$("td.board-cell").click(boardClick);
	$("#msg").text("It's your turn! Click on a piece then click where you want to move it. (You are red)");
}

function getBoardState() {
	var board = [[], [], [], [], [], [], [], []];
	for(var row = 0; row < 8; row++) {
		for(var col = 0; col < 8; col++) {
			var cellName = "cell-" + String(row) + String(col);
			var cell = $("#" + cellName);
			if(cell.has("img").length <= 0) {
				board[row][col] = EMPTY;
			}
			else if(cell.find("img").hasClass("red-piece")) {
				if(cell.find("img").hasClass("king")) {
					board[row][col] = RED_KING;
				}
				else {
					board[row][col] = RED;
				}
			}
			else if(cell.find("img").hasClass("white-piece")) {
				if(cell.find("img").hasClass("king")) {
					board[row][col] = WHITE_KING;
				}
				else {
					board[row][col] = WHITE;
				}
			}
		}
	}
	return board;
}

function playerMove(boardState) {
	var data = {
			"boardState": boardState
	};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/checkers",
		data: JSON.stringify(data),
		success: function(data) {
			/*if(data.won) {
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
			}*/
			if(data.won) {
				
			}
			else {
				$("#cell-" + data.move.row + data.move.col).append("<img class=\"red-piece\" src=\"/resources/images/checkers/white.svg\">");
				$("#cell-" + data.move.originalRow + data.move.originalCol).find("img").remove();
				legalMoves = data.legalMoves;
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
	swal("Checkers Instructions", "Click on a game piece and then click on an empty square to move the piece. Eliminate opponent pieces by hopping over them. Remove all opponent pieces to win!");
}
