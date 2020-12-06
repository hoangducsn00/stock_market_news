package vnexpress;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.LinkedList;

public class VnExpSentencesFilter {
	private String link;
	private Document doc;
	
	
	public VnExpSentencesFilter(String link) throws IOException {
		this.link = link;
		this.doc = Jsoup.connect(link).get();
	}
	
	public String getLink() {
		return link;
	}

	public String getTime() {
		Elements time = doc.select("span[class=date]");
		String timeDisplay = time.get(0).ownText();
		return timeDisplay;
	}	
	
	public LinkedList<String> sentencesHave(String filter){
		LinkedList<String> sentencesFiltered = new LinkedList<>();
		Elements content = doc.select("p[class=Normal]");
		for(int j=0; j<content.size(); j++) {
			//System.out.println(elementPage.get(j).ownText());
			String data = content.get(j).ownText();
			String[] sentences=data.split("\\. ");//chia chuoi dua tren string phan cac nhau boi khoang trang (\\s) 
			for(String s:sentences){  
				if(s.contains(filter)) {
				    sentencesFiltered.add(s);
				}
			} 
		}
		return sentencesFiltered;
	}

}
