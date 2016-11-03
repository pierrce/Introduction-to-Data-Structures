///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (Twitter.java)
// File:             (Twitter.java)
// Semester:         CS367 Fall 2014
//
// Author:           (Daniel Hayes)
// Email:            (dahayes@wisc.edu)
// CS Login:         (dhayes)
// Lecturer's Name:  (Skrentny)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     (Alec Pierce)
// Email:            (apierce2@wisc.edu)
// CS Login:         (apierce)
// Lecturer's Name:  (Skrentny)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.*;
public class  Twitter{

	public static void main(String[] args) {

		SimpleLinkedList<User> users = new SimpleLinkedList<User>();
		SimpleLinkedList<User> following = new SimpleLinkedList<User>();
		Timeline timeline = new Timeline();
		Timeline searchline = new Timeline();

		//Creates a LinkedList of users.
		for(int i = 0; i<args.length; i++){
			users.add(new User(args[i]));
			following.add(new User(args[i]));
			timeline.add(users.get(i).getTweets());
		}

		Scanner stdin = new Scanner(System.in);  //for console input

		boolean used = false;
		boolean done = false;
		boolean name = false;
		boolean invalid = false;
		while (!done) {
			System.out.print("Enter option : ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) {
				used = false;
				String[] commands = input.split(" ");//split on space
				if(commands[0].equals("list") && commands.length == 2){
					
					if(commands[1].equals("users")){
						for(int i = 0; i<users.size(); i++){
							System.out.println(users.get(i).getName());
						}
						used = true;
					}
					if(commands[1].equals("following")){
						for(int i = 0; i<following.size(); i++){
							System.out.println(following.get(i).getName());
						}
						used = true;
					}
				}
				if (commands[0].equals("follow")&& commands.length == 2){
					for(int i = 0; i<users.size(); i++){
						if(users.get(i).getName().equals(commands[1])){
							timeline.add(users.get(i).getTweets());
							following.add(users.get(i));
							used = true;
							name = false;
						}
						else name = true;
						
					}
				
				}
				
				if (commands[0].equals("unfollow")&& commands.length == 2){
					for(int i = 0; i<following.size(); i++){
						if(following.get(i).getName().equals(commands[1])){
							timeline.remove(following.get(i).getName());
							following.remove(i);
							used = true;
							invalid = false;
						}
						else invalid = true;
					}
				
				}

				if(commands[0].equals("search") && commands.length == 2){
					
					searchline = timeline.search(commands[1]);
					searchline.print();
					
					used = true;
				}
				if(commands[0].equals("print")){

					if(commands.length == 1){
						timeline.print();
						used = true;
					}
					if(commands.length == 2){
						timeline.print(Integer.parseInt(commands[1]));
						used = true;
					}
				
				}
				if(commands[0].equals("quit")){
					used = true;
					done = true;
					System.out.println("exit");
				}
				if(!used){  //a command with no argument
					if(name) System.out.println("Invalid Name");
					else if(invalid) System.out.println("Warning: User not followed");
					else System.out.println("Invalid Command");
					invalid = false;
					name = false;
				}
			}//end if
		}//end while
	}//end main
}