package org.jsoup.doctor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class Scrapper {
	
	// Collect data from Google search
	public void googleData(String key) {
		Document doc;
		String url = "https://www.google.com/search?as_q=" + key +
				"&as_eq=&as_nlo=&as_nhi=&lr=lang_en&cr=countryCA&as_qdr=all&as_sitesearch=&as_occt=any&safe=images&tbs=&as_filetype=&as_rights=";
		System.out.println("url : " + url);
		try {
			 
			// need http protocol
			//doc = Jsoup.connect("http://circ.ahajournals.org/content/131/4/e29.full.pdf+html").userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
			doc = Jsoup.connect(url).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
			
			// get page title
			String pageTitle = doc.title();
			System.out.println("page title : " + pageTitle);
	 
			// get all links
			Elements links = doc.select("li[class=g]");
	        for (Element link : links) {
	        	Elements hrefLink = link.select("a[href]");
	        	String href = null;
	        	if(hrefLink != null)
	        		href = hrefLink.get(0).attr("href");
	        	
	        	Elements titles = link.select("h3[class=r]");
	            String title = titles.text();
	
	            Elements bodies = link.select("span[class=st]");
	            String body = bodies.text();
	
	            System.out.println("Title: "+ title + "\n");
	            System.out.println("link : "+ href + "\n");
	            System.out.println("Body: "+body + "\n");
	        }
	        /*
			for (Element link : links) {
	 
				// get the value from href attribute
				System.out.println("\nlink : " + link.attr("href"));
				System.out.println("text : " + link.text());
	 
			}*/
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// collect data from journal site
	public void journalData(){
		
	}
	
	
	public static void main(String[] args) {
	 
		Document doc;
		String keys = "Peripheral+Vascular+Intervention";
		
		Scrapper scrapper = new Scrapper();
		scrapper.googleData(keys);
	 
	  }
 
}