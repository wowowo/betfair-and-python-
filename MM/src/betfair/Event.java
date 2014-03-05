package betfair;

/**
 * Class that holds the information about featured matches.
 * @author User
 *
 */
public class Event implements Comparable<Event>{
	
	public String url;
	public String info;
	public int markets;
	
	/**
	 * Compare the events based on the number of available markets
	 */
	public int compareTo(Event that) {
		
		if (this.markets > that.markets)
			return 1;
		
		else if(this.markets < that.markets)
			return -1;
		
		else return 0;
		
	}
	
	/**
	 * 
	 * @return - the home team
	 */
	public String getHome() {
		
		int k = info.lastIndexOf("In-Play") + 7;
		int i = info.indexOf(" v ");
		
		if(i < 0)
			i = info.indexOf('-', k);
		
		return info.substring(k, i - 2);
	}
	
	/**
	 * 
	 * @return - the away team
	 */
	public String getAway() {
	
	int k = info.lastIndexOf("In-Play") + 7;
	int i = info.indexOf(" v ");
		
		if(i < 0)
			i = info.indexOf('-', k);
		
	return info.substring(i + 3, info.indexOf("ET"));
	}
	
	
	/**
	 * 
	 * @return - the odds
	 */
	public String getOdds() {
		
		int i = info.indexOf(' ', info.indexOf("Markets"));
		String[] odds = info.substring(i + 1).split("[ ]+");
		return "Home win: " + odds[0] + " Draw: " + odds[1] + " Away win: " + odds[2];  
		
	}
	

}
