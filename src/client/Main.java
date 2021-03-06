package client;

import command.CommandController;

import java.util.Scanner;
public class Main {
	private static CommandController commandController = CommandController.getInstance();
	public static Scanner systemIn;
	public static void main(String[] args) {

		/*
		 *   @
		    @|@
		   (___)
		 * */

		// display menu
		System.out.println("==============================================");
		System.out.println("==============================@===============");
		System.out.println("Welcome to Poker Game        @|@");
		System.out.println("                             \\|/");
		System.out.println("                             ) (");
		System.out.println("                            (___)");
		// commandController.display();
		// System.out.println("==============================================");

		Main.systemIn = new Scanner(System.in);

		String input;

		try {
			while (!commandController.shouldQuit()) {
				System.out.println("==============================================");
				commandController.display();
				System.out.println("==============================================");
				
				System.out.print("Menu> ");
				input = Main.systemIn.nextLine();
				commandController.execute(input.trim());

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			Main.systemIn.close();
		}

	}
}
