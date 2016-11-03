import java.util.*;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 */
class Timeline{

	private SimpleLinkedList<Tweet> list;
	private Iterator<Tweet> itr;

	/**
	 * Constructs an empty timeline
	 */
	public Timeline(){

		list = new SimpleLinkedList<Tweet>();

	}

	/**
	 * Adds a single tweet to the Timeline
	 * 
	 * @param tweet the tweet to add
	 */
	public void add(Tweet tweet){


		try {
			if(tweet.getMessage().length() > 140)
				throw new TweetTooLongException();
			else list.add(tweet);
		} catch (TweetTooLongException e) {}

	}

	/**
	 * Adds an ordered list of tweets to the Timeline
	 * @param tweets the list of tweets to add
	 */
	public void add(List<Tweet> tweets){

		itr = tweets.iterator();
		while(itr.hasNext()){
			Tweet curr = itr.next();
			try{
				if(curr.getMessage().length() > 140) throw new TweetTooLongException();
				if(list.size() == 0) list.add(curr);
				else{
					
					for(int i = 0; i<list.size(); i++){

					if(list.get(list.size()-1).getTime()<curr.getTime()){
						list.add(curr);
						break;
					}
					if(list.get(1).getTime()>curr.getTime()){
						list.add(1, curr);
					}
					if(list.get(i).getTime()>curr.getTime()){
						list.add(i-1, curr);
						break;
					}

					}
				}
			} catch (TweetTooLongException e) {}
		}

	}

	/**
	 * Removes all tweets by user from the Timeline
	 * 
	 * @param user the user whose tweets should be removed
	 */
	public void remove(String user){

		for(int i = 0; i<list.size(); i++){

			if(list.get(i).getUser().equals(user)) {
				list.remove(i);
				i--;
			}

		}

	}

	/**
	 * Create a new Timeline containing only the tweets containing keyword
	 * 
	 * @param keyword the keyword to search for
	 * @return a Timeline of tweets containing keyword
	 */
	public Timeline search(String keyword){

		Timeline searchList = new Timeline();

		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getMessage().contains(keyword)) {
				searchList.add(list.get(i));
			}

		}
		return searchList;

	}

	/**
	 * Print each tweet in the timeline
	 */
	public void print(){

		for(int i = 0; i<list.size(); i++){

			list.get(i).print();

		}

	}   

	/**
	 * Print each tweet in the timeline since time
	 * 
	 * @param time the largest time to print tweets
	 */
	public void print(int time){

		for(int i = 0; i<list.size(); i++){

			if(list.get(i).getTime() < time){

				list.get(i).print();

			}

		}

	}
}


