/**
 * 
 */
package com.samer.iam.launcher;

import java.util.Scanner;

import com.samer.iam.business.CreateActivity;

/**
 * @author samer
 *
 */
public class ConsoleLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String choice;
		
		// TODO Auto-generated method stub
		System.out.println("Welcome to the Identity Manager Program");
		Scanner scanner = new Scanner(System.in);
		
		//Will check if the credentials you entered are correct by calling the authentication method
		if(!authenticate(scanner)){
			end(scanner);
			return;
		}
		do{
			//menu
			System.out.println("Please select an action :");
			System.out.println("a. Create an Identity");
			System.out.println("b. Modify an Identity");
			System.out.println("c. Delete an Identity");
			System.out.println("d. Display all Identities");
			System.out.println("e. Quit");
			choice = scanner.nextLine();
			
			switch (choice) {
			case "a":
				//Create
				CreateActivity.execute(scanner);
				break;
			case "b":
				//Modify
				CreateActivity.update(scanner);
				break;
				
			case "c":
				//Delete
				CreateActivity.delete(scanner);
				break;
				
			case "d":
				//Display
				CreateActivity.display();
				break;
				
			case "e":
				//Quit
				break;
				
			default:
				System.out.println("Your choice is not recognized.");
				break;
			}
		}while(!choice.equals("e"));
		
		end(scanner);

	}

	/**
	 * @param scanner
	 */
	private static void end(Scanner scanner) {
		System.out.println("Thank you for using this program!");
		scanner.close();
	}

	/**
	 * @param scanner
	 */
	private static boolean authenticate(Scanner scanner) {
		System.out.println("Please enter your credentials");
		System.out.print("Username: ");
		String usr = scanner.nextLine();
		
		System.out.print("Password: ");
		String pass = scanner.nextLine();
		
		if(usr.equals("admin") && pass.equals("p@$$w0rd")){
			System.out.println("Welcome admin");
			return true;
		}
		else{
			System.out.println("Incorrect credentials");
			return false;
		}
	}

}
