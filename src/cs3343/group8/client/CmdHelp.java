package cs3343.group8.client;

public class CmdHelp extends Command {

	@Override
	public String description() {
		return "Help";
	}

	@Override
	public String key() {
		// TODO Auto-generated method stub
		return "h";
	}

	@Override
	public void execute() {
		System.out.println("[CmdHelp execute()] Need help? ");
	}

}
