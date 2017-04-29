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
import com.wackywozniaks.entity.User;
import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.connect.ConnectAI;
import com.wackywozniaks.games.connect.ConnectMove;
import com.wackywozniaks.games.connect.TicTacToe;

/**
 * 
 * @author Wacky Wozniaks Company
 * @version 04/08/2017
 */
@Controller
@RequestMapping("/games")
public class GamesContoller {
	
	private UserDAOImpl userDAOImpl;
	private ApplicationContext context;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String init(Model model, HttpServletRequest request) {
		String currSessionUser = (String) request.getSession().getAttribute("currentSessionUser");
		if(currSessionUser == null) {
			return "redirect:/login";
		}
		context = new ClassPathXmlApplicationContext("Beans.xml");
		userDAOImpl = (UserDAOImpl) context.getBean("userDAOImpl");
		User user = userDAOImpl.getUser(currSessionUser);
		model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        return "games";
	}
	
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
	public ConnectResponseBean tictactoe(@RequestBody ConnectBean data) {
		TicTacToe tictactoe = new TicTacToe(data.getBoardState());
		//ConnectMove move = ConnectAI.chooseMove(tictactoe);
		//tictactoe.doMove(move);
		
		ConnectResponseBean response = new ConnectResponseBean();
		if(tictactoe.gameOver()) {
			response.setWon(true);
			response.setWinner(tictactoe.getWinner());
			response.setHighlight(tictactoe.getHighlight());
		}
		else {
			ConnectMove move = ConnectAI.chooseMove(tictactoe);
			tictactoe = tictactoe.doMove(move);
			response.setMove(move);
			if(tictactoe.gameOver()) {
				response.setWon(true);
				response.setWinner(tictactoe.getWinner());
				response.setHighlight(tictactoe.getHighlight());
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
	
	/*@RequestMapping(value = "getfour", method = RequestMethod.POST) 
	public String getfour(Model model, TicTacToeBean bean) {
		model.addAttribute("gameState", Connect4.doMove(bean.getGameState(), true)); //gamestate, isAI
	}*/

}
