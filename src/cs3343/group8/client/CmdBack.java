package cs3343.group8.client;

public class CmdBack extends Command {
    private String key;
    private String description;

    public CmdBack() {
    }

    public CmdBack(String key, String description) {
        this.key = key;
        this.description = description;
    }

    @Override
    public void execute() {
        CommandController.getInstance().quitOne(true);
    }

    @Override
    public String key() {
        return key != null ? key : "q";
    }

    @Override
    public String description() {
        return description != null ? description : "Back";
    }
}
