///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             BinaryTree.java
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
 * Contains all the operations for moving through, constructing, and returning 
 * values of a binary tree. Also, contains fuctions to reset the tree, as well
 * as, print the entire tree.
 *
 * <p>Bugs: None
 *
 * @author Alec Pierce and Dan Hayes
 */
public class BinaryTree<E> {

	//Initialize Variables
	BinaryTreenode<E> root;  //the root node
	BinaryTreenode<E> current;  //the iterative current node

	/**
	 * Constructs an empty BinaryTree with a null root.
	 */
	public BinaryTree() {

		root = new BinaryTreenode<E>();

	}

	/**
	 * Constructs a BinaryTree with data stored in its root.
	 *
	 * @param data Passes the value of the root node.
	 */
	public BinaryTree(E data) {

		root = new BinaryTreenode<E>(data);

	}

	/**
	 * Starts the current reference at the root of the tree to begin 
	 * navigation of the tree.
	 *
	 */
	public void start() {

		current = root;

	}

	/**
	 * Returns the data stored in the current node in navigation. 
	 * Throws an IllegalBinaryTreeOpException if there is no current 
	 * node in navigation.
	 *
	 * @return Returns the data of the current node.
	 */
	public E getCurrent() {

		return current.getData();

	}

	/**
	 * Moves the current reference to the left child of the current node 
	 * in navigation. Throws an IllegalBinaryTreeOpException if the 
	 * current node does not have a left child.
	 *
	 */
	public void goLeft() throws IllegalBinaryTreeOpException {

		if(current.getLeft() != null) current = current.getLeft();

		else throw new IllegalBinaryTreeOpException("Cannot go" +
				" left, node does not exist.");

	}

	/**
	 * Moves the current reference to the right child of the current 
	 * node in navigation. Throws an IllegalBinaryTreeOpException if 
	 * the current node does not have a right child.
	 *
	 */
	public void goRight() throws IllegalBinaryTreeOpException {

		if(current.getRight() != null) current = current.getRight();

		else throw new IllegalBinaryTreeOpException("Cannot go" +
				" right, node does not exist.");

	}

	/**
	 * Returns true if the current node in navigation is a 
	 * leaf (i.e., has no children).
	 *
	 * @return Returns true if leaf, false if not.
	 */
	public boolean isLeaf() {

		if(current.getLeft() == null && current.getRight() == null) return true;

		return false;

	}

	/**
	 * Changes the data held by the current node in navigation 
	 * to the specified data.
	 *
	 * @param (data) Repalces current data with this data.
	 */
	public void changeCurrent(E data) {

		current.setData(data);

	}

	/**
	 * Adds a node with the specified child as the right child of the 
	 * current node in navigation. Throws an IllegalBinaryTreeOpException 
	 * if the current node already has a right child.
	 *
	 * @param (child) The value that is to be placed in the right child node.
	 * @throws IllegalBinaryTreeOpException 
	 */
	public void addRightChild(E child) throws IllegalBinaryTreeOpException { 

		if(current.getRight() != null) 
			throw new IllegalBinaryTreeOpException("Cannot add " +
					"child to the right, node already exists.");
		else current.setRight(child);

	}

	/**
	 * Adds a node with the specified child as the left child of the 
	 * current node in navigation. Throws an IllegalBinaryTreeOpException
	 * if the current node already has a left child.
	 *
	 * @param (child) The value that is to be placed in the left child node.
	 * @throws IllegalBinaryTreeOpException 
	 */
	public void addLeftChild(E child) throws IllegalBinaryTreeOpException {

		if(current.getLeft() != null) 
			throw new IllegalBinaryTreeOpException("Cannot add " +
					"child to the left, node already exists.");
		else current.setLeft(child);

	}

	/**
	 * Calls printHelp() if the root isn't null, else prints
	 * "Empty Tree."
	 */
	public void print() {

		String space = "";

		if(root.getData() != null) printHelp(root, space);

		else System.out.println("Empty Tree");

	}

	/**
	 * Recursive method to Pre-order print the tree, starting at 
	 * the root. Each additional level of the tree should be 
	 * incremented by three spaces.
	 *
	 * @param (curr) Current node of the recursion process.
	 * @param (space) Amount of spaces for indentation.
	 */
	private void printHelp(BinaryTreenode<E> curr, String space) {

		if(curr.getData() != null){
			System.out.println(space + curr.getData());
		}
		space += "   ";
		
		//recursive call right.
		if(curr.getLeft() != null){
			printHelp(curr.getLeft(), space);
		}
		
		//recursive call left.
		if(curr.getRight() != null){
			printHelp(curr.getRight(), space);
		}
	}

}
