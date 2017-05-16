var cardTagFront = "<img class=\"playing-card\" src=\"/resources/images/cards/";
var cardTagBack = ".svg\"></img>";

var player1Cards = null;
var player2Cards = null;
var deck = null;
var books = null;

function playerMove()
{
	var data = {};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/gofish",
		data: JSON.stringify(data),
		success: function(data)
		{
			
			if(data.gameOver)
			{
				swal("The Game is Over!", (data.winner === 0) ? "It's a tie!" : (data.winner === 1) ? "You won!" : "You lost!");
			}
		},
		error: function(e)
		{
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error");
		}
	});
}

function instructions()
{
	swal("Go Fish Instructions", "Ask your opponent for a card rank. If the computer has cards of that type, he will give them to you. Otherwise, you will have to go fishing (draw a card" +
			"from the deck). If you receive the card you wanted, you will get to ask again. Try to collect books (collections of all four cards of the same rank). Play continues until" +
			"there are no cards left. Collect the most books to win!")
}