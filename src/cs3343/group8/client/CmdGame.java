package cs3343.group8.client;

import java.util.LinkedHashMap;
import java.util.Map;

import cs3343.group8.DDZ.Deck;
import cs3343.group8.table.Table;

public class CmdGame extends Command {
  private static LinkedHashMap<String, Command> commands = new LinkedHashMap<>();

  public CmdGame(){
  	registerCommand(new Cmd2Play());
  }
  
	public void execute() {
		
		
    Deck d = new Deck();
    Table t = new Table(d);
    displayCommandList();
	}
	
  private void registerCommand(Command command) {
    commands.put(command.key().toUpperCase(), command);

}

  public void displayCommandList() {
    for (Map.Entry<String, Command> entry : commands.entrySet()) {
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

}
