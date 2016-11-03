import java.util.*;


public class MovieDatabase {

	private List<Movie> movieDB;
	private boolean exists = false;


	//DONE
	public MovieDatabase() {
		movieDB = new ArrayList<Movie>();
	}

	//DONE
	public void addMovie(String t){

		Movie m = new Movie(t);

		if(movieDB.contains(m)) exists = true;

		if(!exists) {
			movieDB.add(movieDB.size(), m);
		}
	}

	//DONE
	public void addActor(String n, String t) {

		this.getCast(t).add(n);

	}

	//DONE
	public boolean removeMovie(String t) {

		for(int i = 0; i<size(); i++){
			if(movieDB.get(i).getTitle().equals(t)){
				movieDB.remove(movieDB.get(i));
				return true;
			}
		}

		return false;
	}

	//DONE
	public boolean containsMovie(String t) {

		for(int i = 0; i<size(); i++) {
			if(movieDB.get(i).getTitle().equals(t)) return true;
		}

		return false;	
	}

	//DONE
	public boolean containsActor(String n) {

		for(int i = 0; i<size(); i++) {
			for(int j = 0; j<movieDB.get(i).getCast().size(); j++){
				if(movieDB.get(i).getCast().get(j).equals(n)) {
					return true;
				}
			}
		}

		return false;
	}

	//DONE
	public boolean isCast(String n,String t) {

		for(int i = 0; i<size(); i++) {
			if(movieDB.get(i).getTitle().equals(t)) {
				for(int j = 0; j<getCast(t).size(); j++) {
					if(getCast(t).get(j).equals(n)) return true;
				}
			}
		}

		return false;

	}

	//DONE
	public List getCast(String t) {
		for(int i = 0; i<movieDB.size(); i++){
			if(movieDB.get(i).getTitle().equals(t)){
				return movieDB.get(i).getCast();
			}
		}
		
		return null;

	}

	//DONE
	public List getMovies(String n) {

		exists = false;
		List<Movie> movies = new ArrayList<Movie>();
		for(int i = 0; i<size(); i++){
			if(isCast(n, movieDB.get(i).getTitle())) {
				movies.add(movieDB.get(i));
				exists = true;
			}
		}
		if(exists) return movies;
		else return null;



	}

	//DONE
	public Iterator<Movie> iterator() {

		Iterator<Movie> itr = this.movieDB.iterator();

		return itr;

	}

	//DONE
	public int size() {

		return movieDB.size();

	}


	public boolean removeActor(String n){
		Iterator itr = this.iterator();
		while(itr.hasNext()){
			itr.next().
			Iterator itr2 = itr.next().;
			while(itr2.hasNext()){
				if(( movieDB.itr().getCast().itr2().equals(n))){
					
				}
			}
		}
		return false;

	}

}
