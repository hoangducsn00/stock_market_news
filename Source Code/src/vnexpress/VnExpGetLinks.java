/*
 * Lớp sẽ lấy tất cả link bài viết từ các trang chứng khoán vnExpress
 */


package vnexpress;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class VnExpGetLinks {
	private int pageNumber;
	
	public VnExpGetLinks(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public VnExpGetLinks() {
		this(1);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public String[] getLinks() throws IOException{
		String link = ("https://vnexpress.net/kinh-doanh/chung-khoan-p" + pageNumber); // cấu trúc vnExpress: 30 bài viết 1 trang
		Document doc = Jsoup.connect(link).timeout(5000).get();

		String[] links = new String[30];
		for(int i=30*(pageNumber-1)+1; i<=30*pageNumber; i++ ) {
			Elements aElements = doc.select("a[data-medium=Item-"+i+"]");
			String href = aElements.attr("href");
			if(href.contains("https://vnexpress.net")) {
				links[i-30*(pageNumber-1)-1] =href;
			} else {
				links[i-30*(pageNumber-1)-1] = ("https://vnexpress.net"+ href);
			}
		}
		return links;	
	}
	
}
