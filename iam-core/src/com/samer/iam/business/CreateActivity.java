/**
 * 
 */
package com.samer.iam.business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.samer.iam.datamodel.Identity;
import com.samer.iam.services.IdentityJDBCDAO;

/**
 * @author samer
 *
 */
public class CreateActivity {
	
	/**
	 * @param scanner
	 */
	public static void execute(Scanner scanner) {
		System.out.println("Enter user's first name:");
		String fname = scanner.nextLine();
		System.out.println("Enter user's last name:");
		String lname = scanner.nextLine();
		System.out.println("Enter user's Email:");
		String email = scanner.nextLine();
		//note: check if input is correct and output
		System.out.println("Enter user's birthdate (for example: 24/12/1990):");
		String birthDate = scanner.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		try {
			date = sdf.parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Identity identity = new Identity("", fname, lname, email, date);
		
		System.out.println("The Identity has been created");
		System.out.println(identity);
		
		//persist the identity in Derby DB
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		try {
			identityJDBCDAO.write(identity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void display(){
		
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		List<Identity> identities = null;
		try {
			identities = identityJDBCDAO.readAllIdentities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!identities.isEmpty()){
			System.out.println("These are all the identites stored");
			for(Identity identity : identities)
			{
				System.out.println(identity);
			}
		}
		else{
			System.out.println("There are no identities stored in the Database");
		}

	}
	
	public static void update(Scanner scanner){
		
		System.out.println("Enter the user's id you want to update");
		String uid = scanner.nextLine();
		
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		
		List<Identity> identities = null;
		try {
			identities = identityJDBCDAO.readAllIdentities(Integer.parseInt(uid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!identities.isEmpty()){
			String choice;
			System.out.println("These are the info of your user");
			for(Identity identity : identities)
			{
				System.out.println(identity);
			}
			do{
				System.out.println("Choose one of these actions");
				System.out.println("a. Change first name");
				System.out.println("b. Change last name");
				System.out.println("c. Change email");
				System.out.println("d. Change birthdate");
				System.out.println("e. Quit");
				choice = scanner.nextLine();
				switch(choice)
				{
				case "a":
					System.out.println("Enter new first name:");
					String fname = scanner.nextLine();
					identities.get(0).setFirstname(fname);
					break;
				case "b":
					System.out.println("Enter new last name:");
					String lname = scanner.nextLine();
					identities.get(0).setLastname(lname);
					break;
				case "c":
					System.out.println("Enter new email:");
					String email = scanner.nextLine();
					identities.get(0).setEmail(email);
					break;
				case "d":
					System.out.println("Enter new birthdate (for example: 24/12/1990):");
					String bdate = scanner.nextLine();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date date=null;
					try {
						date = sdf.parse(bdate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					identities.get(0).setBirthdate(date);
					break;
				case "e":
					//Quit
					break;
				default:
					System.out.println("Your choice is not recognized");
					break;
				}
			}while (!choice.equals("e"));
			try {
				identityJDBCDAO.update(identities.get(0));
			} catch (SQLException e) {
				System.out.println("Couldn't update the info");
				e.printStackTrace();
			}
		}
		else{
			System.out.println("There is no user with this id");
		}	
	}
	public static void delete(Scanner scanner){
		
		
		System.out.println("Enter the user's id you want to delete");
		String uid = scanner.nextLine();
		
		IdentityJDBCDAO identityJDBCDAO = new IdentityJDBCDAO();
		
		List<Identity> identities = null;
		try {
			identities = identityJDBCDAO.readAllIdentities(Integer.parseInt(uid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!identities.isEmpty()){
			for(Identity identity : identities)
			{
				System.out.println(identity);
			}
			String c;
			do{
				System.out.println("Are you sure you want to delete this identity? (Y/n)");
				c = scanner.nextLine();
				switch(c)
				{
				case "Y":
					try {
						identityJDBCDAO.delete(identities.get(0));
						System.out.println("User deleted!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "n":
					System.out.println("This user will not be deleted!");
					//exit the loop
					break;
				default:
					System.out.println("Please answer by Y or n...");
					break;
				}
			}while (!(c.equals("n") || c.equals("Y")));

		}
		else{
			System.out.println("There is no user with this id");
		}
		
		
	}

	
}
