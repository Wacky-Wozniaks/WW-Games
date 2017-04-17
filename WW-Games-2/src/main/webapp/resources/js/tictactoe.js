$(document).ready(function() {
	$("td.board-cell").click(function(e) {
		console.log($(this));
		if($(this).text().trim() === "") {
			$(this).find("h1").html("<i class='material-icons'>clear</i>");
		}
	});
});

function getBoardState() {
	
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
		},
		error: function(e) {
			console.log("ERROR: ", e);
			alert("Error connecting to server.");
		},
		done: function(e) {
			console.log("DONE");
		}
	});
}