package betfair;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**

 * @author User
 *
 */
public class Statistics {
	
	private String link;

	/**
	 * A method that returns a link to a website with information
	 * about a specific match.
	 * @param event - the information about the event
	 * @return - a link with information about the event
	 * @throws IOException
	 */
	public static String getStats(Event event) throws IOException {
		

		// extract the name of the home or away team
		// the search the whoscored website for its 
		// future matches
		int k;
		String s  = event.getHome();
		
		if(s.split("[ ]+").length > 1)
			s = event.getAway();
				
		String url1 = "http://www.whoscored.com/Search/?t=" 
						  + s;
		
		Document doc  = Jsoup.connect(url1).userAgent("Mozilla").get();
		Elements table = doc.getElementsByTag("table");
		String url = table.get(0).html();
		k = url.indexOf("/Teams/");
		url = "http://www.whoscored.com"  + url.substring(k, url.indexOf('/', k + 7));
		
		//parse the JSON information to extract match id, specific to the website
		String html = Jsoup.connect(url).userAgent("Mozilla").get().html();
		k = html.indexOf("teamFixtures.load");
		html = html.substring(k, html.indexOf(';', k + 1));
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
		Calendar cal = Calendar.getInstance();
		k = html.indexOf(dateFormat.format(cal.getTime()));

		while(html.charAt(k--) != '[');
		url = html.substring(k + 2, html.indexOf(',',k + 1));
		
		return "http://www.whoscored.com/Matches/" + url ;
	}


	public String print() {
		
		return link;
		
	}




}
