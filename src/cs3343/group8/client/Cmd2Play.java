package cs3343.group8.client;

public class Cmd2Play extends Command{

	public void execute(){
		System.out.println("[Cmd2Play execute()] Play play play play!");
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
