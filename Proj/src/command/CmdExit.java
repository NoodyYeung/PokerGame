package command;



public class CmdExit extends Command {

	public void execute(){
		CommandController.getInstance().quitAll(true);
	}
	@Override
	public String key() {
		return "qq";
	}

	@Override
	public String description() {
		return "Exit";
	}

}
