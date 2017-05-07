var cardTagFront = "<img class=\"playing-card\" src=\"/resources/images/cards/";
var cardTagBack = ".svg\"></img>";

var player1Cards = null;
var player2Cards = null;

$(window).load(function() {
	$("#loading-screen").addClass("hidden");
	$("#game").removeClass("hidden");
});

$(document).ready(function() {
	$("#draw").click(function() {
		playerMove();
	});
});

function setCards(cards, isPlayer1) {
	if(isPlayer1) {
		var newHtml = "";
		for(var i = cards.length - 1; i >= 0; i--) {
			newHtml += cardTagFront + cards[i].suitText + cards[i].rankText + cardTagBack;
		}
		$("#player1Cards").html(newHtml);
	}
	else {
		var newHtml = "";
		for(var i = 0; i < cards.length; i++) {
			newHtml += cardTagFront + cards[i].suitText + cards[i].rankText + cardTagBack;
		}
		$("#player2Cards").html(newHtml);
	}
}

function playerMove() {
	var data = {
		"player1": player1Cards,
		"player2": player2Cards
	};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/war",
		data: JSON.stringify(data),
		success: function(data) {
			$("#player1Count").text(data.counts[0]);
			$("#player2Count").text(data.counts[1]);
			player1Cards = data.player1;
			player2Cards = data.player2;
			setCards(data.draw[0], true);
			setCards(data.draw[1], false);
			if(data.gameOver) {
				swal("The Game is Over!", (data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!");
				$("#draw").attr("disabled", true);
			}
		},
		error: function(e) {
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error");
		}
	});
}

function instructions() {
	swal("War Instructions", "Click on draw. You and the computer will draw a card and whoever has the larger card wins. If the cards are equal, a war will occur. Collect all the cards to win!");
}