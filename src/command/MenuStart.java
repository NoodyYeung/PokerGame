package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import client.AIPlayer;
import client.LocalPlayer;
import gameController.GameController;
import gameController.InsufficientPlayerException;
import gameController.Player;

public class MenuStart extends Menu {
	private LinkedHashMap<String, Running> commands = new LinkedHashMap<>();

	public MenuStart() {
		List<Running> cmds = Arrays.asList(new MenuPlay());
		for (Running command : cmds) {
			commands.put(command.key().toUpperCase(), command);
		}
	}

	@Override
	public String description() {
		return "DouDiZhu --- Local Game";
	}

	@Override
	public String key() {
		return "p";
	}

	@Override
	public Menu execute(String commandKey) {
		// initialize game
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
	public void displayCommandList() {
		// TODO Auto-generated method stub

	}

}
