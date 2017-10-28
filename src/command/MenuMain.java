package command;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

//menus are all singletons
// Also see MenuGame, some parts of some functions repeat a bit...
public class MenuMain extends Menu {

	// Singleton, and a map of commands called 'commands'
	private static MenuMain menu = null;
	private static LinkedHashMap<String, Running> commands = new LinkedHashMap<>();

	private MenuMain() {
		// Initialize the menu with items
		List<Running> cmds = Arrays.asList(new CmdConnect(), new MenuGame(), new CmdHelp(), new CmdBack());
		for (Running command : cmds) {
			commands.put(command.key().toUpperCase(), command);
		}
	}

    /**
     * This function will run on CommandController and display the menu
     */
	@Override
	public void displayCommandList() {
		for (Entry<String, Running> entry : commands.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

    /**
     * The mainMenu execution will return menu if the key of the menu selected. The the selected menu will be added to CommandController.menuHistory.
     * If Cmd is selected, the cmd will be executed
     * @param commandKey
     * @return Running? Return null or the selected cmd that is a menu
     */
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
				cmdOrMenu.execute();
				if (cmdOrMenu instanceof Menu) {
					return ((Menu) cmdOrMenu);
				} else {
					return null;
				}
			} else {
				throw new Exception("Invalid Command [" + commandKey + "]!");
			}

		} catch (Exception e) {
			// I am somewhat averse to allowing any random class use
			// System.out.println... change this later maybe
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static Menu getInstance() {
		if (menu == null)
			menu = new MenuMain();
		return menu;
	}

	@Override
	public String description() {
		return "Main menu auto loaded";
	}

	@Override
	public String key() {
		return null;
	}

}
