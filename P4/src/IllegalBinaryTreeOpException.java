///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             IllegalBinaryTreeOpException.java
// Semester:         CS367 Fall 2014
//
// Author:           Alec Pierce apierce2@wisc.edu
// CS Login:         apeirce
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Dan Hayes dahayes@wisc.edu
// CS Login:         dhayes
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Throws an IllegalBinaryOpException and tells the user which node operation
 * caused the exception.
 *
 * <p>Bugs: None
 *
 * @author Alec Pierce and Dan Hayes
 */
@SuppressWarnings("serial")
public class IllegalBinaryTreeOpException extends Exception {

	/**
	 * Constructor with 1 arg.
	 *
	 * @param arg Name of method that threw exception.
	 */
	public IllegalBinaryTreeOpException(String arg) {

		System.out.println(arg);

	}
}
