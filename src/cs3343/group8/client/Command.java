package cs3343.group8.client;

public abstract class Command {

    public void execute() {
    }

    public abstract String key();

    public abstract String description();

    public String toString() {
        return key() + ": " + description();
    }
}
