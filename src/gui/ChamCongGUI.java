package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChamCongGUI extends JPanel {
	MyProps myProps = new MyProps();
	JTable tblChamCong;

	JPanel pnlForm;

	JLabel lblNV;
	JLabel lblThang;
	JLabel lblNam;
	
	JTextField txtTenNV;

	JButton btnChon;
	JButton btnChonNV;

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
		btnChonNVClicked();
	}

	private void initPanelForm() {
		pnlForm = new JPanel();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		this.add(pnlForm, cons);
	}

	private void initForm() {
		lblNV = new JLabel(NHAN_VIEN);
		lblNV.setFont(myProps.DEFAULT_FONT_SMALL);

		txtTenNV = new JTextField(15);
		txtTenNV.setEditable(false);
		txtTenNV.setFont(myProps.DEFAULT_FONT_SMALL);

		lblThang = new JLabel(THANG);
		lblThang.setFont(myProps.DEFAULT_FONT_SMALL);

		lblNam = new JLabel(NAM);
		lblNam.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box tháng
		ArrayList<Integer> lstThang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			lstThang.add(i);
		}

		Integer[] arrayThang = lstThang.toArray(new Integer[0]);

		JComboBox<Integer> boxThang = new JComboBox<Integer>(arrayThang);
		boxThang.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		ArrayList<Integer> lstNam = new ArrayList<Integer>();
		for (int i = 2017; i <= 2019; i++) {
			lstNam.add(i);
		}

		Integer[] arrayNam = lstNam.toArray(new Integer[0]);

		JComboBox<Integer> boxNam = new JComboBox<Integer>(arrayNam);
		boxNam.setFont(myProps.DEFAULT_FONT_SMALL);
		
		// button chọn
		btnChon = new JButton("Chọn");
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#4caf50"));
		btnChon.setForeground(Color.WHITE);
		
		// button chọn nv
		btnChonNV = new JButton("...");
		myProps.BtnFlat(btnChonNV);
		btnChonNV.setBackground(Color.decode("#e0e0e0"));
		btnChonNV.setForeground(Color.BLACK);

		pnlForm.add(lblNV);
		pnlForm.add(txtTenNV);
		pnlForm.add(btnChonNV);

		pnlForm.add(lblThang);
		pnlForm.add(boxThang);

		pnlForm.add(lblNam);
		pnlForm.add(boxNam);
		
		pnlForm.add(btnChon);
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
		JScrollPane scroll = new JScrollPane(tblChamCong, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
	
	private void btnChonNVClicked() {
		JFrame nvFrame = new JFrame("Chọn nhân viên");
		nvFrame.setLayout(new GridLayout(1, 1));
		nvFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		PhongBanGUI pbGUI = new PhongBanGUI();
		
		pbGUI.btnNvThem.setVisible(false);
		pbGUI.btnNvXoa.setVisible(false);
		pbGUI.btnNvTp.setVisible(false);
		
		pbGUI.btnPbThem.setVisible(false);
		pbGUI.btnPbXoa.setVisible(false);
		pbGUI.btnPbSua.setVisible(false);
		
		pbGUI.txtPbTenPhong.setEditable(false);
		
		// button chọn nv của frame
		JButton btnChonNV_temp = new JButton("Chọn");
		myProps.BtnFlat(btnChonNV_temp);
		btnChonNV_temp.setBackground(Color.decode("#e0e0e0"));
		btnChonNV_temp.setForeground(Color.BLACK);
		btnChonNV_temp.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 8, 4, 1, false, true);
		pbGUI.pnlNV.add(btnChonNV_temp, cons);
		
		// size và position
		nvFrame.setSize(ContentPanel.WIDTH, ContentPanel.HEIGHT);
		nvFrame.setResizable(false);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		int xLocation = (screenWidth - ContentPanel.WIDTH) / 2;
		int yLocation = ((screenHeight - ContentPanel.HEIGHT) / 2 - 25);
		nvFrame.setLocation(xLocation, yLocation);
		
		nvFrame.add(pbGUI);
		
		btnChonNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nvFrame.setVisible(true);
			}
		});
	}
}
