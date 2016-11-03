///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            MapBenchmark.java
// Files:            MapBenchmark.java, SimpleHashMap.java, SimpleTreeMap.java, Entry.java
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *Map benchmark is a simple program to take a series of keys and values and store them
 *in both a TreeMap and a HashMap. The program the proceedes to preforme a get function,
 *a minKey function and a remove function for all the elements in booth maps. The program
 *dose this a fixed amount of time defined by the second console command. For each operation
 *the program keeps track of the minimum time, maximum time average time and the standard
 *deviation for each operation. The program will then display the min time, max time, average
 *and STD of each operation.
 *
 * @author Alec Pierce and Dan Hayes
 */
public class MapBenchmark{

	/**
	 *Map benchmark is a simple program to take a series of keys and values and store them
	 *in both a TreeMap and a HashMap. The program the proceeds to perform a get function,
	 *a minKey function and a remove function for all the elements in booth maps. The program
	 *dose this a fixed amount of time defined by the second console command. For each operation
	 *the program keeps track of the minimum time, maximum time average time and the standard
	 *deviation for each operation. The program will then display the min time, max time, average
	 *and STD of each operation.
	 *
	 * @param (args) (a formatted file to build the hashMap and TreeMap, an int representing the
	 * 					number of iterations the main for loop will exacute.)
	 */
	
	
	public static void main(String[] args) {
		int numIter = Integer.parseInt(args[1]);//number of iterations to run
		long div = numIter;
		SimpleHashMap hashMap = new SimpleHashMap();
		SimpleTreeMap treeMap = new SimpleTreeMap();
		ArrayList<Integer> keys = new ArrayList();
		ArrayList<String> values = new ArrayList();


		double popHash = 0; //mean time of operation popHash
		long popHashMax = 0; // max time of operation popHash
		long popHashMin = 0; // min time of operation popHash
		double popHashStd = 0; //STD of time of operation popHash
		ArrayList<Long> popH = new ArrayList<Long>(); //Array to hold all of the times of popHash 
		double popTree = 0; //mean time of operation popTree
		long popTreeMax = 0; // max time of operation popTree
		long popTreeMin = 0; // min time of operation popTree
		double popTreeStd = 0; //STD of time of operation popTree
		ArrayList<Long> popT = new ArrayList<Long>(); //Array to hold all of the times of popTree
		double getHash = 0; //mean time of operation getHash
		long getHashMax = 0; // max time of operation getHash
		long getHashMin = 0; // min time of operation getHash
		double getHashStd = 0; //STD of time of operation getHash
		ArrayList<Long> getH = new ArrayList<Long>(); //Array to hold all of the times of getHash
		double getTree = 0; //mean time of operation getTree
		long getTreeMax = 0; // max time of operation getTree
		long getTreeMin = 0; // min time of operation getTree
		double getTreeStd = 0; //STD of time of operation getTree
		ArrayList<Long> getT = new ArrayList<Long>(); //Array to hold all of the times of getTree
		double floorKeyHash = 0; //mean time of operation floorKeyHash
		long floorKeyHashMax = 0; // max time of operation floorKeyHash
		long floorKeyHashMin = 0; // min time of operation floorKeyHash
		double floorKeyHashStd = 0; //STD of time of operation floorKeyHash
		ArrayList<Long> floorKeyH = new ArrayList<Long>(); //Array to hold all of the times of floorKeyHash
		double floorKeyTree = 0; //mean time of operation floorKeyTree
		long floorKeyTreeMax = 0; // max time of operation floorKeyTree
		long floorKeyTreeMin = 0; // min time of operation floorKeyTree
		double floorKeyTreeStd = 0; //STD of time of operation floorKeyTree
		ArrayList<Long> floorKeyT = new ArrayList<Long>(); //Array to hold all of the times of floorKeyTree 
		double removeHash = 0; //mean time of operation removeHash
		long removeHashMax = 0; // max time of operation removeHash
		long removeHashMin = 0; // min time of operation removeHash
		double removeHashStd = 0; //STD of time of operation removeHash
		ArrayList<Long> removeH = new ArrayList<Long>(); //Array to hold all of the times of removeHash
		double removeTree = 0; //mean time of operation removeTree
		long removeTreeMax = 0; // max time of operation removeTree
		long removeTreeMin = 0; // min time of operation removeTree
		double removeTreeStd = 0; //STD of time of operation removeTree
		ArrayList<Long> removeT = new ArrayList<Long>(); //Array to hold all of the times of removeTree
		long elapsed = 0; //time elapsed for a single operation
		long startTime = 0; //system time at the start of an operation
		Scanner in = null; // scanner for files
		String input; //input from the file

		//input error handling for 2 args and a valid file
		if (args.length == 2){
			try {
				in = new Scanner(new FileReader(args[0]));
			} catch (FileNotFoundException e) {
				System.out.println("No such file found in areg [0]");
				e.printStackTrace();

			}
		}
		else{System.out.println("there must be 2 command line agruments, a properly formatted"
				+ " input file and an int");}
		while (in.hasNextLine()){
			input = in.nextLine();
			String[] split = input.split(" ");
			keys.add(Integer.parseInt(split[0]));
			values.add(split[1]);

		}


		//main for loop
		for(int ndx = 0;ndx < numIter;ndx++){
			//Basic progress bar
			System.out.print(String.format("%.2f",100* ndx/(float)numIter) +
					"% done \r"); 


			//Populate the hash map
			startTime = System.currentTimeMillis();

			for (int i = 0; i<keys.size(); i++){
				hashMap.put(keys.get(i), values.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			popHash = popHash + elapsed;
			if(popHashMin == 0) popHashMin = elapsed;
			if (elapsed > popHashMax) popHashMax = elapsed;
			if (elapsed < popHashMin) popHashMin = elapsed;
			popH.add(elapsed);

			//Populate the Tree Map
			startTime = System.currentTimeMillis();
			for (int i = 0; i<keys.size(); i++){
				treeMap.put(keys.get(i), values.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			popTree = popTree + elapsed;
			if(popTreeMin == 0) popTreeMin = elapsed;
			if (elapsed > popTreeMax) popTreeMax = elapsed;
			if (elapsed < popTreeMin) popTreeMin = elapsed;
			popT.add(elapsed);


			//Get all hash items
			startTime = System.currentTimeMillis();
			for (int i = 0; i<keys.size(); i++){
				hashMap.get(keys.get(i));
			}


			elapsed = System.currentTimeMillis() - startTime;
			getHash = getHash + elapsed;
			
			if(getHashMax == 0) getHashMax = elapsed;
			if (elapsed > getHashMax) getHashMax = elapsed;
			if (elapsed < getHashMin) getHashMin = elapsed;
			getH.add(elapsed);


			//Get all tree items
			startTime = System.currentTimeMillis();
			for (int i = 0; i<keys.size(); i++){
				treeMap.get(keys.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			getTree = getTree + elapsed;	
			
			if(getTreeMin == 0) getTreeMin = elapsed;
			if (elapsed > getTreeMax) getTreeMax = elapsed;
			if (elapsed < getTreeMin) getTreeMin = elapsed;
			getT.add(elapsed);


			//Floor key all hash items
			startTime = System.currentTimeMillis();
			for (int i = 0; i<keys.size(); i++){
				hashMap.floorKey(keys.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			floorKeyHash = floorKeyHash + elapsed;
			
			if(floorKeyHashMin == 0) floorKeyHashMin = elapsed;
			if (elapsed > floorKeyHashMax) floorKeyHashMax = elapsed;
			if (elapsed < floorKeyHashMin) floorKeyHashMin = elapsed;
			floorKeyH.add(elapsed);


			///Floor key all Tree items
			startTime = System.currentTimeMillis();

			for (int i = 0; i<keys.size(); i++){
				treeMap.floorKey(keys.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			floorKeyTree = floorKeyTree + elapsed;
			
			if(floorKeyTreeMin == 0) floorKeyTreeMin = elapsed;
			if (elapsed > floorKeyTreeMax) floorKeyTreeMax = elapsed;
			if (elapsed < floorKeyTreeMin) floorKeyTreeMin = elapsed;
			floorKeyT.add(elapsed);


			//Remove all Hash items
			startTime = System.currentTimeMillis();
			for (int i = 0; i<keys.size(); i++){
				hashMap.remove(keys.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			removeHash = removeHash + elapsed;
			
			if(removeHashMin == 0) removeHashMin = elapsed;
			if (elapsed > removeHashMax) removeHashMax = elapsed;
			if (elapsed < removeHashMin) removeHashMin = elapsed;
			removeH.add(elapsed);


			//Remove all Tree Items	
			startTime = System.currentTimeMillis();

			for (int i = 0; i<keys.size(); i++){
				treeMap.remove(keys.get(i));
			}

			elapsed = System.currentTimeMillis() - startTime;
			removeTree = removeTree + elapsed;
			
			if(removeTreeMin == 0) removeTreeMin = elapsed;
			if (elapsed > removeTreeMax) removeTreeMax = elapsed;
			if (elapsed < removeTreeMin) removeTreeMin = elapsed;
			removeT.add(elapsed);

		}//end for loop
		
		//calculate means and STDs
		popHash = popHash / div;
		popHashStd = std(popHash, popH, div);
		getHash = getHash / div;
		getHashStd = std(getHash, getH, div);
		floorKeyHash = floorKeyHash / div;
		floorKeyHashStd = std(floorKeyHash, floorKeyH, div);
		removeHash = removeHash / div;
		removeHashStd = std(removeHash, removeH, div);
		popTree = popTree / div;
		popTreeStd = std(popTree, popT, div);
		getTree = getTree / div;
		getTreeStd = std(getTree, getT, div);
		floorKeyTree = floorKeyTree / div;
		floorKeyTreeStd = std(floorKeyTree, floorKeyT, div);
		removeTree = removeTree / div;
		removeTreeStd = std(removeTree, removeT, div);
		
		//Prints out all collected data 
		System.out.println("\nResults of " + numIter + " runs of " + args[0]+ "\n");
		print("Hash", "get", getHashMin, getHashMax, getHash, getHashStd);
		print("Hash", "put", popHashMin, popHashMax, popHash, popHashStd);
		print("Hash", "floorKey", floorKeyHashMin, floorKeyHashMax, floorKeyHash, floorKeyHashStd);
		print("Hash", "remove", removeHashMin, removeHashMax, removeHash, removeHashStd);
		print("Tree", "get", getTreeMin, getTreeMax, getTree, getTreeStd);
		print("Tree", "put", popTreeMin, popTreeMax, popTree, popTreeStd);
		print("Tree", "floorKey", floorKeyTreeMin, floorKeyTreeMax, floorKeyTree, floorKeyTreeStd);
		print("Tree", "remove", removeTreeMin, removeTreeMax, removeTree, removeTreeStd);
		
		
	}//end main method
	
	
	 /**
	 * A method for printing the information min time, max time, mean time and STD of an 
	 * operation in a easy to read format.
	 *
	 * @param (maptype) -Hash or Tree 
	 * @param (opp) operation performed
	 * @param (min) the min time to perform opp in milliseconds 
	 * @param (max) the max time to perform opp in milliseconds
	 * @param (mean) the mean time to perform opp in milliseconds
	 * @param (std) the STD of times in milliseconds
	 */
	public static void print(String maptype, String opp, long min, long max, double mean, double std){
		System.out.format(maptype +"Map: " + opp +"\n" +
				"--------------------\n" +
				"Min : " + min + "\n" +
				"Max : " + max + "\n" +
				"Mean : " + "%.3f" + "\n" +
				"Std Dev : " + "%.3f" + "\n \n", mean, std);
		
	}
	
	//Gives the callers STDs
	 /**
	 * A method for calculating the Standard Deviation(STD) of the times that it took
	 * to perform the operation in milliseconds.
	 * 
	 * @param (average) - the average time to perform a given task
	 * @param (list) all the times for a given task
	 * @param (numIter) the number of times a task was performed 
	 * @return(double) returns a STD to the caller  
	 */
	public static double std(double average, ArrayList<Long> list, long numIter){
		double std = 0;
		for(int i = 0; i<list.size(); i++){
			std = (std + Math.pow((double)list.get(i) - average, 2));
		}
		std = std / numIter;
		return std;		
	}
}//end class
