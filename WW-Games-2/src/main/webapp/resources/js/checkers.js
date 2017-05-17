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
var difficulty;

$(document).ready(function() {
	swal({
		title: 'Select Difficulty',
		input: 'radio',
		inputOptions : {
			'0': 'Easy',
			'1': 'Medium',
			'2': 'Hard'
		},
		inputValidator : function(result) {
			return new Promise(function(resolve, reject) {
				if (result) {
					resolve();
				} else {
					reject('Please Select a Difficulty');
				}
			});
		}
	}).then(function(result) {
		if (result) {
			difficulty = result;
		}
	})
	$("img.game-piece").click(pieceClick);
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).has("img").length > 0) {
		pieceClick(e, $(this).children("img"));
	}
	else if($("img.selected-piece").length > 0 && $(this).hasClass("board-cell-highlight")) {
		var thisId = $(this).attr("id");
		var thisRow = thisId.charAt(thisId.length - 2);
		var thisCol = thisId.charAt(thisId.length - 1);
		var possibleMoves = legalMoves[$("img.selected-piece").parent().attr("id")];
		for(var i = 0; i < possibleMoves.length; i++) {
			if(possibleMoves[i].row == thisRow && possibleMoves[i].col == thisCol) {
				movePiece(possibleMoves[i], true);
				break;
			}
		}
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
		if(piece.hasClass("king")) {
			piece.attr("src", SELECTED_RED_KING_PIECE);
		}
		else {
			piece.attr("src", SELECTED_PIECE);
		}
		piece.addClass("selected-piece");
		var possibleMoves = legalMoves[piece.parent().attr("id")];
		for(var i = 0; i < possibleMoves.length; i++) {
			$("#cell-" + possibleMoves[i].row + possibleMoves[i].col).addClass("board-cell-highlight");
		}
	}
	else if(piece.is("img.selected-piece")) {
		if(piece.hasClass("king")) {
			piece.attr("src", RED_KING_PIECE);
		}
		else {
			piece.attr("src", RED_PIECE);
		}
		piece.removeClass("selected-piece");
		var possibleMoves = legalMoves[piece.parent().attr("id")];
		for(var i = 0; i < possibleMoves.length; i++) {
			$("#cell-" + possibleMoves[i].row + possibleMoves[i].col).removeClass("board-cell-highlight");
		}
	}
}

async function movePiece(move, isRed) {
	var moveStack = [];
	while(move != null) {
		moveStack.push(move);
		move = move.lastMove;
	}
	while(moveStack.length > 0) {
		var currMove = moveStack.pop();
		if(currMove.player === WHITE_PLAYER) {
			await sleep(300);
		}
		if(currMove.jump) {
			if(isRed) {
				if(currMove.king) {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"red-piece king\" src=\"/resources/images/checkers/redking.svg\">");
				}
				else {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"red-piece\" src=\"/resources/images/checkers/red.svg\">");
				}
			}
			else {
				if(currMove.king) {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"white-piece king\" src=\"/resources/images/checkers/whiteking.svg\">");
				}
				else {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"white-piece\" src=\"/resources/images/checkers/white.svg\">");
				}
			}
			var midCellRow = (currMove.originalRow + currMove.row) / 2;
			var midCellCol = (currMove.originalCol + currMove.col) / 2;
			$("#cell-" + midCellRow + midCellCol).find("img").remove();
			$("#cell-" + currMove.originalRow + currMove.originalCol).find("img").remove();
		}
		else {
			if(isRed) {
				if(currMove.king) {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"red-piece king\" src=\"/resources/images/checkers/redking.svg\">");
				}
				else {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"red-piece\" src=\"/resources/images/checkers/red.svg\">");
				}
			}
			else {
				if(currMove.king) {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"white-piece king\" src=\"/resources/images/checkers/whiteking.svg\">");
				}
				else {
					$("#cell-" + currMove.row + currMove.col).append("<img class=\"white-piece\" src=\"/resources/images/checkers/white.svg\">");
				}
			}
			$("#cell-" + currMove.originalRow + currMove.originalCol).find("img").remove();
		}
	}
}

function sleep(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
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
			"boardState": boardState,
			"difficulty": difficulty
	};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/checkers",
		data: JSON.stringify(data),
		success: function(data) {
			if(data.won) {
				swal("The Game is Over!", (data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!");
				$("#msg").text("Game over! " + ((data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!"));
			}
			else {
				movePiece(data.move, false);
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
