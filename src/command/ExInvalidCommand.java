package command;

/**
 * Created by yeungchunyin on 5/11/2017.
 */
public class ExInvalidCommand extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8929428451753904221L;

	public ExInvalidCommand(String commandKey) {
        super("Invalid Command [" + commandKey + "]!");
    }
}
