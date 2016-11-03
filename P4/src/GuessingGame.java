///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            GuessingGame
// Files:            GuessingGame.java, BinaryTree.java, 
//                   IllegalBinaryTreeOpException.java
// Semester:         CS367 Fall 2014
//
// Author:           Alec Pierce
// Email:            apierce2@wisc.edu
// CS Login:         apierce
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Dan Hayes
// Email:            dahayes@wisc.edu
// CS Login:         dhayes
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;

/**
 * GuessingGame will take a file if provided, and setup a binary tree used in
 * playing a 20Q-like game. However, the game goes on until the user quits,
 * and if an answer cannot be found, will ask the user for a question leading
 * to the answer, as well as the answer. Can also quit the game, reset a tree,
 * and print the tree.
 *
 * <p>Bugs: None
 *
 * @author Alec Pierce and Dan Hayes
 */
public class GuessingGame {

	/**
	 * The main method of the Guessing game program. will build a binary tree
	 * form a provided formatted file, if present. The program will present the
	 * user with four options; (o)utput, (p)lay, (q)uit, (r)eset.  If o is selected
	 * it will print out a preorder list of the current tree. If p is selected it
	 * will play the game with the user. If it fails to answer correctly it will 
	 * ask you for your answer then ask you for a yes or no question so as to build
	 * out the tree and become more accurate. If q is selected it will quit the 
	 * program, and if r is selected it will create a new tree and prompt the user 
	 * for an initial question and answers for the first two child nodes. 
	 *
	 *
	 * @param (args) (a formatted file to build a tree, if present)
	 */
	public static void main(String[] args) throws IOException {

		Scanner stdin = new Scanner(System.in);  // scanner for user input
		BinaryTree<String> tree = new BinaryTree<String>(); //Binart tree that holds att the questions and answers
		Scanner in = null; // scanner for files
		String input; //the user or file input
		String newQ; // new question to be added to the tree
		String newA; // new question to be added to the tree

		if (args.length == 1) in = new Scanner(new FileReader(args[0]));

		tree.start();
		while(true) {
	
			System.out.println("Please enter a command (o, p, q, r):");
			
			if (in != null && in.hasNextLine()) input = in.nextLine();
			else input = stdin.nextLine();
			
			while(input.length() != 1) {
				System.out.println("Please enter a command (o, p, q, r):");
				if (in != null && in.hasNextLine()) input = in.nextLine();
				else input = stdin.nextLine();
			}

			if(input.toLowerCase().equals("o")) tree.print();

			if(input.toLowerCase().equals("p")) {

				if(tree.root.getData() == null) tree = reset(tree, stdin, in);

				tree.current = askQuestion(tree.current, stdin, in);
				System.out.println("I guess: " + tree.current.getData()
						+ ". Was I right?");

				if (in != null && in.hasNextLine()) input = in.nextLine();
				else input = stdin.nextLine();
				
				while(input.length() != 1) {
					
					System.out.print("Please enter 'y' or 'n':");
					if (in != null && in.hasNextLine()) input = in.nextLine();
					else input = stdin.nextLine();
					
				}

				if(input.toLowerCase().equals("n")){

					System.out.println("Darn. Ok, tell me a question that " +
					"is true for your answer, but false for my guess.");

					if (in != null && in.hasNextLine()) newQ = in.nextLine();
					else newQ = stdin.nextLine();
					
					System.out.println("Thanks! And what were you thinking of?");

					if (in != null && in.hasNextLine()) newA = in.nextLine();
					else newA = stdin.nextLine();

					try {
						tree.addLeftChild(newA);
					} catch (IllegalBinaryTreeOpException e) {
						e.printStackTrace();
					}
					
					try {
						tree.addRightChild(tree.getCurrent());
					} catch (IllegalBinaryTreeOpException e) {
						e.printStackTrace();
					}
					
					tree.changeCurrent(newQ);

				}

				else System.out.println("I win!");

				tree.start();

			}

			if(input.toLowerCase().equals("q")) break;

			if(input.toLowerCase().equals("r")) {
				tree = reset(tree, stdin, in);		
			}

		}//end while



	}//end main

	 /**
	 * Creates a new tree with the root node containing the user/file provided question,
	 * a child node to the left wit a gess resulting from a user/file true input and a child
	 * node to the right that contains a guess resulting from a user/file false input. 
	 *
	 * @param (tree) (The tree set to be replaced)
	 * @param (stdin) (Scanner for user input)
	 * @param (in) (Scanner for file input)
	 * @return the new tree created from the users's input.
	 */
	private static BinaryTree<String> reset(BinaryTree<String> tree, Scanner stdin, Scanner in) throws IOException 
	{
		String input;

		System.out.println("Please enter a question.");
		if (in != null && in.hasNextLine()) input = in.nextLine();
		else input = stdin.nextLine();

		tree = new BinaryTree<String>(input);
		tree.start();

		System.out.println("Please enter something that is true for that question.");
		
		if (in != null && in.hasNextLine()) input = in.nextLine();
		else input = stdin.nextLine();

		try {
			tree.addLeftChild(input);
		} catch (IllegalBinaryTreeOpException e1) {
			e1.printStackTrace();
		}

		System.out.println("Please enter something that is false for that question.");
		if (in != null && in.hasNextLine()) input = in.nextLine();
		else input = stdin.nextLine();

		try {
			tree.addRightChild(input);
		} catch (IllegalBinaryTreeOpException e) {
			e.printStackTrace();
		}

		return tree;
	}//end method

	 /**
	 * A recursive method for traversing the binart tree and asking questions finally 
	 * returning a node containing the guessed answer when if finda a leaf node.
	 *
	 * @param (node) 
	 * @param (stdin) Scanner for user input
	 * @param (in) Scanner for file input
	 * @return the node with the guessed answer. 
	 */
	private static BinaryTreenode<String> askQuestion(BinaryTreenode<String> node, Scanner stdin, Scanner in) throws IOException {

		BinaryTreenode<String> answer = null;
		String input = null;
		
		// base case: if at a tree leaf return that node
		if(node.getLeft() == null){
			return node;
		}

		//ask questions
		System.out.println(node.getData());

		if (in != null && in.hasNextLine()) input = in.nextLine();
		else input = stdin.nextLine();
			
		while(input.length() != 1) {
			
			System.out.print("Please enter 'y' or 'n':");
			if (in != null && in.hasNextLine()) input = in.nextLine();
			else input = stdin.nextLine();
			
		}
		
		//recursive call left
		if(input.toLowerCase().equals("y")) {
			answer = askQuestion(node.getLeft(), stdin, in);
		}
		
		//recursive call right
		if(input.toLowerCase().equals("n")) {
			answer = askQuestion(node.getRight(), stdin, in);
		}

		return answer;
	}//end method

}//end class
