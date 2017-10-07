package cs3343.group8.client;

import java.util.Scanner;

import cs3343.group8.DDZ.Deck;

public class Main {

    private static CommandController commandController = new CommandController();

    public static void main(String[] args) {
        commandController
                .registerCommand(new CmdConnect())
                .registerCommand(new CmdGame())
                .registerCommand(new CmdQuit());
/*
 *   @
    @|@
    \|/
    ) (
   (___)
 * */
        // display menu
        System.out.println("==============================@===============");
        System.out.println("Welcome to Poker Game        @|@");
        System.out.println("                             \\|/");
        System.out.println("                             ) (");
        System.out.println("                            (___)");
        commandController.displayCommandList();
        System.out.println("==============================================");
        
        Scanner in = new Scanner(System.in);

        String input;
        try {
            while (!commandController.shouldQuit()) {
                System.out.print("Menu> ");
                input = in.nextLine();
                commandController.execute(input.trim());


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            in.close();
        }

    }
}
