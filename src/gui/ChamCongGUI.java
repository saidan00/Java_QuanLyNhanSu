package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChamCongGUI extends JPanel {
	MyProps myProps = new MyProps();
	JTable tblChamCong;

	JPanel pnlForm;

	JLabel lblNV;
	JLabel lblTenNV;
	JLabel lblThang;
	JLabel lblNam;
	
	JButton btnChon;

	final String NHAN_VIEN = "Nhân viên";
	final String THANG = "Tháng";
	final String NAM = "Năm";

	// có 2 combo box cho tháng, năm
	public ChamCongGUI() {
		this.setLayout(new GridBagLayout());

		initComponents();
	}

	private void initComponents() {
		initPanelForm();
		initForm();
		initTableChamCong();
	}

	private void initPanelForm() {
		pnlForm = new JPanel();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		this.add(pnlForm, cons);
	}

	private void initForm() {
		lblNV = new JLabel(NHAN_VIEN);
		lblNV.setFont(myProps.DEFAULT_FONT_SMALL);
		
		lblTenNV = new JLabel();
		lblTenNV.setFont(myProps.DEFAULT_FONT_SMALL);
		
		lblThang = new JLabel(THANG);
		lblThang.setFont(myProps.DEFAULT_FONT_SMALL);
		
		lblNam = new JLabel(NAM);
		lblNam.setFont(myProps.DEFAULT_FONT_SMALL);
		
		ArrayList<Integer> lstThang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			lstThang.add(i);
		}
		
	    Integer[] arrayThang = lstThang.toArray(new Integer[0]);
		
		JComboBox<Integer> boxThang = new JComboBox<Integer>(arrayThang);
		boxThang.setFont(myProps.DEFAULT_FONT_SMALL);

		pnlForm.add(lblNV);
		pnlForm.add(lblTenNV);
		
		pnlForm.add(lblThang);
		pnlForm.add(boxThang);
		
		pnlForm.add(lblNam);
	}

	private void initTableChamCong() {
		tblChamCong = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
//		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// đọc dữ liệu
		setModelTable();

		// không cho phép di chuyển vị trí columns
		tblChamCong.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblChamCong.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblChamCong.setAutoCreateRowSorter(true);
		
		tblChamCong.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblChamCong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 6, 5, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(scroll, cons);
	}

	private void setModelTable() {
		// table header
		Vector<String> header = new Vector<String>();
		header.add(NHAN_VIEN);
		for (int i = 1; i <= 31; i++) {
			header.add(String.valueOf(i));
		}

		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		
		tblChamCong.setModel(dtm);
	}

}
