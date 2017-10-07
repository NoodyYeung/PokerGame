package cs3343.group8.client;

public class CmdBack extends Command {

    @Override
    public void execute() {
        CommandController.getInstance().quitOne(true);
    }

    @Override
    public String key() {
        return "q";
    }

    @Override
    public String description() {
        return "Back";
    }
}
