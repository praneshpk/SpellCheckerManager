package edu.ncsu.csc316.spell_checker.manager;

import java.util.Scanner;

/**
 * The SpellCheckerUI class allows the user to interface with SpellCheckerManager
 * and provide input files
 * @author Pranesh Kamalakanthan
 *
 */
public class SpellCheckerUI {
	/**
	 * Starts the program
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the path to the dictionary: ");
		SpellCheckerManager s = new SpellCheckerManager(in.nextLine());
		System.out.print("Enter the path to the words you want to spell check:" );
		System.out.println(s.spellCheck(in.nextLine()));
	}
	
}
