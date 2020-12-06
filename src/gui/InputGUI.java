package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import stocknews.StockNewsApp;

public class InputGUI extends JFrame {
	StockNewsApp app;

	public InputGUI()  {
		setGUI();
		try {
			app = new StockNewsApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setGUI() throws InputMismatchException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 180, 652, 463);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("STOCK");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(26, 83, 103, 24);
		contentPane.add(lblNewLabel_1);

		// Cài đặt các RadioButton chọn Stock
		JRadioButton rdbtnHOSE = new JRadioButton("HOSE");
		rdbtnHOSE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnHOSE.setBackground(new Color(255, 69, 0));
		rdbtnHOSE.setBounds(26, 113, 103, 21);
		rdbtnHOSE.setSelected(true); // Cài đặt chọn mặc định HOSE
		contentPane.add(rdbtnHOSE);

		JRadioButton rdbtnHASTC = new JRadioButton("HASTC");
		rdbtnHASTC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnHASTC.setBackground(new Color(255, 215, 0));
		rdbtnHASTC.setBounds(26, 136, 103, 21);
		contentPane.add(rdbtnHASTC);

		JRadioButton rdbtnUPCOM = new JRadioButton("UPCOM");
		rdbtnUPCOM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnUPCOM.setBackground(new Color(144, 238, 144));
		rdbtnUPCOM.setBounds(26, 159, 103, 21);
		contentPane.add(rdbtnUPCOM);

		JRadioButton rdbtnVN30 = new JRadioButton("VN30");
		rdbtnVN30.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnVN30.setBackground(new Color(135, 206, 235));
		rdbtnVN30.setBounds(26, 182, 103, 21);
		contentPane.add(rdbtnVN30);

		ButtonGroup G = new ButtonGroup();
		G.add(rdbtnHOSE);
		G.add(rdbtnHASTC);
		G.add(rdbtnUPCOM);
		G.add(rdbtnVN30);

		// Cài đặt các ComboBox chọn ngày tháng của dữ liệu lấy về
		String currentYear = Integer.toString(java.time.LocalDate.now().getYear());

		int monthValue = java.time.LocalDate.now().getMonthValue();
		String currentMonth = Integer.toString(monthValue);
		if (monthValue < 10) {
			currentMonth = "0" + currentMonth;
		}

		int dateValue = java.time.LocalDate.now().getDayOfMonth();
		String currentDate = Integer.toString(dateValue);
		if (dateValue < 10) {
			currentDate = "0" + currentDate;
		}

		String[] dateCalendar = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JComboBox comboBoxDate = new JComboBox(dateCalendar);
		comboBoxDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxDate.setBounds(188, 138, 59, 21);
		comboBoxDate.setSelectedItem(currentDate); // Cài đặt chọn ngày mặc định là ngày hiện tại
		contentPane.add(comboBoxDate);

		JLabel lblNewLabel_2 = new JLabel("Date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(186, 113, 61, 21);
		contentPane.add(lblNewLabel_2);

		String[] monthCalendar = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox comboBoxMonth = new JComboBox(monthCalendar);
		comboBoxMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxMonth.setBounds(271, 138, 59, 21);
		comboBoxMonth.setSelectedItem(currentMonth); // Cài đặt chọn ngày mặc định là tháng hiện tại
		contentPane.add(comboBoxMonth);

		JLabel lblNewLabel_2_1 = new JLabel("Month");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(271, 113, 61, 21);
		contentPane.add(lblNewLabel_2_1);

		String[] yearCalendar = { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
				"2020" };
		JComboBox comboBoxYear = new JComboBox(yearCalendar);
		comboBoxYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxYear.setBounds(364, 138, 71, 21);
		comboBoxYear.setSelectedItem(currentYear); // Cài đặt chọn năm mặc định là năm hiện tại
		contentPane.add(comboBoxYear);

		JLabel lblNewLabel_2_1_1 = new JLabel("Year");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(364, 113, 61, 21);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3_1 = new JLabel(
				"Notice: Saving data in SQL Database gives you more reviews.");
		lblNewLabel_3_1.setForeground(Color.RED);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3_1.setBounds(188, 206, 436, 21);
		contentPane.add(lblNewLabel_3_1);

		// Cài đặt các RadioButton chọn có lưu dữ liệu vào SQL Server hay không
		JRadioButton rdbtnSaveYes = new JRadioButton("Yes");
		rdbtnSaveYes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSaveYes.setBackground(new Color(102, 205, 170));
		rdbtnSaveYes.setBounds(220, 233, 59, 21);
		rdbtnSaveYes.setSelected(true); // Cài đặt chọn mặc định YES
		contentPane.add(rdbtnSaveYes);

		JRadioButton rdbtnSaveNo = new JRadioButton("No");
		rdbtnSaveNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSaveNo.setBackground(new Color(255, 165, 0));
		rdbtnSaveNo.setBounds(341, 233, 59, 21);
		contentPane.add(rdbtnSaveNo);

		ButtonGroup YN1 = new ButtonGroup();
		YN1.add(rdbtnSaveYes);
		YN1.add(rdbtnSaveNo);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel_4.setBounds(48, 278, 324, 94);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Finding more information on e-newspaper?");
		lblNewLabel_4.setBounds(10, 10, 240, 15);
		panel_4.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new Color(30, 144, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// Cài đặt các RadioButton chọn có tìm kiếm thêm thông tin trên mạng bằng keyword hay không
		JRadioButton rdbtnFindYes = new JRadioButton("Yes");
		rdbtnFindYes.setBounds(32, 31, 59, 21);
		panel_4.add(rdbtnFindYes);
		rdbtnFindYes.setBackground(new Color(102, 205, 170));
		rdbtnFindYes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnFindNo = new JRadioButton("No");
		rdbtnFindNo.setBounds(32, 63, 60, 21);
		panel_4.add(rdbtnFindNo);
		rdbtnFindNo.setBackground(new Color(244, 164, 96));
		rdbtnFindNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnFindNo.setSelected(true); //cài đặt chọn mặc định là NO
		
		ButtonGroup YN2 = new ButtonGroup();
		YN2.add(rdbtnFindYes);
		YN2.add(rdbtnFindNo);
		
		//Cài đặt khu vực nhập từ khóa
		JLabel lblNewLabel_5 = new JLabel("Keyword: ");
		lblNewLabel_5.setBounds(106, 46, 71, 24);
		panel_4.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JTextField keywordField = new JTextField();
		keywordField.setBounds(175, 46, 131, 24);
		panel_4.add(keywordField);
		keywordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		keywordField.setColumns(10);
		
		//Cài đặt button Apply
		JButton btnApply = new JButton("Apply");
		btnApply.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// cài đặt chọn Stock
				if (rdbtnHOSE.isSelected()) {
					app.setStock("HOSE");
				} else if (rdbtnHASTC.isSelected()) {
					app.setStock("HASTC");
				} else if (rdbtnUPCOM.isSelected()) {
					app.setStock("UPCOM");
				} else {
					app.setStock("VN30");
				}

				// cài đặt chọn ngày tháng
				String date = (String) comboBoxDate.getSelectedItem();
				String month = (String) comboBoxMonth.getSelectedItem();
				String year = (String) comboBoxYear.getSelectedItem();
				if (checkTime(year, month, date) == false) {
					JOptionPane.showMessageDialog(null, "THE INSERT DATE_TIME IS NOT AVAILABLE!", "ERROR", JOptionPane.ERROR_MESSAGE);
					throw new InputMismatchException("INSERT_TIME_NOT_AVAILABLE");
				}
				app.setDate(date);
				app.setMonth(month);
				app.setYear(year);

				// cài đặt chọn lưu trữ dữ liệu vào sql server hay không
				if (rdbtnSaveYes.isSelected()) {
					app.setSQLConnect(true);
				} else {
					app.setSQLConnect(false);
				}
				
				// Cài đặt chọn có tìm kiếm thêm thông tin trên mạng bằng keyword hay không
				if (rdbtnFindYes.isSelected()) {
					app.setFindInfoByKeyword(true);
				} else {
					app.setFindInfoByKeyword(false);
				}
				
				String keyword = keywordField.getText();
				app.setKeyword(keyword);
				dispose();
				app.start();
			}
		});
		btnApply.setForeground(Color.WHITE);
		btnApply.setBackground(new Color(64, 224, 208));
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnApply.setBounds(439, 384, 71, 29);
		contentPane.add(btnApply);

		//Cài đặt button Cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(255, 0, 0));
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(520, 384, 71, 29);
		contentPane.add(btnCancel);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(48, 10, 543, 47);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("STOCK DATA ANALYSIS APPLICATION");
		panel.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(100, 149, 237));
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel_1.setBounds(10, 76, 138, 156);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		panel_2.setBounds(167, 83, 284, 94);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1_1 = new JLabel("TIME OF DATA");
		panel_2.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(167, 182, 461, 82);
		contentPane.add(panel_3);
		
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Do you want to save the data in SQL Database?");
		lblNewLabel_3.setBounds(21, 10, 308, 15);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_3);
		contentPane.add(panel_3);

	}
	
	private boolean checkTime (String year, String month, String date) {
		int yearValue = Integer.parseInt(year);
		int monthValue = Integer.parseInt(month);
		int dateValue = Integer.parseInt(date);
		switch (monthValue) {
			case 2:
				if ((yearValue%4 == 0 && yearValue%100 != 0) || (yearValue%100 == 0 && yearValue%400 == 0)){
					if (dateValue > 29) return false;
				} else {
					if (dateValue > 28) return false;
				}
			case 4:
			case 6:
			case 9:
			case 11:
				if (dateValue == 31) {
					return false;
				}
				break;
			default:	
		}
		return true;
	}
}
