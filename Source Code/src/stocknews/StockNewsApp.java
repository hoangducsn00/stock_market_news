package stocknews;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import vnexpress.*;
import gui.*;

public class StockNewsApp {
	private IPriceHistoryData db;
	InputGUI gui;
	private String date;
	private String month;
	private String year;
	private String stock;
	private boolean SQLConnect;
	private boolean findInfoByKeyword;
	private String keyword;

	public StockNewsApp(InputGUI parentGUI) throws IOException {
		gui = parentGUI;
	}

	public void setDB(IPriceHistoryData db) {
		this.db = db;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public boolean isSQLConnect() {
		return SQLConnect;
	}

	public void setSQLConnect(boolean SQLConnect) {
		this.SQLConnect = SQLConnect;
	}
	
	public boolean isFindInfoByKeyword() {
		return findInfoByKeyword;
	}

	public void setFindInfoByKeyword(boolean SQLConnect) {
		this.findInfoByKeyword = SQLConnect;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	//cài đặt khung hiển thị Output và chuyển hướng hiển thị system.out vào khung này
	public void setOutputGUI() {
		JTextComponent textComponent = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textComponent);

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("STOCK NEWS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scrollPane);
		frame.setSize(600, 700);
		frame.setVisible(true);

		RedirectOutput.sendTo(textComponent);
	}
 
	public void start() {
		//chuyển hướng hiển thị console
		setOutputGUI();
		
		
		// cài đặt input data
		this.setDB(new CafeFGetData(this.getYear(), this.getMonth(), this.getDate(), this.getStock()));
		
		/*
		 * Kiểm tra xem người dùng có muốn sử dụng sql server hay không
		 * nếu có: sinh câu trên các bảng sql server
		 * nếu không: sinh câu từ dữ liệu lấy trực tiếp từ web
		 * 
		 */
		SentenceForms news = new SentenceForms(this.db);
		if(isSQLConnect()) {
			try {
				news.describeWithSQLServer();

			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				news.describeWithoutSQLServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Kiểm tra xem người dùng có muốn tìm kiếm các câu trên báo có chứa keyword không
		 * nếu có: tìm và lọc câu trên các bài báo ở VnExpress
		 * 
		 */
		if(isFindInfoByKeyword()) {
			try {
				VnExpNews newspaper = new VnExpNews();
				newspaper.printNews(getKeyword());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

	public static void main(String[] args) {
		// chạy hiển thị giao diện
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputGUI frame = new InputGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
