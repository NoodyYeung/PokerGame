package command;

/**
 * Created by yeungchunyin on 5/11/2017.
 */
public class ExInvalidCommand extends Exception {
    public ExInvalidCommand(String commandKey) {
        super("Invalid Command [" + commandKey + "]!");
    }
}
