package cs3343.group8.client;

import cs3343.group8.DDZ.Deck;
import cs3343.group8.table.Table;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

// This is the main menu selection for the game: this is only a menu selection!

public class MenuGame extends Menu {
	private static LinkedHashMap<String, Running> commands = new LinkedHashMap<>();
	private static MenuGame menu = null;

	public MenuGame() {
		// If you look closely, this actually is similar to MenuMain; other methods (execute, displayCommandList) here are similar to MenuMain too... anyway to decrease repetition?
		// Maybe someone who knows how to do it can try to do so
		List<Running> cmds = Arrays.asList(new Cmd2Play(), new CmdBack());
		for (Running command : cmds) {
			commands.put(command.key().toUpperCase(), command);
		}
	}

	// For now: just the bare bones. Add new functionality here
	public void execute() {

		Deck d = new Deck();
		Table t = new Table(d);
		
	}

	public void displayCommandList() {
		for (Entry<String, Running> entry : commands.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	@Override
	public String key() {
		return "2";
	}

	@Override
	public String description() {
		return "Begin local game";
	}

	@Override
	public Menu execute(String commandKey) {
		try {
			if (commandKey.length() == 0) {
				return null;
			}
			String key = commandKey.toUpperCase();
			if (commands.containsKey(key)) {
				Running cmdOrMenu = commands.get(key);
				// At node:
				if (cmdOrMenu instanceof Menu) {
					cmdOrMenu.execute();
					return (Menu) cmdOrMenu;
					// No more branches: at leaf
				} else if (cmdOrMenu instanceof Command) {
					((Command) cmdOrMenu).execute();
					return null;
				}
			} else {
				throw new Exception("Invalid Command [" + commandKey + "]!");
			}

			throw new Exception("cmdOrMenu not instanceof Menu nor instanceof Command: error [" + commandKey + "]!");
		} catch (Exception e) {
			// Same, maybe don't println in secretive places
			System.out.println(e.getMessage());
			return null;
		}
	}

}
