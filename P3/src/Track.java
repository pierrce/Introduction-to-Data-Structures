///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             Track.java
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
 * The track class keeps track of the tracks connecting the stations/platforms 
 * as well as the trains that move across them.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Alec Pierce & Dan Hayes
 */

public class Track {

	//Initialized Variables
	@SuppressWarnings("rawtypes")
	SimpleQueue track;
	Train tempTrain;
	
	/**
	 * Constructs a new track.
	 *
	 * @param (int capacity) The max size of the track.
	 */
	@SuppressWarnings("rawtypes")
	public Track(int capacity) {
		track = new SimpleQueue(capacity);
	}
	
	/**
	 * Put adds a train to the track when there's room.
	 *
	 * @param (Train item) The train to be added to the track.
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(Train item) throws FullQueueException {
		try {
			track.enqueue(item);
		} catch (FullQueueException e) {
			throw new FullQueueException();
		}
	}

	/**
	 * Get removes the train in the first position of the track.
	 *
	 * @return Returns the first train.
	 */
	public Train dequeue() throws EmptyQueueException {
		return (Train) track.dequeue();
	}

	/**
	 * Check looks at the first train on the track without removing it.
	 *
	 * @return Returns the first train.
	 */
	public Train peek() throws EmptyQueueException {
		try {
			return (Train) track.peek();
		} catch (EmptyQueueException e) {
			throw new EmptyQueueException();
		}
	}

	/**
	 * isEmpty tells the user whether the track is empty or not.
	 *
	 * @return Returns true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return track.isEmpty();
	}

	/**
	 * isFull tells the user whether the track is full or not.
	 *
	 * @return Returns true if full, false otherwise.
	 */
	public boolean isFull() {
		return track.isFull();
	}

}
