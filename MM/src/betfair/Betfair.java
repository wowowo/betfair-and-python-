package betfair;



import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class that gets information about the featured matches from the Betfair website.
 * @author User
 *
 */
public class Betfair {

	
	/**
	 * Extract the matches from the url, create Event objects representing then
	 * then sort according to the available bets.
	 * @param url - can be changed to get information, about the Premiership or others.
	 * @return - an array of Events, representing the featured matches
	 * @throws Exception
	 */
	public static Event[] getEvents(String url) throws Exception {

		Document doc = Jsoup.connect(url).get();
		Elements events = doc.getElementsByClass("event-list");
		Element event_list = events.get(0);
		events = null;
		events = event_list.children();
		event_list = null;
		Event[] eventList = new Event[events.size()];
		
		for(int i = 0; i < events.size(); i++) {
			
			Element event = events.get(i);
			eventList[i] = new Event();
			eventList[i].url = extractUrl(event.select("a[href]").first().toString());
			eventList[i].info = event.text();
			eventList[i].markets = getMarkets(eventList[i].info);
			
		}
		
		Arrays.sort(eventList);
		return  eventList;
		
	}
	
	/**
	 * 
	 * @param info - the information about the particular event
	 * @return - the number of bets available
	 */
	private static int getMarkets(String info) {

		int first = info.indexOf('+');
		return Integer.parseInt(info.substring(first, info.indexOf(' ', first + 1)));
		
	}

	/**
	 * 
	 * @param str - the information returned from the server
	 * @return - the specific url for that event
	 */
	private static String extractUrl(String str) {
		
		int first = str.indexOf('"');
		return "betfair.com" + str.substring(first + 1, str.indexOf('"', first + 1));
		
	}
	

}
