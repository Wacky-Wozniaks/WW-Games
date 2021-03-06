package com.wackywozniaks.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wackywozniaks.dao.UserDAO;
import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.dto.CheckersBean;
import com.wackywozniaks.dto.CheckersResponseBean;
import com.wackywozniaks.dto.ConnectBean;
import com.wackywozniaks.dto.ConnectResponseBean;
import com.wackywozniaks.dto.GoFishBean;
import com.wackywozniaks.dto.GoFishResponseBean;
import com.wackywozniaks.dto.WarBean;
import com.wackywozniaks.dto.WarResponseBean;
import com.wackywozniaks.entity.User;
import com.wackywozniaks.games.cards.Card;
import com.wackywozniaks.games.cards.GoFish;
import com.wackywozniaks.games.cards.GoFishAI;
import com.wackywozniaks.games.cards.GoFishMove;
import com.wackywozniaks.games.cards.War;
import com.wackywozniaks.games.checkers.Checkers;
import com.wackywozniaks.games.checkers.CheckersAI;
import com.wackywozniaks.games.checkers.CheckersMove;
import com.wackywozniaks.games.connect.Connect4;
import com.wackywozniaks.games.connect.ConnectAI;
import com.wackywozniaks.games.connect.ConnectMove;
import com.wackywozniaks.games.connect.TicTacToe;

/**
 * Controller for the games.
 * 
 * @author Wacky Wozniaks Company
 * @version 05/18/2017
 */
@Controller
@RequestMapping("/games")
public class GamesContoller {
	
	private UserDAOImpl userDAOImpl;
	private ApplicationContext context;
	
	@RequestMapping(value = "tictactoe", method = RequestMethod.GET)
	public String tictactoe(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("gameName", "Tic Tac Toe");
        return "tictactoe";
	}
	
	@RequestMapping(value = "tictactoe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ConnectResponseBean tictactoe(@RequestBody ConnectBean data, HttpServletRequest request) {
		TicTacToe tictactoe = new TicTacToe(data.getBoardState());
		
		ConnectResponseBean response = new ConnectResponseBean();
		if(tictactoe.gameOver()) {
			response.setWon(true);
			response.setWinner(tictactoe.getWinner());
			response.setHighlight(tictactoe.getHighlight());
			addPoints(request, tictactoe.getWinner());
		}
		else {
			ConnectMove move = ConnectAI.chooseMove(tictactoe, data.getDifficulty());
			tictactoe = tictactoe.doMove(move);
			response.setMove(move);
			if(tictactoe.gameOver()) {
				response.setWon(true);
				response.setWinner(tictactoe.getWinner());
				response.setHighlight(tictactoe.getHighlight());
				addPoints(request, tictactoe.getWinner());
			}
			else {
				response.setWon(false);
			}
		}
		
		return response;
	}
	
	@RequestMapping(value = "getfour", method = RequestMethod.GET)
	public String getfour(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("gameName", "GetFour");
        return "getfour";
	}
	
	@RequestMapping(value = "getfour", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ConnectResponseBean getfour(@RequestBody ConnectBean data, HttpServletRequest request) {
		Connect4 getFour = new Connect4(data.getBoardState());
		ConnectResponseBean response = new ConnectResponseBean();
		if(getFour.gameOver()) {
			response.setWon(true);
			response.setWinner(getFour.getWinner());
			response.setHighlight(getFour.getHighlight());
			addPoints(request, getFour.getWinner());
		}
		else {
			ConnectMove move = ConnectAI.chooseMove(getFour, data.getDifficulty());
			getFour = getFour.doMove(move);
			response.setMove(move);
			if(getFour.gameOver()) {
				response.setWon(true);
				response.setWinner(getFour.getWinner());
				response.setHighlight(getFour.getHighlight());
				addPoints(request, getFour.getWinner());
			}
			else {
				response.setWon(false);
			}
		}
		
		return response;
	}
	
	@RequestMapping(value = "war", method = RequestMethod.GET)
	public String war(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("gameName", "War");
        return "war";
	}
	
	@RequestMapping(value = "war", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WarResponseBean war(@RequestBody WarBean data, HttpServletRequest request) {
		War war = new War(data.getPlayer1(), data.getPlayer2());
		WarResponseBean response = new WarResponseBean();
		Object[] stuff = war.next();
		while(((Integer) stuff[2]).equals(War.DRAW)) {
			stuff = war.war(stuff);
		}
		if(war.gameOver()) {
			response.setGameOver(true);
			response.setWinner(war.getWinner());
		}
		response.setCounts(war.getCounts());
		response.setDraw(stuff);
		response.setPlayer1(war.getPlayer1());
		response.setPlayer2(war.getPlayer2());
		
		return response;
	}
	
	@RequestMapping(value = "checkers", method = RequestMethod.GET)
	public String checkers(Map<String, Object> model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		
		ObjectMapper mapper = new ObjectMapper();
		User user = userDAOImpl.getUser(currSessionUser);
		model.put("name", user.getFirstName() + " " + user.getLastName());
		model.put("gameName", "Checkers");
		
		try {
			model.put("legalMoves", mapper.writeValueAsString((new Checkers()).getMapOfMoves(Checkers.RED_PLAYER)));
		} catch (JsonProcessingException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.WARNING, e.getMessage(), e);
			e.printStackTrace();
		}
		
        return "checkers";
	}
	
	@RequestMapping(value = "checkers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CheckersResponseBean checkers(@RequestBody CheckersBean data, HttpServletRequest request) {
		Checkers checkers = new Checkers(data.getBoardState());
		
		CheckersResponseBean response = new CheckersResponseBean();
		if(checkers.gameOver()) {
			response.setWon(true);
			response.setWinner(checkers.getWinner());
			addPoints(request, checkers.getWinner());
		}
		else {
			CheckersMove move = CheckersAI.chooseMove(checkers, data.getDifficulty());
			checkers = checkers.doMove(move);
			response.setMove(move);
			if(checkers.gameOver()) {
				response.setWon(true);
				response.setWinner(checkers.getWinner());
				addPoints(request, checkers.getWinner());
			}
			else {
				response.setLegalMoves(checkers.getMapOfMoves(Checkers.RED_PLAYER));
				response.setWon(false);
			}
		}
		
		return response;
	}
	
	@RequestMapping(value = "gofish", method = RequestMethod.GET)
	public String gofish(Model model, HttpServletRequest request)
	{
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("gameName", "Go Fish");
        return "gofish";
	}
	
	@RequestMapping(value = "gofish", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public GoFishResponseBean gofish(@RequestBody GoFishBean data, HttpServletRequest request)
	{
		int type = data.getType();
		GoFish game;
		if(type == 0) game = new GoFish();
		else game = new GoFish(data.getDeck(), data.getHand1(), data.getHand2(), data.getBooks());
		
		GoFishResponseBean response = new GoFishResponseBean();
		response.setOldHand1(new ArrayList<Card>(game.getHand1()));
		response.setOldHand2(new ArrayList<Card>(game.getHand2()));
		if(type == 0 || type == 3)
		{
			game.wasEmpty(1);
			response.setPossible(game.getLegalActions(1));
			response.setStep(1);
		}
		else if(type == 1)
		{
			response.setStep(2);	
			String ask = data.getMove();
			GoFishMove move = new GoFishMove(0, ask, 0);
			boolean has = game.otherHas(move);
			if(has)
			{
				response.setGoFish(false);
				response.setGoAgain(true);
				game.transfer(1, move);
			}
			else
			{
				response.setGoFish(true);
				response.setGoAgain(game.goFish(1, move));
			}
			response.setTransfer(game.getTransfer());
			game.formBook(1);
		}
		else if(type == 2)
		{
			game.wasEmpty(2);
			GoFishMove m = GoFishAI.chooseMove(game);
			response.setRequested(m.getVal());
			boolean has = game.otherHas(m);
			if(has)
			{
				response.setGoFish(false);
				response.setGoAgain(true);
				game.transfer(2, m);
			}
			else
			{
				response.setGoFish(true);
				response.setGoAgain(game.goFish(2, m));
			}
			game.formBook(2);
		}
		
		if(game.gameOver())
		{
			response.setGameOver(true);
			response.setWinner(game.getWinner());
		}
		response.setDeck(game.getDeck());
		response.setHand1(game.getHand1());
		response.setHand2(game.getHand2());
		response.setBooks(game.getBooks());
		return response;
	}
	
	private void addPoints(HttpServletRequest request, int winner) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		if(winner == 1) {
			userDAOImpl.addPoints((String) request.getSession().getAttribute("currentSessionUser"), 5);
		}
		else if(winner == 0) {
			userDAOImpl.addPoints((String) request.getSession().getAttribute("currentSessionUser"), 3);
		}
		else {
			userDAOImpl.addPoints((String) request.getSession().getAttribute("currentSessionUser"), 1);
		}
	}
	
}
