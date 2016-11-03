///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             Platform.java
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
 *Creates a platform that keeps track of the trains that are currently stationed
 *at it.
 *
 * <p>Bugs: 
 *
 * @author Alec Pierce & Dan Hayes
 */
public class Platform implements PlatformADT {

	//Initialized variables.
	@SuppressWarnings("rawtypes")
	SimpleStack platform;
	Train tempTrain;
	
	/**
	 * Constructs a new platform.
	 *
	 * @param (int capacity) The max size of the platform.
	 */
	@SuppressWarnings("rawtypes")
	public Platform(int capacity) {
		platform = new SimpleStack(capacity);
	}
	
	/**
	 * Put adds a train to the platform when there's room.
	 *
	 * @param (Train item) The train to be added to the platform.
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void put(Train item) throws FullPlatformException {
		try {
			platform.push(item);
		} catch (FullStackException e) {
			throw new FullPlatformException();
		}
	}

	/**
	 * Get removes the train in the first position of the platform.
	 *
	 * @return Returns the first train.
	 */
	@Override
	public Train get() throws EmptyPlatformException {
		try {
			tempTrain = (Train) platform.pop();
		} catch (EmptyStackException e) {
			throw new EmptyPlatformException();
		}
		return tempTrain;
	}

	/**
	 * Check looks at the first train on the platform without removing it.
	 *
	 * @return Returns the first train.
	 */
	@Override
	public Train check() throws EmptyPlatformException {
		try {
			return (Train) platform.peek();
		} catch (EmptyStackException e) {
			throw new EmptyPlatformException();
		}
	}

	/**
	 * isEmpty returns true if the platform is empty or false otherwise.
	 *
	 * @return True if empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return platform.isEmpty();
	}

	/**
	 *isFull tells the user whether the platform is full or not.
	 *
	 * @return Returns true if full, false otherwise.
	 */
	@Override
	public boolean isFull() {
		return platform.isFull();
	}

}
