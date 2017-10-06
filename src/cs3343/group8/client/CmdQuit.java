package cs3343.group8.client;

public class CmdQuit extends Command {

    @Override
    public void execute() {
        super.execute();
        CommandController.getInstance().setQuit(true);
    }

    @Override
    public String key() {
        return "q";
    }

    @Override
    public String description() {
        return "Quit";
    }
}
