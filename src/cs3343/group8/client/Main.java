package cs3343.group8.client;

import java.util.Scanner;

public class Main {

    private static CommandController commandController = CommandController.getInstance();

    public static void main(String[] args) {
        commandController
                .registerCommand(new CmdConnect())
                .registerCommand(new CmdQuit());

        // display menu
        System.out.println("==============================================");
        System.out.println("Welcome to Poker Game");
        System.out.println("==============================================");
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
