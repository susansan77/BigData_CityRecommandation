package queries;

public class SearchCritiria {
	private String restaurantRating;
	private String lodgingRating;
	private String pointsRating;
	private String comeFrom;
	private String radius;
	private String lowBudget;
	private String highBudget;
	private String lowTemperature;
	private String highTemperature;
	private String depatureMonth;
	
	public SearchCritiria(String restaurant, String lodging, String points) {
		this.restaurantRating = restaurant;
		this.lodgingRating = lodging;
		this.pointsRating = points;
		this.comeFrom = "-1";
		this.radius = "-1";
		this.lowBudget = "-1";
		this.highBudget = "-1";
		this.lowTemperature = "-1";
		this.highTemperature = "-1";
		this.depatureMonth = "-1";
	}
	
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	
	public String getComeFrom() {
		return comeFrom;
	}
	
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	public String getRadius() {
		return radius;
	}
	
	public void setLowBudget(String lowBudget) {
		this.lowBudget = lowBudget;
	}
	
	public String getLowBudget() {
		return lowBudget;
	}
	
	public void setHighBudget(String highBudget) {
		this.highBudget = highBudget;
	}
	
	public String getHighBudget() {
		return highBudget;
	}
	
	public void setLowTemperature(String lowTemperature) {
		this.lowTemperature = lowTemperature;
	}
	
	public String getLowTemperature() {
		return lowTemperature;
	}
	
	public void setHighTemperature(String highTemperature) {
		this.highTemperature = highTemperature;
	}
	
	public String getHighTemperature() {
		return highTemperature;
	}
	
	public void setRestaurantRating(String restaurantRating) {
		this.restaurantRating = restaurantRating;
	}
	
	public String getRestaurantRating() {
		return restaurantRating;
	}
	
	public void setLodgingRating(String lodgingRating) {
		this.lodgingRating = lodgingRating;
	}
	
	public String getLodgingRating() {
		return lodgingRating;
	}
	
	public void setPointsRating(String pointsRating) {
		this.pointsRating = pointsRating;
	}
	
	public String getPointsRating() {
		return pointsRating;
	}
	
	public void setDepatureMonth(String depatureMonth) {
		this.depatureMonth = depatureMonth;
	}
	
	public String getDepatureMonth() {
		return depatureMonth;
	}


}
