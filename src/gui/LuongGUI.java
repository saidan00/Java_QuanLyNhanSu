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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bus.LuongBUS;
import bus.NhanVienBUS;
import dto.LuongDTO;
import dto.NhanVienDTO;

public class LuongGUI extends JPanel {
	LuongBUS luongBUS = new LuongBUS();
	NhanVienBUS nvBUS = new NhanVienBUS();
	MyProps myProps = new MyProps();

	JPanel pnlLuong;
	JPanel pnlNV;

	JTable tblLuong;
	JTable tblNV;

	final String lblLuong = "LƯƠNG";
	final String lblNV = "NHÂN VIÊN";

	JLabel lblLgMaLuong, lblLgLuongCB;
	JLabel lblNvMaNV, lblNvHoNV, lblNvTenNV, lblNvChucVu;

	JTextField txtLgMaLuong, txtLgLuongCB;
	JButton btnLgThem, btnLgSua, btnLgXoa;
	JButton btnNvDoiLuong;

	final String MA_LUONG = "Mã Lương";
	final String LUONG_CB = "Lương cơ bản";

	final String MA_NV = "Mã NV";
	final String HO_NV = "Họ NV";
	final String TEN_NV = "Tên NV";
	final String CHUC_VU = "Chức Vụ";

	public LuongGUI() {
		initComponents();
	}

	// khởi tạo các component
	private void initComponents() {
		this.setLayout(new GridLayout(1, 2));
//		this.setBackground(Color.PINK);

		initPanelLuong();
		initFormLuong();
		initTableLuong();

		// button phong ban
		initButtonLuong();
		btnLgThemClicked();
		btnLgXoaClicked();
		btnLgSuaClicked();

		initPanelNhanVien();
		initTableNhanVien();
		initButtonNhanVien();

		// button nhan vien
		btnNvDoiLuongClicked();

		tblLgMouseListener();
	}

	private void initPanelLuong() {
		pnlLuong = new JPanel();
		pnlLuong.setLayout(new GridBagLayout());

		TitledBorder border = new TitledBorder(lblLuong);
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);

		pnlLuong.setBorder(border);

		this.add(pnlLuong);
	}

	private void initFormLuong() {
		GridBagConstraints cons = new GridBagConstraints();
		// mã nhân viên
		lblLgMaLuong = new JLabel(MA_LUONG);
		lblLgMaLuong.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		pnlLuong.add(lblLgMaLuong, cons);

		txtLgMaLuong = myProps.RoundedTextField(5);
		txtLgMaLuong.setEditable(false); // không cho sửa
		cons = myProps.MyGridBagConstraints(2, 1, 1, 1, true, true);
		pnlLuong.add(txtLgMaLuong, cons);

		// họ nhân viên
		lblLgLuongCB = new JLabel(LUONG_CB);
		lblLgLuongCB.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(3, 1, 1, 1, true, true);
		pnlLuong.add(lblLgLuongCB, cons);

		txtLgLuongCB = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlLuong.add(txtLgLuongCB, cons);
	}

	private void initTableLuong() {
		tblLuong = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		// không cho phép di chuyển vị trí columns
		tblLuong.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblLuong.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblLuong.setAutoCreateRowSorter(true);

		setModelTableLg();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 4, 5, true, true);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblLuong);

		pnlLuong.add(scroll, cons);
	}

	private void setModelTableLg() {
		// table header
		Vector<String> header = new Vector<String>();
		header.add(MA_LUONG);
		header.add(LUONG_CB);

		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				default:
					return Integer.class;
				}
			}
		};

		ArrayList<LuongDTO> lstLg = new ArrayList<LuongDTO>();
		lstLg = luongBUS.LuongAll();

		LuongDTO lg = new LuongDTO();

		for (int i = 0; i < lstLg.size(); i++) {
			lg = lstLg.get(i);
			Object[] row = { lg.getMaLuong(), lg.getLuongCB() };
			dtm.addRow(row);
		}

		tblLuong.setModel(dtm);
	}

	private void initButtonLuong() {
		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new GridBagLayout());

		btnLgThem = new JButton("Thêm");
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 2, 1, true, true);
		pnlBtn.add(btnLgThem, cons);
		myProps.BtnFlat(btnLgThem);
		btnLgThem.setBackground(Color.decode("#4caf50"));
		btnLgThem.setForeground(Color.WHITE);
		btnLgThem.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnLgSua = new JButton("Sửa");
		cons = myProps.MyGridBagConstraints(3, 1, 2, 1, true, true);
		pnlBtn.add(btnLgSua, cons);
		myProps.BtnFlat(btnLgSua);
		btnLgSua.setBackground(Color.decode("#e0e0e0"));
		btnLgSua.setForeground(Color.BLACK);
		btnLgSua.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		btnLgSua.setText("Sửa");

		btnLgXoa = new JButton("Xóa");
		cons = myProps.MyGridBagConstraints(5, 1, 2, 1, true, true);
		pnlBtn.add(btnLgXoa, cons);
		myProps.BtnFlat(btnLgXoa);
		btnLgXoa.setBackground(Color.decode("#e53935"));
		btnLgXoa.setForeground(Color.WHITE);
		btnLgXoa.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlLuong.add(pnlBtn, cons);
	}

	private void initPanelNhanVien() {
		pnlNV = new JPanel();
		pnlNV.setLayout(new GridBagLayout());

		TitledBorder border = new TitledBorder(lblNV);
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);
		
		JTextField tempTxt = myProps.RoundedTextField(5);
		tempTxt.setVisible(false);
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(1, 1));
		tempPanel.add(tempTxt);
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 0, 1, 1, true, true);
		pnlNV.add(tempPanel, cons);
		
		pnlNV.setBorder(border);

		this.add(pnlNV);
	}

	private void initTableNhanVien() {
		tblNV = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		// đọc dữ liệu
		ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();
		setModelTableNV(lstNV);

		// không cho phép di chuyển vị trí columns
		tblNV.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblNV.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblNV.setAutoCreateRowSorter(true);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 4, 5, true, true);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblNV);

		pnlNV.add(scroll, cons);
	}

	private void setModelTableNV(ArrayList<NhanVienDTO> lstNV) {
		// table header
		Vector<String> header = new Vector<String>();
		header.add(MA_NV);
		header.add(TEN_NV);

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

		NhanVienDTO nv = new NhanVienDTO();

		for (int i = 0; i < lstNV.size(); i++) {
			nv = lstNV.get(i);
			Object[] row = { nv.getMaNV(), nv.getHoNV() + " " + nv.getTenNV() };
			dtm.addRow(row);
		}

		tblNV.setModel(dtm);
	}

	private void tblLgMouseListener() {
		tblLuong.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblLuong.getSelectedRow();
				int maLg = (int) tblLuong.getValueAt(row, 0);

				txtLgMaLuong.setText(tblLuong.getValueAt(row, 0).toString());
				txtLgLuongCB.setText(tblLuong.getValueAt(row, 1).toString());

				ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoLuong(maLg);
				setModelTableNV(lstNV);
			}
		});
	}

	private void btnLgThemClicked() {
		btnLgThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String luongCb = String.valueOf(txtLgLuongCB.getText());

				if (luongCb.isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lương cơ bản");
					txtLgLuongCB.requestFocus();
				} else {
					try {
						LuongDTO lg = new LuongDTO();
						lg.setLuongCB(Integer.valueOf(luongCb));
						
						luongBUS.LuongAdd(lg);

						JOptionPane.showMessageDialog(null, "Thêm thành công");
						setModelTableLg();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
						txtLgLuongCB.requestFocus();
					}
				}
			}
		});
	}

	private void btnLgXoaClicked() {
		btnLgXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtLgMaLuong.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn mục cần xóa");
				} else {
					int maLg = Integer.valueOf(txtLgMaLuong.getText());

					luongBUS.LuongDelete(maLg);

					setModelTableLg();
				}
			}
		});
	}
	
	private void btnLgSuaClicked() {
		btnLgSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String luongCb = String.valueOf(txtLgLuongCB.getText());

				if (luongCb.isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập lương cơ bản");
					txtLgLuongCB.requestFocus();
				} else {
					try {
						LuongDTO lg = new LuongDTO();
						lg.setMaLuong(Integer.valueOf(txtLgMaLuong.getText()));
						lg.setLuongCB(Integer.valueOf(luongCb));
						
						luongBUS.LuongEdit(lg);

						JOptionPane.showMessageDialog(null, "Sửa thành công");
						setModelTableLg();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
						setModelTableLg();
					}
				}
			}
		});
	}

	private void initButtonNhanVien() {
		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new GridBagLayout());

		btnNvDoiLuong = new JButton("Đổi mức lương");
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 2, 1, true, true);
		pnlBtn.add(btnNvDoiLuong, cons);
		myProps.BtnFlat(btnNvDoiLuong);
		btnNvDoiLuong.setBackground(Color.decode("#e53935"));
		btnNvDoiLuong.setForeground(Color.WHITE);
		btnNvDoiLuong.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlNV.add(pnlBtn, cons);
	}

	// đổi lương
	private void btnNvDoiLuongClicked() {
		btnNvDoiLuong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNV.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				} else {
					int maLg = Integer.valueOf(txtLgMaLuong.getText());

					int maNv = (int) tblNV.getValueAt(row, 0);

					NhanVienDTO nvDto = nvBUS.NhanVienGet(maNv);
					nvDto.setMaLuong(maLg);

					ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoLuong(maLg);
					setModelTableNV(lstNV);
					
					FrameDoiLuong(maNv);
				}
			}
		});
	}

	private void FrameDoiLuong(int maNv) {
//		int maLg = Integer.valueOf(txtLgMaLuong.getText());
		JFrame lgFrame = new JFrame("Chọn lương");

		lgFrame.setSize(MyProps.DEFAULT_WIDTH / 2, MyProps.DEFAULT_HEIGHT / 2);
		lgFrame.setResizable(false);
		lgFrame.setVisible(true);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		int xLocation = (screenWidth - MyProps.DEFAULT_WIDTH / 2) / 2;
		int yLocation = ((screenHeight - MyProps.DEFAULT_HEIGHT / 2) / 2 - 25);
		lgFrame.setLocation(xLocation, yLocation);

		lgFrame.setLayout(new GridLayout(1, 1));
		lgFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pnlTblLgTemp = new JPanel();
		pnlTblLgTemp.setLayout(new GridBagLayout());

		lgFrame.add(pnlTblLgTemp);

		JTable tblLgTemp = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}

			@Override
			public Dimension getPreferredScrollableViewportSize() {
				return new Dimension(100, 100);
			}
		};

		// không cho phép di chuyển vị trí columns
		tblLgTemp.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblLgTemp.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblLgTemp.setAutoCreateRowSorter(true);

		// đọc dữ liệu
		ArrayList<LuongDTO> lstLg = luongBUS.LuongAll();

		// table header
		Vector<String> header = new Vector<String>();
		header.add(MA_LUONG);
		header.add(LUONG_CB);

		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				default:
					return Integer.class;
				}
			}
		};

		LuongDTO lg = new LuongDTO();

		for (int i = 0; i < lstLg.size(); i++) {
			lg = lstLg.get(i);
			Object[] row = { lg.getMaLuong(), lg.getLuongCB() };
			dtm.addRow(row);
		}

		tblLgTemp.setModel(dtm);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblLgTemp);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;

		pnlTblLgTemp.add(scroll, cons);

		JButton btnChon = new JButton("Chọn");
		cons = myProps.MyGridBagConstraints(1, 2, 1, 1, false, true);
		pnlTblLgTemp.add(btnChon, cons);
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#e0e0e0"));
		btnChon.setForeground(Color.BLACK);
		btnChon.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblLgTemp.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn lương");
				} else {
					int maLg = (int) tblLgTemp.getValueAt(row, 0);

					NhanVienDTO nvDto = nvBUS.NhanVienGet(maNv);
					
					nvDto.setMaLuong(maLg);
					
					nvBUS.NhanVienEdit(nvDto);

					JOptionPane.showMessageDialog(null, "Đổi thành công");

					ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoLuong(Integer.valueOf(txtLgMaLuong.getText()));
					setModelTableNV(lstNV);

					lgFrame.dispatchEvent(new WindowEvent(lgFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
	}
}
