package cs3343.group8.client;

import java.util.HashMap;
import java.util.Map;

public class CommandController {

    private static HashMap<String, Command> commands = new HashMap<>();

    private boolean quit = false;

    private static CommandController ourInstance = new CommandController();

    public static CommandController getInstance() {
        return ourInstance;
    }

    private CommandController() {
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
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}


