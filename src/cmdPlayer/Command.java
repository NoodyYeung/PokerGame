package cmdPlayer;

public abstract class Command implements Running{

    public abstract void execute();


    public String toString() {
        return key() + ": " + description();
    }
}
