package stocknews;

import java.util.Date;

public class Stock {

    private String name; 	// mã Chứng Khoán
    private Date date; 		// ngày lấy thông tin
    private float open;		// giá Mở cửa
    private float close;	// giá Đóng cửa
    private float high;		// giá Cao nhất
    private float low;		// giá Thấp nhất
    private int vol;		// Khối lượng giao dịch
    private float changePct;// Thay đổi giá cổ phiếu theo %

    public Stock() {

    }

    public Stock(String name, Date date, float open, float close, float high, float low, int vol, float changePct) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.changePct = changePct;
    }

    // Miêu tả về thông số 1 cổ phiếu
    public void describe() {

        if (changePct > 1.5) {
            System.out.println(this.name + " có giá mở cửa là " + this.open + " và kết thúc ở " + this.close + " điểm, tăng mạnh ("
                    + this.changePct + "%).");
        } else if (changePct > 0 && changePct <= 1.5) {
            System.out.println(this.name + " có giá mở cửa là " + this.open + " và kết thúc ở " + this.close + " điểm, tăng ("
                    + this.changePct + "%).");
        } else if (changePct < 0 && changePct > -1.5) {
            System.out.println(this.date + "; " + this.name + " có giá mở cửa là " + this.open + " và kết thúc ở " + this.close
                    + " điểm, giảm (" + this.changePct + "%).");
        } else if (changePct < -1.5) {
            System.out.println(this.date + "; " + this.name + " có giá mở cửa là " + this.open + " và kết thúc ở " + this.close
                    + " điểm, giảm mạnh (" + this.changePct + "%).");
        } else if (changePct == 0) {
            System.out.println("Chốt phiên ngày " + this.date + "; " + this.name + " đứng giá tham chiếu với " + this.open + " điểm.");
        }
        System.out.println("Khối lượng giao dịch đạt " + this.vol + " cổ phiếu\n");
    }

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public float getOpen() {
		return open;
	}

	public float getClose() {
		return close;
	}

	public float getHigh() {
		return high;
	}

	public float getLow() {
		return low;
	}

	public int getVol() {
		return vol;
	}

	public float getChangePct() {
		return changePct;
	}

}
