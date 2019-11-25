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
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.NhanVienBUS;
import bus.PhongBanBUS;
import dto.NhanVienDTO;
import dto.PhongBanDTO;

public class ChamCongGUI extends JPanel {
	NhanVienBUS nvBUS = new NhanVienBUS();
	PhongBanBUS pbBUS = new PhongBanBUS();

	MyProps myProps = new MyProps();
	JTable tblChamCong;

	JPanel pnlForm;

	JLabel lblPB;
	JLabel lblThang;
	JLabel lblNam;

	JTextField txtTenPB;

	JButton btnChon;
	JButton btnChonNV;
	
	JComboBox<Integer> boxThang;
	JComboBox<Integer> boxNam;

	int maPb, thang = 1, nam = 2017;

	final String PHONG_BAN = "Phòng ban";
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
		btnChonNvClicked();
		btnChonClicked();
	}

	private void initPanelForm() {
		pnlForm = new JPanel();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		this.add(pnlForm, cons);
	}

	private void initForm() {
		lblPB = new JLabel(PHONG_BAN);
		lblPB.setFont(new Font("Arial Nova", Font.BOLD, 12));

		txtTenPB = new JTextField(15);
		txtTenPB.setEditable(false);
		txtTenPB.setFont(myProps.DEFAULT_FONT_SMALL);

		lblThang = new JLabel(THANG);
		lblThang.setFont(new Font("Arial Nova", Font.BOLD, 12));

		lblNam = new JLabel(NAM);
		lblNam.setFont(new Font("Arial Nova", Font.BOLD, 12));

		// combo box tháng
		ArrayList<Integer> lstThang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			lstThang.add(i);
		}

		Integer[] arrayThang = lstThang.toArray(new Integer[0]);

		boxThang = new JComboBox<Integer>(arrayThang);
		boxThang.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		ArrayList<Integer> lstNam = new ArrayList<Integer>();
		for (int i = 2017; i <= 2019; i++) {
			lstNam.add(i);
		}

		Integer[] arrayNam = lstNam.toArray(new Integer[0]);

		boxNam = new JComboBox<Integer>(arrayNam);
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

		pnlForm.add(lblPB);
		pnlForm.add(txtTenPB);
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
	
	private void btnChonNvClicked() {
		btnChonNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FramePhongBan();
			}
		});
	}

	// button dấu 3 chấm
	private void FramePhongBan() {
		JFrame pbFrame = new JFrame("Chọn phòng ban");

		pbFrame.setSize(MyProps.DEFAULT_WIDTH / 2, MyProps.DEFAULT_HEIGHT / 2);
		pbFrame.setResizable(false);
		pbFrame.setVisible(true);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		int xLocation = (screenWidth - MyProps.DEFAULT_WIDTH / 2) / 2;
		int yLocation = ((screenHeight - MyProps.DEFAULT_HEIGHT / 2) / 2 - 25);
		pbFrame.setLocation(xLocation, yLocation);

		pbFrame.setLayout(new GridLayout(1, 1));
		pbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pnlTblPbTemp = new JPanel();
		pnlTblPbTemp.setLayout(new GridBagLayout());

		pbFrame.add(pnlTblPbTemp);

		JTable tblNvTemp = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}

			@Override
			public Dimension getPreferredScrollableViewportSize() {
				return new Dimension(100, 100);
			}
		};

		// không cho phép di chuyển vị trí columns
		tblNvTemp.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblNvTemp.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblNvTemp.setAutoCreateRowSorter(true);

		// đọc dữ liệu
		ArrayList<PhongBanDTO> lstPB = pbBUS.PhongBanAll();

		// table header
		Vector<String> header = new Vector<String>();
		header.add("Mã phòng");
		header.add("Tên phòng");

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

		PhongBanDTO pb = new PhongBanDTO();

		for (int i = 0; i < lstPB.size(); i++) {
			pb = lstPB.get(i);
			Object[] row = { pb.getMaPhong(), pb.getTenPhong() };
			dtm.addRow(row);
		}

		tblNvTemp.setModel(dtm);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblNvTemp);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;

		pnlTblPbTemp.add(scroll, cons);

		JButton btnChon = new JButton("Chọn");
		cons = myProps.MyGridBagConstraints(1, 2, 1, 1, false, true);
		pnlTblPbTemp.add(btnChon, cons);
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#e0e0e0"));
		btnChon.setForeground(Color.BLACK);
		btnChon.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNvTemp.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng ban");
				} else {
					maPb = (int) tblNvTemp.getValueAt(row, 0);
					PhongBanDTO pb = new PhongBanDTO();

					pbFrame.dispatchEvent(new WindowEvent(pbFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
	}

	private void setModelTableChamCong() {
		ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();

		lstNV = nvBUS.NhanVienTheoPhongBan(maPb);

		int days = 0;

		switch (thang) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 2:
			days = (nam % 4 == 0) ? 29 : 28;
			break;
		default:
			days = 30;
			break;
		}

		// table header
		Vector<String> header = new Vector<String>();
		header.add(PHONG_BAN);
		for (int i = 1; i <= days; i++) {
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

	private void btnChonClicked() {
		btnChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thang = (int) boxThang.getSelectedItem();
				nam = (int) boxNam.getSelectedItem();
				
				setModelTableChamCong();
			}
		});
	}
}
