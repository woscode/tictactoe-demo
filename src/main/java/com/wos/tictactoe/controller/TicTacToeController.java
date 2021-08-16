package com.wos.tictactoe.controller;

import com.wos.tictactoe.model.Mark;
import com.wos.tictactoe.model.Game;
import com.wos.tictactoe.model.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("game")
@RequestMapping("/")
public class TicTacToeController {
	
	static final Logger logger = LoggerFactory.getLogger(TicTacToeController.class);

	@GetMapping("/")
	public String tictactoe(@ModelAttribute("game") Game game) {
		return "tictactoe";
	}
	
	@PostMapping("/")
	public String tictactoeMove(
			@ModelAttribute("game") Game game,
			@RequestParam("square_id") String squareId, 
			@RequestParam(value="restart", required=false, defaultValue="false") boolean restart,
			@RequestParam(value = "player_move_first", required = false) boolean playerMoveFirst) {

		if (restart) {
			logger.info("Is player move first? : {}", playerMoveFirst);
			game.restart(playerMoveFirst);
			if (!game.isPlayerMoveFirst())
				game.move(4);
		} else {
			logger.info("Player move on {}", squareId);
			game.move(Integer.valueOf(squareId));
			game.randomMove();
		}
		return "tictactoe";
	}

	public String goodbye(SessionStatus status) {
	    status.setComplete();
	    return "tictactoe";
	 }

	
	@ModelAttribute("game")
	private Game tictactoeGame() {
		return new Game(
				new Player(0L, "Player", Mark.X),
				new Player(1L, "AI", Mark.O)
			);
	}
}
