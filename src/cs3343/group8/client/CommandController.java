package cs3343.group8.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

//either quits or exceutes menu
public class CommandController {

	private static CommandController controller = null;
	private static LinkedHashMap<String, Command> globalCommands = new LinkedHashMap<>();

	private static boolean quit = false;

	// Menu history allows up to record the last menu we were navigating in, and when we choose 'back', the menu will let you go to the last menu
	private static ArrayList<Menu> menuHistory = new ArrayList<Menu>();

	// Constructor initialization: loads main menu and adds commands to 'global commands'
	private CommandController() {
		menuHistory.add(MenuMain.getInstance());
		
		List<Command> cmds = Arrays.asList(new CmdHelp(), new CmdBack());
		for (Command command : cmds) {
			globalCommands.put(command.key().toUpperCase(), command);
		}
	}

	// Displays menu
	public void display() {
		// Current menu: menu and options (menus that are printed out have a suffix of '+'
		Menu curMenu = menuHistory.get(menuHistory.size() - 1);
		System.out.println("Menu: " + curMenu.description());
		curMenu.displayCommandList();
		// Global commands
		for (Entry<String, Command> entry : globalCommands.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

	public void execute(String commandKey) {
		String key = commandKey.toUpperCase();
		
		// Global commands
		if (globalCommands.containsKey(key)) {
			globalCommands.get(key).execute();
			return;
		}

		Menu curMenu = menuHistory.get(menuHistory.size() - 1);
		Menu nextMenu = curMenu.execute(key);

		// nextMenu will be null if 'Running' is not a menu, so nextMenu must not be a command
		if (nextMenu != null) {
			menuHistory.add(nextMenu);
		}
	}

	public boolean shouldQuit() {
		return quit;
	}

	protected void quitAll(boolean quit) {
		CommandController.quit = true;
	}

	protected void quitOne(boolean quit) {
		// if 'back' from MenuMain, program will quit 
		if (menuHistory.size() == 1) {
			System.out.println("Exit");
			quitAll(true);
			return;
		}
		
		// remove last menu
		menuHistory.remove(menuHistory.size() - 1);
	}

	public static CommandController getInstance() {
		if (controller == null) controller = new CommandController();
		return controller;
	}
}
