package sqlserver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import stocknews.Stock;

public class SQLServerConnection {
	
    public static Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try {
            	String url = "jdbc:sqlserver://localhost:1433;databaseName=Stock";// your db name
        		String user = "StockUser"; // your db username
        		String password = "123"; // your db password
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
            	JOptionPane.showMessageDialog(null, "DATABASE 'Stock' NOT FOUND.\nPlease check your SQL Server Database or choose not using SQL Server. ", "ERROR", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(SQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
        	JOptionPane.showMessageDialog(null, "CAN'T CONNECT TO SQL SERVER", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
	public SQLServerConnection() {
		
	}

	public void insertData(stocknews.IPriceHistoryData db) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = getConnection();
		Statement statement = conn.createStatement();

		String data[][] = db.getAllData();
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] != null) {
				String maCK = data[i][0];
				// insert the data
				String table = "CafeF_" + db.getStockExchange() + "_DB";
				try {
					statement.executeUpdate("INSERT INTO "+table + " VALUES ('" + maCK
							+ "', '" + db.getTime() + "', '" + db.getDongCua(maCK) + "', '" + db.getMoCua(maCK) + "', '"
							+ db.getCaoNhat(maCK) + "', '" + db.getThapNhat(maCK) + "', '" + db.getVol(maCK) + "', '"
							+ db.getChangePct(maCK) + "')");
				} catch (SQLException e) {
					// Bỏ qua nếu dữ liệu đã có trong CSDL
				}
			} else
				break;
		}

		conn.close();
	}
	
    public ArrayList<Stock> getAllStock(String query) {
        ArrayList<Stock> list = new ArrayList<>();
        Connection conn = SQLServerConnection.getConnection();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("Ten");
                Date date = rs.getDate("Ngay");
                float close = rs.getFloat("Dong");
                float open = rs.getFloat("Mo");
                float high = rs.getFloat("Cao");
                float low = rs.getFloat("Thap");
                int vol = rs.getInt("Vol");
                float change = rs.getFloat("ChangePct");

                Stock stock = new Stock(name, date, open, close, high, low, vol, change);
                list.add(stock);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
	

}
