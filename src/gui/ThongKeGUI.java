package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bus.BangChamCongBUS;
import bus.ChamCongBUS;
import bus.NhanVienBUS;
import bus.PhongBanBUS;

public class ThongKeGUI extends JPanel {
	NhanVienBUS nvBUS = new NhanVienBUS();
	PhongBanBUS pbBUS = new PhongBanBUS();
	ChamCongBUS ccBUS = new ChamCongBUS();
	BangChamCongBUS bccBUS = new BangChamCongBUS();

	MyProps myProps = new MyProps();

	JTable tblTK;

	JLabel lblOption;
	JLabel lblThang;
	JLabel lblNam;
	JLabel lblTu, lblDen;

	JPanel pnlForm;

	JComboBox<String> boxOption;
	JComboBox<Integer> boxThang1;
	JComboBox<Integer> boxNam1;
	JComboBox<Integer> boxThang2;
	JComboBox<Integer> boxNam2;

	JButton btnExcel;
	JButton btnChon;
	
	String option = "1";

	final String LUA_CHON = "Chọn";
	final String THANG = "Tháng";
	final String NAM = "Năm";

	public ThongKeGUI() {
		this.setLayout(new GridBagLayout());

		initComponents();
	}

	private void initComponents() {
		initPnlForm();
		initForm();
		initTableThongKe();

		initButton();
		btnThongKeClicked();
		boxOptionListener();
	}

	private void initPnlForm() {
		pnlForm = new JPanel();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		this.add(pnlForm, cons);
	}

	private void initForm() {
		lblOption = new JLabel(LUA_CHON);
		lblOption.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		ArrayList<String> lstOption = new ArrayList<String>();
		lstOption.add("1. Lương NV theo tháng");
		lstOption.add("2. Quá trình công tác");
		lstOption.add("3. Hello 3");

		String[] arrayOption = lstOption.toArray(new String[0]);

		boxOption = new JComboBox<String>(arrayOption);
		boxOption.setFont(myProps.DEFAULT_FONT_SMALL);

		lblThang = new JLabel(THANG);
		lblThang.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		lblNam = new JLabel(NAM);
		lblNam.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		
		lblTu = new JLabel("Từ");
		lblTu.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		lblDen = new JLabel("Đến");
		lblDen.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		// combo box tháng
		ArrayList<Integer> lstThang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			lstThang.add(i);
		}

		Integer[] arrayThang = lstThang.toArray(new Integer[0]);

		boxThang1 = new JComboBox<Integer>(arrayThang);
		boxThang1.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		ArrayList<Integer> lstNam = new ArrayList<Integer>();
		for (int i = 2017; i <= 2019; i++) {
			lstNam.add(i);
		}

		Integer[] arrayNam = lstNam.toArray(new Integer[0]);

		boxNam1 = new JComboBox<Integer>(arrayNam);
		boxNam1.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box tháng
		boxThang2 = new JComboBox<Integer>(arrayThang);
		boxThang2.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		boxNam2 = new JComboBox<Integer>(arrayNam);
		boxNam2.setFont(myProps.DEFAULT_FONT_SMALL);

		pnlForm.add(lblOption);
		pnlForm.add(boxOption);
		
		pnlForm.add(lblTu);
		lblTu.setVisible(false);
		
		pnlForm.add(lblThang);
		pnlForm.add(boxThang1);

		pnlForm.add(lblNam);
		pnlForm.add(boxNam1);
		
		pnlForm.add(lblDen);
		lblDen.setVisible(false);
		
		pnlForm.add(boxThang2);
		pnlForm.add(boxNam2);
		boxThang2.setVisible(false);
		boxNam2.setVisible(false);
	}

	private void initButton() {
		btnChon = new JButton("Thống kê");
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#4caf50"));
		btnChon.setForeground(Color.WHITE);
		btnChon.setFont(myProps.DEFAULT_FONT_SMALL);

		btnExcel = new JButton("Xuất excel");
		myProps.BtnFlat(btnExcel);
		btnExcel.setBackground(Color.decode("#e0e0e0"));
		btnExcel.setForeground(Color.BLACK);
		btnExcel.setFont(myProps.DEFAULT_FONT_SMALL);

		pnlForm.add(btnChon);
		pnlForm.add(btnExcel);
	}

	private void initTableThongKe() {
		tblTK = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
//		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// không cho phép di chuyển vị trí columns
		tblTK.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
//		tblChamCong.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
//		tblChamCong.setAutoCreateRowSorter(true);

		tblTK.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblTK, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 6, 5, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(scroll, cons);
	}

	private void btnThongKeClicked() {
		ActionListener btnChonClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String option = (String) boxOption.getSelectedItem();
				option = option.substring(0, 1); // lấy số thống kê
//				System.out.print(option);
			}
		};

		btnChon.addActionListener(btnChonClick);
	}
	
	private void boxOptionListener() {
		ActionListener boxOptionSelect = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = (String) boxOption.getSelectedItem();
				option = option.substring(0, 1); // lấy số thống kê
//				System.out.print(option);
				switch (option) {
				case "1":
					lblTu.setVisible(false);
					lblDen.setVisible(false);
					boxNam2.setVisible(false);
					boxThang2.setVisible(false);
					
					lblThang.setVisible(true);
					lblNam.setVisible(true);
					boxThang1.setVisible(true);
					boxNam1.setVisible(true);
					break;
				case "2":
					lblThang.setVisible(false);
					lblNam.setVisible(false);
					
					lblTu.setVisible(true);
					lblDen.setVisible(true);
					boxNam2.setVisible(true);
					boxThang2.setVisible(true);
					boxThang1.setVisible(true);
					boxNam1.setVisible(true);
					break;
				case "3":
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + option);
				}
			}
		};
		
		boxOption.addActionListener(boxOptionSelect);
	}
}
