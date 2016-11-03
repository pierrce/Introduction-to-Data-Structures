import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class User {
	
	private BufferedReader in;
	private String name;
	private String tweet;
	private int time;
	private File tweets;
	String[] tempStr = new String[2];
	private List<Tweet> tweetHistory;
	String currLine;
	
	public User(String fileName) {
		
		tempStr = fileName.split("\\.");
		name = tempStr[0];
		tweets = new File(fileName);
		try {
			in = new BufferedReader(new FileReader(tweets));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tweetHistory = new ArrayList<Tweet>();
		
		try {
			while((currLine = in.readLine()) != null){
				
				time = Integer.parseInt(currLine.split(":")[0]);
				tweet = currLine.split(":")[1];
				tweetHistory.add(new Tweet(time, tweet, name));
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getName(){
		return name;
	}
	
	public List<Tweet> getTweets(){
		return tweetHistory;
	}
	

	

}
