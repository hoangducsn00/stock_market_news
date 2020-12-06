package stocknews;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import sqlserver.SQLQuery;
import sqlserver.SQLServerConnection;
import txtfilesave.*;

public class SentenceForms {
	
	private ITxtFileSave txt;
	private IPriceHistoryData db;
	
	public SentenceForms(IPriceHistoryData db) {
		this.db =db;
		if(db.getStockExchange().contains("HOSE")) {
			txt = new HOSENewsTxtSave();
		} else if(db.getStockExchange().contains("HASTC")) {
			txt = new HASTCNewsTxtSave();
		} else if (db.getStockExchange().contains("UPCOM")) {
			txt = new UPCOMNewsTxtSave();
		} else {
			txt = new VN30NewsTxtSave();
		}
	}
	
	// Sử dụng để miêu tả chung và lưu giữ vào file txt thông tin các cổ phiếu ở sàn CK khi không dùng SQL Server
	public void describeWithoutSQLServer() throws IOException {
		String[][] data = db.getAllData();
		System.out.println("THÔNG TIN CỔ PHIẾU SÀN " + db.getStockExchange() + " NGÀY " + db.getTime() + "\n\n");
		txt.storage("THÔNG TIN CỔ PHIẾU SÀN " + db.getStockExchange() + " NGÀY " + db.getTime() + "\n\n");
		
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] != null) {
				String name = data[i][0];
				String close = data[i][1];
				String open = data[i][4];
				String vol =data[i][7];
				double changePct = Double.parseDouble(data[i][11]);
				
		        if (changePct > 1.5) {
		        	String s = name + " có giá mở cửa là " + open + " và kết thúc ở " + close + " điểm, tăng mạnh ("
		                    + changePct + "%).";
		            System.out.println(s);
		            txt.storage(s);
		        } else if (changePct > 0 && changePct <= 1.5) {
		        	String s = name + " có giá mở cửa là " + open + " và kết thúc ở " + close + " điểm, tăng ("
		                    + changePct + "%).";
		            System.out.println(s);
		            txt.storage(s);
		        } else if (changePct < 0 && changePct > -1.5) {
		        	String s = db.getTime() + "; " + name + " có giá mở cửa là " + open + " và kết thúc ở " + close
		                    + " điểm, giảm (" + changePct + "%).";
		            System.out.println(s);
		            txt.storage(s);
		        } else if (changePct < -1.5) {
		        	String s = db.getTime() + "; " + name + " có giá mở cửa là " + open + " và kết thúc ở " + close
		                    + " điểm, giảm mạnh (" + changePct + "%).";
		            System.out.println(s);
		            txt.storage(s);
		        } else if (changePct == 0) {
		        	String s = "Chốt phiên ngày " + db.getTime() + "; " + name + " đứng giá tham chiếu với " + open + " điểm.";
		            System.out.println(s);
		            txt.storage(s);
		        }
		        String s = "Khối lượng giao dịch đạt " + vol + " cổ phiếu\n";
		        System.out.println(s);
		        txt.storage(s);
			} else
				break;
		}
	}
	
	// Sử dụng để miêu tả chung thông tin các cổ phiếu ở sàn CK khi dùng SQL Server
	public void describeWithSQLServer() throws ClassNotFoundException, IOException, SQLException {
		SQLServerConnection conn = new SQLServerConnection();
		conn.insertData(db);
        SQLQuery query = new SQLQuery(db);
        ArrayList<Stock> list1 = conn.getAllStock(query.getTop3());
        ArrayList<Stock> list2 = conn.getAllStock(query.getTopCoPhieuTang());
        ArrayList<Stock> list3 = conn.getAllStock(query.getKhongGiaoDich());
        ArrayList<Stock> list4 = conn.getAllStock(query.getTangMax());
        ArrayList<Stock> list5 = conn.getAllStock(query.getGiamMax());
        ArrayList<Stock> list6 = conn.getAllStock(query.getTopCoPhieuGiam());
        ArrayList<Stock> list7 = conn.getAllStock(query.getDungGia());
        
        SentenceForms createNews = new SentenceForms(db);
        if(list1.size() !=0) {
        	createNews.top3(list1);
        }
        if(list2.size() !=0) {
        	createNews.nhomTang(list2);
        }
        if(list6.size() !=0) {
        	createNews.nhomGiam(list6);
        }
        if(list7.size() !=0) {
        	createNews.dungGia(list7);
        }
        if(list3.size() !=0) {
        	createNews.khongGiaoDich(list3);
        }
        if(list4.size() !=0) {
        	createNews.changeMax(list4);
        }
        if(list5.size() !=0) {
        	createNews.changeMax(list5);
        }
        System.out.println("----------***----------");
	}
	
    public void top3(ArrayList<Stock> list) {
        float difference = list.get(0).getVol() - list.get(1).getVol();
        System.out.println("*** Ngày " + list.get(0).getDate() + " ***");
        System.out.println("Mã cổ phiếu được mua bán tích cực nhất trong ngày " + list.get(0).getDate() + " là " + list.get(0).getName() + " với lần lượt là hơn "
                + list.get(0).getVol() + " cổ phiếu" + ". " + " Ít hơn " + difference + " cổ phiếu so với " + list.get(0).getName() + " là mã " + list.get(1).getName()
                + ", " + "\ntheo sau là " + list.get(2).getName() + " với hơn " + list.get(2).getVol() + " cổ phiếu. ");
        System.out.println("Cổ phiếu thanh khoản cao nhất trong ngày hôm nay là " + list.get(0).getName() + " với " + list.get(0).getVol() + " đơn vị, tiếp theo là "
                + list.get(1).getName() + " với " + list.get(1).getVol() + ", " + list.get(2).getName() + " đứng vị trí thứ ba với " + list.get(2).getVol() + " đơn vị được trao tay.");
        System.out.println("Dòng tiền tập trung nhiều vào " + list.get(0).getName() + " và  " + list.get(1).getName() + ", hai mã này dẫn đầu thanh khoản trong ngày hôm nay với hơn "
                + list.get(0).getVol() + "-" + list.get(1).getVol() + " cổ phiếu.\n");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void nhomTang(ArrayList<Stock> list) {
        System.out.println("*** Ngày " + list.get(0).getDate() + " ***");
        System.out.println("NHÓM CỔ PHIẾU TĂNG GIÁ");
        System.out.println("Hàng loạt cổ phiếu tăng mạnh trong ngày hôm nay. Nổi bật nhất vẫn là " + list.get(0).getName() + " với mức giao dịch lên đến " +
                list.get(0).getVol() + " cổ phiếu, tăng " + list.get(0).getChangePct() + " % so với ngày hôm qua.\nChiếm vị trí thứ 2 là " + list.get(1).getName() +
                ", có khối lượng giao dịch khớp lệnh là " + list.get(1).getVol() + " cổ phiếu. " + list.get(2).getName() + " xếp ở vị trí thứ 3.\n");
        for (int i = 3; i < (list.size() - 1); i++) {
            list.get(i).describe();
        }
        System.out.println("\nKhiêm tốn nhất là " + list.get(list.size() - 1).getName() + " với vỏn vẹn " + list.get(list.size() - 1).getVol() +
                " cổ phiếu được khớp lệnh\n");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void nhomGiam(ArrayList<Stock> list) {
        System.out.println("*** Ngày " + list.get(0).getDate() + " ***");
        System.out.println("NHÓM CỔ PHIẾU GIẢM GIÁ");
        System.out.println("Trong ngày hôm nay " + list.get(0).getDate() + "hàng loạt cổ phiếu như " + list.get(0).getName() + ", " + list.get(1).getName() + ", " +
                list.get(2).getName() + " ... tụt giá");
        System.out.println("Giảm giá mạnh nhất là " + list.get(0).getName() + " mở cửa ở " + list.get(0).getOpen() + "và kết thúc ở " + list.get(0).getClose()
                + " giảm " + list.get(0).getChangePct() + " so với ngày hôm trước. ");
        for (int i = 1; i < (list.size() - 1); i++) {
            list.get(i).describe();
        }
        System.out.println("\nTuy có khối lượng giao dịch ở mức " + list.get(list.size() - 1).getVol() + ", " + list.get(list.size() - 1).getName() +
                " vẫn giảm giá so với ngày hôm qua với giá đóng cửa là " + list.get(list.size() - 1).getClose() + " tụt " + list.get(list.size() - 1).getChangePct() + " %.\n");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void dungGia(ArrayList<Stock> list) {
        System.out.println("*** Ngày " + list.get(0).getDate() + " ***");
        System.out.println("NHÓM CỔ PHIẾU CÓ GIAO DỊCH NHƯNG ĐỨNG GIÁ");
        if(list.size() <3) {
        	for (int i = 0; i < (list.size()); i++) {
        		list.get(i).describe();
        	}
        }
        else {
            System.out.println("Trong phiên giao dịch ngày hôm nay " + list.get(0).getDate() + " hàng loạt cổ phiếu đồng thời đứng giá tham chiếu mặc dù có giao dịch. Trong đó có những " +
                    "cổ phiếu có khối lượng giao dịch khớp lệnh rất lớn như " + list.get(0).getName() + ", " + list.get(1).getName() + " khối lượng "
                    + "cổ phiếu giao dịch lên đến hàng triệu đơn vị. ");
            System.out.println("Theo sau đó là các cổ phiếu như ");
            for (int i = 2; i < (list.size() - 1); i++) {
                System.out.print(list.get(i).getName() + ", ");
            }
            System.out.print(list.get(list.size() - 1).getName() + " đứng giá tham chiếu.\n");
        }
            System.out.println("--------------------------------------------------------------------------------");
    }

    public void khongGiaoDich(ArrayList<Stock> list) {
        System.out.println("*** Ngày " + list.get(0).getDate() + " ***");
        System.out.println("NHÓM CỔ PHIẾU KHÔNG CÓ GIAO DỊCH ");
        System.out.print("Các cổ phiếu không có giao dịch ngày " + list.get(0).getDate() + ": ");
        
        for (int i = 0; i < (list.size() - 1); i++) {
            System.out.print(list.get(i).getName() + ", ");
        }
        System.out.print(list.get(list.size() - 1).getName() + ".\n");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void changeMax(ArrayList<Stock> list) { //Cổ phiếu thay đổi kịch trần/sàn. Đối với sàn Hose thì change = 7
        if (list.get(0).getChangePct() > 0) {
            System.out.print("Các cổ phiếu tăng kịch trần ngày hôm nay: \n");
            for (int i = 0; i <= (list.size() - 1); i++) {
                System.out.println(list.get(i).getName() + " - Khối lượng giao dịch: " + list.get(i).getVol());
            }
            System.out.println("\nTrong đó " + list.get(0).getName() + " có khối lượng giao dịch lớn nhất lên đến " + list.get(0).getVol());
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            System.out.print("Các cổ phiếu giảm kịch sàn ngày hôm nay: \n");
            for (int i = 0; i <= (list.size() - 1); i++) {
                System.out.println(list.get(i).getName() + " - Khối lượng giao dịch: " + list.get(i).getVol());
            }
            System.out.println("\nTrong đó " + list.get(0).getName() + " có khối lượng giao dịch lớn nhất lên đến " + list.get(0).getVol());
            System.out.println("--------------------------------------------------------------------------------");
        }

    }

}

