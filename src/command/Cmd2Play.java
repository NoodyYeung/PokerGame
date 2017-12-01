package command;

import client.AIPlayer;
import client.LocalPlayer;
import gameController.GameController;
import gameController.InsufficientPlayerException;
import gameController.Player;

import java.util.ArrayList;

public class Cmd2Play extends Command{

	public void execute(){

		ArrayList<Player> players = new ArrayList<>();
		players.add(new LocalPlayer());
		players.add(new AIPlayer());
		players.add(new AIPlayer());
		try {
			GameController gameController = new GameController(players);
			gameController.startGame();

		}  catch (InsufficientPlayerException e) {
//			e.printStackTrace();
		}
	}
	
	@Override
	public String key() {
		return "p";
	}

	@Override
	public String description() {
		return "Play some cards";
	}

}
