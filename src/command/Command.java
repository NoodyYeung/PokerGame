package command;

import cards.ExCardNoExists;

public abstract class Command implements Running{

    public abstract void execute() throws ExCardNoExists;


    public String toString() {
        return key() + ": " + description();
    }
}
