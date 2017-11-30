package command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yeungchunyin on 8/10/2017.
 * To handle the options from server. Intends to create menu by using the server response.
 */
public abstract class AbstractDynamicMenu extends Menu {
    private LinkedHashMap<String, Running> commands;
    public abstract Menu onPostExecuted(Menu menu);
    public abstract Menu executeCmd(String cmd);


    public AbstractDynamicMenu() {
        this.commands = getCommands();
    }

    public abstract LinkedHashMap<String, Running> getCommands();
    @Override
    public void displayCommandList() {
        for (Map.Entry<String, Running> entry : commands.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public Menu execute(String commandKey) {
        Menu menu = executeCmd(commandKey);
        return onPostExecuted(menu);
    }
}
