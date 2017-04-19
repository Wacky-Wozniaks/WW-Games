$(document).ready(function() {
	$("td.board-cell").click(boardClick);
});

function boardClick(e) {
	if($(this).is("td.board-cell") &&  $(this).text().trim() === "") {
		lockBoard();
		$(this).find("h1").html("<i class='material-icons'>clear</i>");
	}
}

function boardClickLocked(e) {
	swal("It's not your turn.", "", "error");
}

function getBoardState() {
	
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

function playerMove() {
	var data = {};
	data["gameState"] = getBoardState();
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/tictactoe",
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