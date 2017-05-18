var cardTagFront = "<img class=\"playing-card\" src=\"/resources/images/cards/";
var cardTagBack = ".svg\"></img>";

var player1Cards = null;
var player2Cards = null;
var deck = null;
var books = null;
var goAgain = false
var gameOver = false
var winner = 0

$(window).load(function()
{
	$("#loading-screen").addClass("hidden");
	$("#game").removeClass("hidden");
});

$(document).ready(function()
{
	$("#draw").click(function()
	{
		createGame();
	});
}

function deckClick(e)
{
	setGame1()
}

function cardsClick(e)
{
	setGame2()
}

function fishClick(e)
{
	setGame2()
}

function setGame1()
{
	setPlayer1Cards(cards)
	setPlayer2Cards(cards)
	setBooks()
	$("#msg").text("");
	if(gameOver) gameOver()
	if(goAgain) requestMoves()
	else computerMove()
}

function setGame2()
{
	setPlayer1Cards(cards)
	setPlayer2Cards(cards)
	setBooks()
	$("#msg").text("");
	if(gameOver) gameOver()
	if(goAgain) computerMove()
	else requestMoves()
}

function gameOver()
{
	swal("The Game is Over!", (winner === 0) ? "It's a tie!" : (winner === 1) ? "You won!" : "You lost!");
	$("#msg").text("Game over! " + ((winner === 0) ? "It's a tie!" : (winner === 1) ? "You won!" : "You lost!"));
}

function setPlayer1Cards(cards)
{
	var newHtml = "";
	for(var i = 0; i < cards.length; i++)
	{
		newHtml += cardTagFront + cards[i].suitText + cards[i].rankText + cardTagBack;
	}
	$("#player1").html(newHtml);
}

function setPlayer2Cards(cards)
{
	var newHtml = "";
	for(var i = 0; i < cards.length; i++)
	{
		newHtml += cardTagFront + "Back" + cardTagBack;
	}
	$("#player2").html(newHtml);
}

function setBooks()
{
	$('#books1').html("Books: " + books[0])
	$('#books2').html("Books: " + books[1])
}

function chooseMove(moves)
{
	var inputOptions = new Object()
	for(var i = 0, len = moves.length; i < len; i++)
	{
		inputOptions[moves[i].rankText] = moves[i].rankText
	}
	
	swal({
		title: "What do you want to ask for?",
		input: radio,
		inputOptions: inputOptions,
		inputValidator: function(result)
		{
			return new Promise(function(resolve, reject)
			{
				if(result)
				{
					resolve();
				}
				else
				{
					reject('Please Select a Value');
				}
			});
		}
	}).then(function(result)
	{
		makeMove(result)
	})
}

function createGame()
{
	var data = {
		"step": 0
	};
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/gofish",
		data: JSON.stringify(data),
		success: function(data)
		{
			chooseMove(data.possible)
		},
		error: function(e)
		{
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error");
		}
	});
}

function requestMoves()
{
	var data = {
		"player1": player1Cards,
		"player2": player2Cards,
		"deck": deck,
		"books": book,
		"step": 3
	};
	
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/games/gofish",
		data: JSON.stringify(data),
		success: function(data)
		{
			chooseMove(data.possible)
		},
		error: function(e)
		{
			console.log("ERROR: ", e);
			swal("Error connecting to server.", "Please refresh the page.", "error");
		}
	});
}

function makeMove(move)
{
	var data = {
			"player1": player1Cards,
			"player2": player2Cards,
			"deck": deck,
			"books": book,
			"step": 1,
			"move": move
		};
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/games/gofish",
			data: JSON.stringify(data),
			success: function(data)
			{
				player1Cards = data.hand1
				player2Cards = data.hand2
				deck = data.deck
				books = data.books
				if(data.goFish)
				{
					unlockDeck()
				}
				else
				{
					setGame1
				}
				goAgain = data.goAgain
				if(data.gameOver)
				{
					gameOver = true
					winner = data.winner
				}
			},
			error: function(e)
			{
				console.log("ERROR: ", e);
				swal("Error connecting to server.", "Please refresh the page.", "error");
			}
		});
}

function computerMove()
{
	var data = {
			"player1": player1Cards,
			"player2": player2Cards,
			"deck": deck,
			"books": book,
			"step": 2
		};
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/games/gofish",
			data: JSON.stringify(data),
			success: function(data)
			{
				player1Cards = data.hand1
				player2Cards = data.hand2
				deck = data.deck
				books = data.books
				
				if(data.goFish)
				{
					unlockFish()
				}
				else
				{
					unlockCards()
				}
				
				goAgain = data.goAgain
				if(data.gameOver)
				{
					gameOver = true
					winner = data.winner
				}
			},
			error: function(e)
			{
				console.log("ERROR: ", e);
				swal("Error connecting to server.", "Please refresh the page.", "error");
			}
		});
}

function deckClickLocked(e)
{
	swal("You can't draw now", "", "error");
}

function lockDeck()
{
	$("deck").off('click');
	$("#deck").click(deckClickLocked);
}

function unlockDeck()
{
	$("#deck").off('click');
	$("#deck").click(deckClick);
	$("#msg").text("Click on the deck to go fish!");
}

function cardsClickLocked(e)
{
	swal("You can't use your cards now", "", "error");
}

function lockCards()
{
	$("#player1").off('click');
	$("#player1").click(cardsClickLocked);
}

function unlockCards()
{
	$("#player1").off('click');
	$("#player1").click(cardsClick);
	$("#msg").text("Click on your cards to give to the computer.");
}

function fishClickLocked(e)
{
	swal("You can't say go fish now", "", "error");
}

function lockFish()
{
	$("#gofish").off('click');
	$("#gofish").click(fishClickLocked);
}

function unlockFish()
{
	$("#goFish").off('click');
	$("#gofish").click(fishClick);
	$("#msg").text("Click on the button to tell the computer to go fish!");
}

function instructions()
{
	swal("Go Fish Instructions", "Ask your opponent for a card rank. If the computer has cards of that type, he will give them to you. Otherwise, you will have to go fishing (draw a card" +
			"from the deck). If you receive the card you wanted, you will get to ask again. Try to collect books (collections of all four cards of the same rank). Play continues until" +
			"there are no cards left. Collect the most books to win!")
}