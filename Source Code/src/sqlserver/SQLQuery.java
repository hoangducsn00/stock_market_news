package sqlserver;

import stocknews.IPriceHistoryData;

public class SQLQuery { 
    private String topCoPhieuTang;
    private String topCoPhieuGiam;
    private String top3;
    private String dungGia;
    private String khongGiaoDich; 
    private String tangMax;
    private String giamMax;
    
    public SQLQuery(IPriceHistoryData db) {
    	String table = "CafeF_" + db.getStockExchange() + "_DB";
    	String date = db.getTime();
    	this.topCoPhieuTang = "select * from "+ table +" where ChangePct > 0 and Ngay = '"+ date +"' order by ChangePct desc";
    	this.topCoPhieuGiam = "select * from "+ table +" where ChangePct < 0 and Ngay = '"+ date +"' order by ChangePct asc";
    	this.top3 = "select top 3 * from "+ table +" where Ngay = '"+ date +"' order by ChangePct desc";
    	this.dungGia = "select top 10 * from "+ table +" where ChangePct = 0 and Ngay = '"+ date +"' order by Vol desc";
    	this.khongGiaoDich = "select * from "+ table +" where Vol = 0 and Ngay = '"+ date +"'";
    	this.tangMax = "select * from "+ table +" where Ten in (select top 1 with ties Ten from "+ table +" order by ChangePct desc) and Ngay = '"+ date +"'  order by Vol desc";
    	this.giamMax = "select * from "+ table +" where Ten in (select top 1 with ties Ten from "+ table +" order by ChangePct asc) and Ngay = '"+ date +"' order by Vol desc";
    }

	public String getTopCoPhieuTang() {
		return topCoPhieuTang;
	}

	public String getTopCoPhieuGiam() {
		return topCoPhieuGiam;
	}

	public String getTop3() {
		return top3;
	}

	public String getDungGia() {
		return dungGia;
	}
	
	public String getKhongGiaoDich() {
		return khongGiaoDich;
	}

	public String getTangMax() {
		return tangMax;
	}

	public String getGiamMax() {
		return giamMax;
	}
    
}
