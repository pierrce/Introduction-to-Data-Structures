// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.*;

public class MovieFlix {

	public static void main(String[] args) throws IOException {

		BufferedReader textReader = null;
		String lineRead = null;
		String[] temp;
		MovieDatabase movDat = new MovieDatabase();
		//Checks if there is exactly one argument.
		if(args.length != 1) {
			System.out.println("Usage: java MovieFlix FileName");
			System.exit(0);
		}

		try {
			textReader = new BufferedReader(new FileReader(args[0]));
		} 

		catch (IOException e) {
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}

		//Reads the data from the txt file and adds actor/movies.
		while((lineRead = textReader.readLine()) != null) {
			temp = lineRead.split(",");
			for(int i = 1; i<temp.length; i++){
				movDat.addMovie(temp[i]);
				movDat.addActor(temp[0], temp[i]);
			}
		}



		Scanner stdin = new Scanner(System.in);  //for console input

		boolean done = false;
		while (!done) {
			System.out.print("Enter option (cdprswx): ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) {
				char choice = input.charAt(0); //strip off option character
				String remainder = "";         //will hold the remaining input
				if (input.length() > 1) {      //if there is an argument
					//trim off any leading or trailing spaces
					remainder = input.substring(1).trim(); 

					switch (choice) { //the commands that have arguments

					//DONE
					case 'c':

						for(int i = 0; i < movDat.getCast(remainder).size(); i++) {
							System.out.println(movDat.getCast(remainder).get(i));
						}

						break;

					case 'p':
						
						
						for(int i = 0; i < movDat.getMovies(remainder).size(); i++) {
							System.out.println(movDat.getMovies(remainder).get(i));
						}
						
						break;
						
					//DONE
					case 'r':
						
						if(movDat.removeMovie(remainder)) {
							System.out.println("Movie Removed");
						}
						else System.out.println("Movie not found");
						break;

					case 's':
						// The following code reads in a comma-separated sequence 
						// of strings.  If there are exactly two strings in the 
						// sequence, the strings are assigned to name1 and name2.
						// Otherwise, an error message is printed.
						String[] tokens = remainder.split("[,]+");
						if (tokens.length != 2) {
							System.out.println("need to provide exactly two names");
						}
						else {
							String name1 = tokens[0].trim();
							String name2 = tokens[1].trim();

							List<Movie> movies1 = movDat.getMovies(name1);
							List<Movie> movies2 = movDat.getMovies(name2);
							
							for(int i = 0; i < movies1.size(); i++){
								for(int j = 0; j < movies2.size(); j++){
									if(movies1.get(i).getTitle().equals(movies2.get(j).getTitle())) {
										System.out.println(movies1.get(i).getTitle());
									}
								}
							}
						}
						break;

					case 'w':
						if(movDat.removeActor(remainder)) System.out.println(remainder + " withdrawn from all movies");
						else System.out.println("actor not found");
						break;

					default: //ignore invalid commands
						System.out.println("Incorrect command.");
						break;

					} // end switch
				} // end if
				else { //if there is no argument
					switch (choice) { //the commands without arguments

					case 'd': 
						// TODO to implement this option ***
						break;

					case 'x':
						done = true;
						System.out.println("exit");
						break;

					default:  //a command with no argument
						System.out.println("Incorrect command.");
						break;
					} //end switch
				} //end else  
			} //end if
		} //end while
	} //end main
}
