package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//either quits or exceutes menu
public class CommandController {

	private static CommandController controller = null;
	private boolean quit = false;
	public static CommandController getInstance() {
		if (controller == null) controller = new CommandController();
		return controller;
	}

	/**
	 * This function create a commandController without menu
	 * @return
	 */
	public static CommandController createSubCommandController(){
		CommandController commandController = new CommandController();
		commandController.quitOne(true);
		return commandController;
	}


	// Menu history allows up to record the last menu we were navigating in, and when we choose 'back', the menu will let you go to the last menu
	private ArrayList<Menu> menuHistory = new ArrayList<Menu>();

	/**
	 * Constructor initialization: loads main menu and adds commands to 'global commands'
	 */
	private CommandController() {
		menuHistory.add(MenuMain.getInstance());
		
		List<Command> cmds = Arrays.asList(new CmdHelp("The instruction manual is available in the game folder. More information is in readme.txt. Begin the game to start!"), new CmdBack());
	}


	// Displays menu
	public void display() {
		// Current menu: menu and options (menus that are printed out have a suffix of '+'
		Menu curMenu = menuHistory.get(menuHistory.size() - 1);
		System.out.println("Menu: " + curMenu.description());
		curMenu.displayCommandList();
		// Global commands

	}

	/**
	 * Execute the cmd on current menu
	 * @param commandKey
	 */
	public void execute(String commandKey) {
		executeCmdOnTopMenu(commandKey);
	}

	/**
	 * Push new menu to the top of the menuHistory
	 * @param menu
	 */
	public void pushNewMenu(Menu menu){
		if(menu != null){
			menuHistory.add(menu);
		}
	}

	/**
	 * Find to menu with last index in the menuHistory and executed the cmd by key
	 * @param key
	 */
	public void executeCmdOnTopMenu(String key){
		key = key.toUpperCase();
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
		this.quit = true;
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

	public void quitMenu(Menu menu) {
		menuHistory.remove(menu);
	}
}
