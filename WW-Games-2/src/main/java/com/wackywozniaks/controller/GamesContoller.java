package com.wackywozniaks.controller;

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

import com.wackywozniaks.dao.impl.UserDAOImpl;
import com.wackywozniaks.dto.ConnectBean;
import com.wackywozniaks.dto.ConnectResponseBean;
import com.wackywozniaks.dto.WarBean;
import com.wackywozniaks.dto.WarResponseBean;
import com.wackywozniaks.entity.User;
import com.wackywozniaks.games.cards.War;
import com.wackywozniaks.games.connect.Connect4;
import com.wackywozniaks.games.connect.ConnectAI;
import com.wackywozniaks.games.connect.ConnectMove;
import com.wackywozniaks.games.connect.TicTacToe;

/**
 * Controller for the games.
 * 
 * @author Wacky Wozniaks Company
 * @version 05/01/2017
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
			ConnectMove move = ConnectAI.chooseMove(tictactoe, ConnectAI.MINIMAX);
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
			ConnectMove move = ConnectAI.chooseMove(getFour, ConnectAI.MINIMAX);
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
	public String checkers(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        return "checkers";
	}
	
	@RequestMapping(value = "checkers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WarResponseBean checkers(@RequestBody WarBean data, HttpServletRequest request) {
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
	
	private void addPoints(HttpServletRequest request, int winner) {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		System.out.println((String) request.getSession().getAttribute("currentSessionUser"));
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
