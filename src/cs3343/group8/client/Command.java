package cs3343.group8.client;

public abstract class Command implements Running{

    public abstract void execute();


    public String toString() {
        return key() + ": " + description();
    }
}
