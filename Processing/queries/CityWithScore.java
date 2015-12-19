package queries;

public class CityWithScore {
	private String city;
	private String score;
	
	public CityWithScore(String city, String score) {
		this.city = city;
		this.score = score;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getScore() {
		return score;
	}
	@Override
	public String toString(){
		return this.city+"/"+this.score;
	}
}

