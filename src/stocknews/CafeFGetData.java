package stocknews;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CafeFGetData implements IPriceHistoryData {
	private String[][] data;
	private String time;
	private String stockExchange;

	public CafeFGetData(String year, String month, String date, String stockExchange) {
		this.time = year + "-" + month + "-" + date;
		this.stockExchange = stockExchange; 

		data = new String[900][12];

		Document doc;
		try {
			doc = Jsoup.connect("https://s.cafef.vn/TraCuuLichSu2/1/" + stockExchange + "/" + date + "/" + month + "/"
					+ year + ".chn").timeout(5000).get(); // kết nối với link dữ liệu
			Element table = doc.select("table#table2sort").first();
			Elements row = table.select("tr");
			for (int i = 1; i < row.size() - 1; i++) {
				Elements column = row.get(i).select("td");
				for (int j = 0; j < column.size(); j++) {
					switch (j) {
					case 0:
					case 1:
						data[i - 1][j] = column.get(j).text();
						
						break;
					case 3:
						data[i - 1][j - 1] = column.get(j).text();
						break;
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
					case 11:
					case 12:
					case 13:
						data[i - 1][j - 2] = column.get(j).text();
						break;
					default:
						//column 2 và 4 không chứa dữ liệu gì nên không lưu
					}
				}
			}
		} catch (IOException e) {
			System.out.println("ERR_INTERNET_DISCONNECTED\n");
		}
		
		// Kiểm tra có tồn tại dữ liệu vào hay không
		int numberOfValueNotNull = 0;
		for(int i=0; i<data.length; i++) {
			for(int j=0; j<data[0].length; j++) {
				if(data[i][j] != null) {
					numberOfValueNotNull++;
				}
			}
		}
		if (numberOfValueNotNull == 0) {
			JOptionPane.showMessageDialog(null, "NO DATA FOUND.\nPlease insert another date. ", "ERROR", JOptionPane.ERROR_MESSAGE);
			throw new NullPointerException("NO_DATA_FOUND");
		}

	}

	@Override
	public String[][] getAllData() {
		/*
		 * Cấu trúc bảng data
		 * 
		 * data[i][0]: mã CK
		 * data[i][1]: đóng cửa
		 * data[i][2]: thay đổi
		 * data[i][3]: Giá tham chiếu
		 * data[i][4]: Giá mở cửa
		 * data[i][5]: Giá cao nhất
		 * data[i][6]: Giá thấp nhất
		 * data[i][7]: KLGD khớp lệnh
		 * data[i][8]: GTDD khớp lệnh
		 * data[i][9]: KLGD thỏa thuận
		 * data[i][10]: GTDD thỏa thuận
		 * data[i][11]: thay đổi(%)
		 * 
		 */
		return this.data;
		
	}

	@Override
	public String getTime() {
		return this.time;
	}

	@Override
	public double getDongCua(String maCK) {
		double dongCua = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				dongCua = Double.parseDouble(data[i][1]);
				break;
			}
		}
		return dongCua;
	}

	@Override
	public double getMoCua(String maCK) {
		double moCua = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				moCua = Double.parseDouble(data[i][4]);
				break;
			}
		}
		return moCua;
	}

	@Override
	public double getCaoNhat(String maCK) {
		double caoNhat = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				caoNhat = Double.parseDouble(data[i][5]);
				break;
			}
		}
		return caoNhat;
	}

	@Override
	public double getThapNhat(String maCK) {
		double thapNhat = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				thapNhat = Double.parseDouble(data[i][6]);
				break;
			}
		}
		return thapNhat;
	}

	@Override
	public int getVol(String maCK) {
		int KLGD = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				KLGD = Integer.parseInt(data[i][7].replaceAll(",", ""));
				break;
			}
		}
		return KLGD;
	}

	@Override
	public double getChangePct(String maCK) {
		double changePct = 0;
		for (int i = 0; i < 400; i++) {
			if (data[i][0].contains(maCK)) {
				changePct = Double.parseDouble(data[i][11]);
				break;
			}
		}
		return changePct;
	}

	@Override
	public String getStockExchange() {
		return this.stockExchange;
	}



}
