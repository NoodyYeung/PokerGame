package cs3343.group8.client;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandController {

    private LinkedHashMap<String, Command> commands = null;
    
    // quit one level? all levels?
    enum Quits{FALSE, ONE, ALL}
    private Quits quit = Quits.FALSE;

    // if you're inside a commmand tree (eg. a submenu), execute the command inside the command tree instead. if you're at the root menu, just execute the root
    private Command currentCommand = null;


    public CommandController() {
    	commands = new LinkedHashMap<>();
    }

    public CommandController registerCommand(Command command) {
        commands.put(command.key().toUpperCase(), command);
        return this;
    }

    public void displayCommandList() {
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void execute(String commandKey) {
        try {
            if (commandKey.length() == 0) {
                return;
            }
            String key = commandKey.toUpperCase();
            if (commands.containsKey(key)) {
                commands.get(key).execute();
            } else {
                throw new Exception("Invalid Command [" + commandKey + "]!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean shouldQuit() {
        return quit == Quits.ALL;
    }

    protected void quitAll(boolean quit) {
        this.quit = Quits.ALL;
    }
    
    protected void quitOne(boolean quit) {
      this.quit = Quits.ONE;
    }
}


