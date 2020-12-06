package vnexpress;

import java.io.IOException;
import txtfilesave.*;

public class VnExpNews {
	private int pageStart; // trang lấy link đầu tiên của mục bài viết chứng khoán
	private int pageEnd; // trang lấy link cuối cùng của mục bài viết chứng khoán
	private ITxtFileSave news;
	
	public VnExpNews() {
		this.pageStart = 1;
    	this.pageEnd = 1;
    	this.news = new VnExpressNewsTxtSave();
	}
	
	public void printNews (String keyword) throws IOException {
    	java.util.Date date = new java.util.Date();  
    	System.out.println("Modified Time: "+ date + "\n====================\n");
    	news.storage("Modified Time: "+ date + "\n====================\n");
    	
    	for (int page = pageStart; page <= pageEnd; page++) {
        	VnExpGetLinks links = new VnExpGetLinks(page); // Lấy links ở trang thứ 'page' mục chứng khoán

        	// Chỉ in ra thông tin ngày tháng những bài viết có câu chứa từ khóa
        	for (String std: links.getLinks()) {
        			VnExpSentencesFilter filter = new VnExpSentencesFilter(std);
        			if(filter.sentencesHave(keyword).size() != 0) {
            			System.out.println(filter.getTime()+"\n");
            			news.storage(filter.getTime()+"\n");
        			
            			for(String s: filter.sentencesHave(keyword)) {
        				System.out.println(s);
        				news.storage(s);
        			}
            			
            		System.out.println("______________________________________________\n");
            		news.storage("______________________________________________\n");
    			}
    		}
    	}
    	System.out.println("\n\n********************************************************************************************************************************************************************************************************");
    	news.storage("\n\n********************************************************************************************************************************************************************************************************");
	}

}

