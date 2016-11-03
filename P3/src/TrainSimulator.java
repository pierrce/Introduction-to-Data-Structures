///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            TrainSimulator
// Files:            TrainSimulator.java, Platform.java, SimpleStack.java, 
//                   SimpleQueue.java, Track.java
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
// Pair Partner:     (name of your pair programming partner)
// Email:            dahayes@wisc.edu
// CS Login:         dhayes
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class file of the program. Creates a simulation of multiple trains
 * moving from platform to platform using queues and stacks as tracks and 
 * stations.
 *
 * <p>Bugs: We were unable to finish the entire project due to numerous bugs. The more we tried to fix it the more bugs appeared.
 *
 * @author Alec Pierce & Dan Hayes
 */

public class TrainSimulator {

	public static void main(String[] args) throws EmptyPlatformException, FullQueueException, FullPlatformException, EmptyQueueException {

		//Initialized Variables.
		Train[] trains;
		Station[] stations;
		BufferedReader in = null;
		String currLine = null;
		int count = 0;
		int time = 0;
		Track[] tracks;
		String[] out = new String[4];
		ArrayList<String[]> output = new ArrayList<String[]>();





		//Catches all the possible argument errors passed.
		if(args.length != 3){
			System.out.println("Invalid amount of args.");
			System.exit(0);
		}

		//Creates reader for station file.
		try {
			in = new BufferedReader(new FileReader(args[1]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Gets the number of stations.
		try {
			currLine = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		stations = new Station[Integer.parseInt(currLine)];

		//Sets the stations.
		try {
			while((currLine = in.readLine()) != null){
				stations[count] = new Station(Integer.parseInt(currLine.split(",")[0]),
						Integer.parseInt(currLine.split(",")[1]));
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Creates reader for train file.
		try {
			in = new BufferedReader(new FileReader(args[2]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Gets the number of stations.
		try {
			currLine = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		trains = new Train[Integer.parseInt(currLine)];
		count = 0;

		//Sets the trains.
		try {
			while((currLine = in.readLine()) != null){
				trains[count] = new Train(Integer.parseInt(currLine.split(",")[0]));
				for(int i = 1; i<stations.length; i++){
					trains[count].getETD().add(Integer.parseInt(currLine.split(",")[i]));
				}
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Put trains into station 1.
		for(int i = trains.length-1; i>=0; i--){
			stations[0].getPlatform().put(trains[i]);	
		}

		tracks = new Track[stations.length-1];
		for(int i = 0; i<tracks.length; i++){
			tracks[i] = new Track(trains.length);
		}

		while(!stations[stations.length-1].getPlatform().isFull()){

			//move from station to track
			for (int i = 0; i < stations.length -1; i++){

				if(!stations[i].getPlatform().isEmpty()){
					while (!stations[i].getPlatform().isEmpty() && stations[i].getPlatform().check().getETD().get(i) <= time){
						stations[i].getPlatform().check().getATD().add(time);
						//
						out[0] = Integer.toString(time);
						out[1] = Integer.toString(stations[i].getPlatform().check().getId());
						out[2] = " has exited from station ";
						out[3] = Integer.toString(stations[i].getId());
						output.add(out);
						tracks[i].enqueue(stations[i].getPlatform().get());


					}
				}
			}
			// move from track to station
			for (int i = 0; i < tracks.length; i ++){
				if (!tracks[i].isEmpty()){
					while(!stations[i+1].getPlatform().isFull() && time >= (tracks[i].peek()).getETD().get(i) + 10){
						if(!stations[i+1].getPlatform().isFull()){

							out[0] = Integer.toString(time);
							out[1] = Integer.toString(tracks[i].peek().getId());
							out[2] = " has been parked at station ";
							out[3] = Integer.toString(stations[i+1].getId());
							output.add(out);

							stations[i+1].getPlatform().put(tracks[i].dequeue());

						}
					}

				}
			}
			time++;
		}




		//Determines what to output to console.
		switch(Integer.parseInt(args[0])){
		case 0:
			Iterator<String[]> itr = output.iterator();
			while (itr.hasNext()){
				String[] bob = itr.next(); 
				System.out.println(bob[0]+ "\t Train " + bob[1]+bob[2]+bob[3]);
			}
			

			break;
		case 1:

			for(int i = 0; i<trains.length; i++){
				System.out.println(trains[i].getATD());
			}
			break;

		case 2:
			for(int i = 0; i<trains.length; i++){
				System.out.println(trains[i].getATA());
			}
			break;


		default: 
			System.out.println("Invalid Number.");
			break;
		}

	}

}
