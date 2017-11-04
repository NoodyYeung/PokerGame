package player;

import java.util.Scanner;


import cmdPlayer.CommandController;
public class Main {
	private static CommandController commandController = CommandController.getInstance();
	
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

		Scanner in = new Scanner(System.in);

		String input;
		try {
			while (!commandController.shouldQuit()) {
				System.out.println("==============================================");
				commandController.display();
				System.out.println("==============================================");
				
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
