package main;

import exceptions.*;
import vault.PasswordVault;

/**
 * The program is a simple password vault designed to allow a user (client for the vault) to create a username,
 * store site name and site password pairs in the vault.
 *
 * @author Xiaoyu Wang
 * @version 2.0
 */
public class Driver {

	/**
	 * The main method demonstrates all implemented classes' functionality, and handles(catches) all checked exceptions.
	 * @param args A string array containing the command line arguments.
	 * @throws InvalidUsernameException The username is invalid.
	 * @throws InvalidPasswordException The password is invalid.
	 * @throws DuplicateSiteException The site already exists.
	 * @throws DuplicateUserException The user already exists.
	 * @throws PasswordMismatchException The input password is incorrect.
	 * @throws UserLockedOutException The user is blocked due to 3 times failed logins.
	 * @throws UserNotFoundException The user doesn't exist.
	 * @throws SiteNotFoundException The site doesn't exist.
	 * @throws InvalidSiteException The site name is invalid.
	 */
	public static void main(String[] args) throws InvalidUsernameException, InvalidPasswordException,
			DuplicateSiteException, DuplicateUserException, PasswordMismatchException,
			UserLockedOutException, UserNotFoundException, SiteNotFoundException,
			InvalidSiteException {
		// TODO Auto-generated method stub
		// Create a PasswordVault object
		PasswordVault passwordVault = new PasswordVault();

		// 1. Testing Success Case
		System.out.println("** 1. Testing success case: **");
		System.out.println("\nAttempting to add user 'snoopy' and password '$qazwsx12'");
		passwordVault.addNewUser("snoopy", "$qazwsx12");
		System.out.println("Added user 'snoopy'");

		System.out.println("\nAttempting to add site 'amazon' for user 'snoopy'");
		System.out.println("Added site 'amazon' for user 'snoopy' => generated site password: "
						   + passwordVault.addNewSite("snoopy", "$qazwsx12", "amazon"));

		System.out.println("\nAttempting to retrieve 'amazon' site password for user 'snoopy'");
		System.out.println("Retrieved site 'amazon' for user 'snoopy' => retrieved password: "
						   + passwordVault.retrieveSitePassword("snoopy", "$qazwsx12", "amazon"));

		System.out.println("\nAttempting to update 'amazon' site password for user 'snoopy'");
		System.out.println("Updated site 'amazon' for user 'snoopy' => updated password: "
						   + passwordVault.updateSitePassword("snoopy", "$qazwsx12", "amazon"));

		// 2. Testing exceptions for addNewUser
		System.out.println("\n\n** 2. Testing exceptions for addNewUser: ** ");
		System.out.println("\nAttempting to add user 'Daisy' and password '$qazwsx12'");
		try {
			passwordVault.addNewUser("Daisy", "$qazwsx12");
		} catch (InvalidUsernameException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to add user 'daisyy' and password 'qazwsx12'");
		try {
			passwordVault.addNewUser("daisyy", "qazwsx12");
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to add user 'snoopy' and password '$qazwsx12'");
		try {
			passwordVault.addNewUser("snoopy", "!qazwsx12");
		} catch (DuplicateUserException e) {
			System.out.println(e.getMessage());
		}

		// 3. Testing exceptions for addNewSite
		System.out.println("\n\n** 3.  Testing exceptions for addNewSite: **");
		System.out.println("\nAttempting to add site 'amazon' for user 'snoopy'");
		try {
			passwordVault.addNewSite("snoopy", "$qazwsx12", "amazon");
		} catch (DuplicateSiteException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to add site 'amazon' for user 'einstein'");
		try {
			passwordVault.addNewSite("einstein", "$qazwsx12", "amazon");
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to add site 'amazon.com' for user 'snoopy'");
		try {
			passwordVault.addNewSite("snoopy", "$qazwsx12", "amazon.com");
		} catch (InvalidSiteException e) {
			System.out.println(e.getMessage());
		}

		// 4. Testing exceptions for retrieveSitePassword
		System.out.println("\n\n** 4.  Testing exceptions for retrieveSitePassword: **");
		System.out.println("\nAttempting to retrieve 'amazon.com' site password for user 'snoopy'");
		try {
			passwordVault.retrieveSitePassword("snoopy", "$qazwsx12", "amazon.com");
		} catch (SiteNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// 5. Testing exceptions for updateSitePassword
		System.out.println("\n\n** 5.  Testing exceptions for updateSitePassword: **");
		System.out.println("\nAttempting to update 'amazon.com' site password for user 'snoopy'");
		try {
			passwordVault.updateSitePassword("snoopy", "$qazwsx12", "amazon.com");
		} catch (SiteNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to update 'amazon' site password for user 'snoopy'");
		try {
			passwordVault.updateSitePassword("snoopy", "#qazwsx12", "amazon");
		} catch (PasswordMismatchException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to update 'amazon' site password for user 'snoopy'");
		try {
			passwordVault.updateSitePassword("snoopy", "%qazwsx12", "amazon");
		} catch (PasswordMismatchException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to update 'amazon' site password for user 'snoopy'");
		try {
			passwordVault.updateSitePassword("snoopy", "^qazwsx12", "amazon");
		} catch (PasswordMismatchException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nAttempting to update 'amazon' site password for user 'snoopy'");
		try {
			passwordVault.updateSitePassword("snoopy", "&qazwsx12", "amazon");
		} catch (UserLockedOutException e) {
			System.out.println(e.getMessage());
		}

	}

}
