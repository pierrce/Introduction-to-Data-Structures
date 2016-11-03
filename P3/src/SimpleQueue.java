///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleQueue.java
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
 * SimpleQueue creates a queue-like data structure that has a first in, first
 * out way of working. 
 *
 * <p>Bugs: 
 * 
 *
 * @author Alec Pierce & Dan Hayes
 */
public class SimpleQueue<E> implements QueueADT<E>{
	
	private E[] queue;
	private E temp;

	/**
	 * Constructor for the SimpleQueue class. Initializes variables
	 * and creates a new SimpleQueue.
	 *
	 * @param (int capacity) Gives the method the max size of the queue. 
	 */
	@SuppressWarnings("unchecked")
	public SimpleQueue(int capacity) {
		queue = (E[])(new Object[capacity]);
	}
	
	/**
	 * Enqueue adds a new object to the next available space. If full throws
	 * an exception.
	 *
	 * @param (E item) The item passed to add to the current queue.
	 * 
	 * @return void
	 */
	@Override
	public void enqueue(E item) throws FullQueueException {
		for(int i = 0; i<queue.length; i++) {
			if(queue[i] == null){
				queue[i] = item;
				break;
			}
		}
		
	}

	/**
	 * Dequeue removes the first object in the queue. If it is empty, throws
	 * an exception.
	 *
	 * @return Returns the first object in queue.
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		temp = queue[0];
		queue[0] = null;
		for(int i = 1; i < queue.length; i++) {
			queue[i-1] = queue[i];
		}
		return temp;
	}

	/**
	 * Peek allows the user to look at the next object to be dequeued
	 * without actually removing it.
	 * 
	 * Returns the first object in queue.
	 */
	@Override
	public E peek() throws EmptyQueueException {
		return queue[0];
	}

	/**
	 * isEmpty returns true if the queue is empty or false otherwise.
	 *
	 * @return True if empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		for(int i = queue.length-1; i > -1; i--) {
			if(queue[i] != null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 *isFull tells the user whether the queue is full or not.
	 *
	 * @return Returns true if full, false otherwise.
	 */
	@Override
	public boolean isFull() {
		if(queue[queue.length-1] != null) return true;
		else return false;
	}

}
