///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleStack.java
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
 * A stack that has a first in, last out way of working. Allows the user to add
 * an object onto the stack until full, and remove from the top of the stack
 * until empty.
 *
 * <p>Bugs: 
 *
 * @author Alec Pierce & Dan Hayes
 */
public class SimpleStack<E> implements StackADT<E> {
	
	private E[] stack;
	private E temp;
	
	/**
	 * Constructs a new stack.
	 *
	 * @param (int capacity) Gives the max height of the stack.
	 */
	@SuppressWarnings("unchecked")
	public SimpleStack(int capacity) {
		stack = (E[])(new Object[capacity]);
	}
	
	/**
	 * Push adds an object to the stack if space is available. 
	 *
	 * @param (E item) The item that will be added to the stack.
	 * @return void
	 */
	@Override
	public void push(E item) throws FullStackException {
		for(int i = 0; i < stack.length; i++) {
			if(stack[i] == null) {
				stack[i] = item;
				break;
			}
		}
	}

	/**
	 * Pop removes the top item from the stack if there is an item
	 * available.
	 *
	 * @return Returns the object on the top of the stack.
	 */
	@Override
	public E pop() throws EmptyStackException {
		for(int i = stack.length-1; i > -1; i--) {
			if(stack[i] != null){
				temp = stack[i];
				stack[i] = null;
				return temp;
			}
		}
		return null;
	}

	/**
	 *Peek allows the user to see whats on the top of the stack
	 *without removing it.
	 *
	 * @return Returns the object that is at the top of the stack.
	 */
	@Override
	public E peek() throws EmptyStackException {
		for(int i = stack.length-1; i > -1; i--) {
			if(stack[i] != null){
				return stack[i];
			}
		}
		return null;
	}

	/**
	 * isEmpty tells the user whether the stack is empty or not.
	 *
	 * @return Returns true if empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		for(int i = stack.length-1; i > -1; i--) {
			if(stack[i] != null){
				return false;
			}
		}
		return true;
	}

	/**
	 * isFull tells the user whether the stack is full or not.
	 *
	 * @return Returns true if full, false otherwise.
	 */
	@Override
	public boolean isFull() {
		if(stack[stack.length-1] != null) return true;
		else return false;
	}

}
