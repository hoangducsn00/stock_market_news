package stocknews;


public interface IPriceHistoryData {
	public String[][] getAllData();
	public String getTime();
	public String getStockExchange();
	public double getDongCua(String name);
	public double getMoCua(String name);
	public double getCaoNhat(String name);
	public double getThapNhat(String name);
	public int getVol(String name);
	public double getChangePct(String name);
}
