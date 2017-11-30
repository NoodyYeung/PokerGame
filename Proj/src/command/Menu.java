package command;

public abstract class Menu implements Running{
	// protected LinkedHashMap<String, Running> commands = new LinkedHashMap<>();

	public void execute(){
		
	}
	
	// When a menu 'executes' a command, it will call execute(), then enter the next menu
	public abstract Menu execute(String commandKey);

	public abstract void displayCommandList();

	public void quit(){
		CommandController.getInstance().quitMenu(this);
	}

  	public String toString() {
    return key() + ": " + description() + "+";
}
}