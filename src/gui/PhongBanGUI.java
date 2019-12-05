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

import bus.HopDongLaoDongBUS;
import bus.NhanVienBUS;
import bus.PhongBanBUS;
import dto.HopDongLaoDongDTO;
import dto.NhanVienDTO;
import dto.PhongBanDTO;

public class PhongBanGUI extends JPanel {
	PhongBanBUS pbBUS = new PhongBanBUS();
	NhanVienBUS nvBUS = new NhanVienBUS();
	HopDongLaoDongBUS hdBUS = new HopDongLaoDongBUS();
	MyProps myProps = new MyProps();

	JPanel pnlPB;
	JPanel pnlNV;

	JTable tblPB;
	JTable tblNV;

	final String lblPB = "PHÒNG BAN";
	final String lblNV = "NHÂN VIÊN";

	JLabel lblPbMaPhong, lblPbTenPhong;
	JLabel lblNvMaNV, lblNvHoNV, lblNvTenNV, lblNvChucVu;

	JTextField txtPbMaPhong, txtPbTenPhong;
	JButton btnPbThem, btnPbSua, btnPbXoa;
	JButton btnNvThem, btnNvTp, btnNvXoa;

	final String MA_PHONG = "Mã Phòng";
	final String TEN_PHONG = "Tên Phòng";

	final String MA_NV = "Mã NV";
	final String HO_NV = "Họ NV";
	final String TEN_NV = "Tên NV";
	final String CHUC_VU = "Chức Vụ";

	public PhongBanGUI() {
		initComponents();
	}

	// khởi tạo các component
	private void initComponents() {
		this.setLayout(new GridLayout(1, 2));
//		this.setBackground(Color.PINK);

		initPanelPhongBan();
		initFormPhongBan();
		initTablePhongBan();

		// button phong ban
		initButtonPhongBan();
		btnPbThemClicked();
		btnPbXoaClicked();
		btnPbSuaClicked();

		initPanelNhanVien();
		initTableNhanVien();
		initButtonNhanVien();

		// button nhan vien
		btnNvTpClicked();
		btnNvXoaClicked();
		btnNvThemClicked();

		tblPBMouseListener();
	}

	private void initPanelPhongBan() {
		pnlPB = new JPanel();
		pnlPB.setLayout(new GridBagLayout());

		TitledBorder border = new TitledBorder(lblPB);
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);

		pnlPB.setBorder(border);

		this.add(pnlPB);
	}

	private void initFormPhongBan() {
		GridBagConstraints cons = new GridBagConstraints();
		// mã nhân viên
		lblPbMaPhong = new JLabel(MA_PHONG);
		lblPbMaPhong.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		pnlPB.add(lblPbMaPhong, cons);

		txtPbMaPhong = myProps.RoundedTextField(5);
		txtPbMaPhong.setEditable(false); // không cho sửa
		cons = myProps.MyGridBagConstraints(2, 1, 1, 1, true, true);
		pnlPB.add(txtPbMaPhong, cons);

		// họ nhân viên
		lblPbTenPhong = new JLabel(TEN_PHONG);
		lblPbTenPhong.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(3, 1, 1, 1, true, true);
		pnlPB.add(lblPbTenPhong, cons);

		txtPbTenPhong = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlPB.add(txtPbTenPhong, cons);
	}

	private void initTablePhongBan() {
		tblPB = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

		// không cho phép di chuyển vị trí columns
		tblPB.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblPB.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblPB.setAutoCreateRowSorter(true);

		setModelTablePB();

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 4, 5, true, true);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblPB);

		pnlPB.add(scroll, cons);
	}

	private void setModelTablePB() {
		// table header
		Vector<String> header = new Vector<String>();
		header.add(MA_PHONG);
		header.add(TEN_PHONG);

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

		ArrayList<PhongBanDTO> lstPB = new ArrayList<PhongBanDTO>();
		lstPB = pbBUS.PhongBanAll();

		PhongBanDTO pb = new PhongBanDTO();

		for (int i = 0; i < lstPB.size(); i++) {
			pb = lstPB.get(i);
			Object[] row = { pb.getMaPhong(), pb.getTenPhong() };
			dtm.addRow(row);
		}

		tblPB.setModel(dtm);
	}

	private void initButtonPhongBan() {
		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new GridBagLayout());

		btnPbThem = new JButton("Thêm");
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 2, 1, true, true);
		pnlBtn.add(btnPbThem, cons);
		myProps.BtnFlat(btnPbThem);
		btnPbThem.setBackground(Color.decode("#4caf50"));
		btnPbThem.setForeground(Color.WHITE);
		btnPbThem.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnPbSua = new JButton("Sửa");
		cons = myProps.MyGridBagConstraints(3, 1, 2, 1, true, true);
		pnlBtn.add(btnPbSua, cons);
		myProps.BtnFlat(btnPbSua);
		btnPbSua.setBackground(Color.decode("#e0e0e0"));
		btnPbSua.setForeground(Color.BLACK);
		btnPbSua.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		btnPbSua.setText("Sửa");

		btnPbXoa = new JButton("Xóa");
		cons = myProps.MyGridBagConstraints(5, 1, 2, 1, true, true);
		pnlBtn.add(btnPbXoa, cons);
		myProps.BtnFlat(btnPbXoa);
		btnPbXoa.setBackground(Color.decode("#e53935"));
		btnPbXoa.setForeground(Color.WHITE);
		btnPbXoa.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlPB.add(pnlBtn, cons);
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
		header.add(CHUC_VU);

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
			Object[] row = { nv.getMaNV(), nv.getHoNV() + " " + nv.getTenNV(), hdBUS.ChucVuCuaNhanVien(nv.getMaNV()) };
			dtm.addRow(row);
		}

		tblNV.setModel(dtm);
	}

	private void tblPBMouseListener() {
		tblPB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblPB.getSelectedRow();
				int maPb = (int) tblPB.getValueAt(row, 0);

				txtPbMaPhong.setText(tblPB.getValueAt(row, 0).toString());
				txtPbTenPhong.setText(tblPB.getValueAt(row, 1).toString());

				ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoPhongBan(maPb);
				setModelTableNV(lstNV);
			}
		});
	}

	private void btnPbThemClicked() {
		btnPbThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhongBanDTO pb = new PhongBanDTO();

				pb.setTenPhong(String.valueOf(txtPbTenPhong.getText()));
				pb.setMaTruongPhong(null);

				if (pb.getTenPhong().isBlank()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng");
					txtPbTenPhong.requestFocus();
				} else {
					pbBUS.PhongBanAdd(pb);

					JOptionPane.showMessageDialog(null, "Thêm thành công");
				}

				setModelTablePB();
			}
		});
	}

	private void btnPbXoaClicked() {
		btnPbXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtPbMaPhong.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng ban");
				} else {
					int maPb = Integer.valueOf(txtPbMaPhong.getText());

					pbBUS.PhongBanDelete(maPb);

					setModelTablePB();
				}
			}
		});
	}
	
	private void btnPbSuaClicked() {
		btnPbSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtPbMaPhong.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng ban");
				} else {
					int maPb = Integer.valueOf(txtPbMaPhong.getText());
					String tenPb = String.valueOf(txtPbTenPhong.getText());

					if (tenPb.isBlank()) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng");
						txtPbTenPhong.requestFocus();
					} else {
						pbBUS.PhongBanEdit(maPb, tenPb);

						JOptionPane.showMessageDialog(null, "Sửa thành công");
					}

					setModelTablePB();
				}
			}
		});
	}

	private void initButtonNhanVien() {
		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new GridBagLayout());

		btnNvThem = new JButton("Thêm");
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 2, 1, true, true);
		pnlBtn.add(btnNvThem, cons);
		myProps.BtnFlat(btnNvThem);
		btnNvThem.setBackground(Color.decode("#4caf50"));
		btnNvThem.setForeground(Color.WHITE);
		btnNvThem.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnNvTp = new JButton("Gán trưởng phòng");
		cons = myProps.MyGridBagConstraints(3, 1, 2, 1, true, true);
		pnlBtn.add(btnNvTp, cons);
		myProps.BtnFlat(btnNvTp);
		btnNvTp.setBackground(Color.decode("#e0e0e0"));
		btnNvTp.setForeground(Color.BLACK);
		btnNvTp.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnNvXoa = new JButton("Xóa khỏi phòng");
		cons = myProps.MyGridBagConstraints(5, 1, 2, 1, true, true);
		pnlBtn.add(btnNvXoa, cons);
		myProps.BtnFlat(btnNvXoa);
		btnNvXoa.setBackground(Color.decode("#e53935"));
		btnNvXoa.setForeground(Color.WHITE);
		btnNvXoa.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlNV.add(pnlBtn, cons);
	}

	private void btnNvTpClicked() {
		btnNvTp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNV.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				} else {
					int maPb = Integer.valueOf(txtPbMaPhong.getText());

					int maNv = (int) tblNV.getValueAt(row, 0);

					HopDongLaoDongDTO hd = hdBUS.HopDongMoiNhat(maNv);

					hdBUS.CapNhatTruongPhong(maPb, maNv, hd);

					JOptionPane.showMessageDialog(null, "Gán thành công");

					ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoPhongBan(maPb);
					setModelTableNV(lstNV);
				}
			}
		});
	}

	// xóa nv khỏi phòng ban
	private void btnNvXoaClicked() {
		btnNvXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNV.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				} else {
					int maPb = Integer.valueOf(txtPbMaPhong.getText());

					int maNv = (int) tblNV.getValueAt(row, 0);
					String chucVu = (String) tblNV.getValueAt(row, 2);

					if (chucVu.equals("Trưởng Phòng")) {
						JOptionPane.showMessageDialog(null, "Không thể xóa trưởng phòng");
					} else {
						hdBUS.XoaNhanVienKhoiPhongBan(maNv, maPb);
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					}

					ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoPhongBan(maPb);
					setModelTableNV(lstNV);
				}
			}
		});
	}

	// thêm nv vào phòng ban
	private void btnNvThemClicked() {
		btnNvThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblPB.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng ban");
				} else {
					FrameThemNhanVien();
				}
			}
		});
	}

	private void FrameThemNhanVien() {
		int maPb = Integer.valueOf(txtPbMaPhong.getText());
		JFrame nvFrame = new JFrame("Chọn nhân viên");

		nvFrame.setSize(MyProps.DEFAULT_WIDTH / 2, MyProps.DEFAULT_HEIGHT / 2);
		nvFrame.setResizable(false);
		nvFrame.setVisible(true);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		int xLocation = (screenWidth - MyProps.DEFAULT_WIDTH / 2) / 2;
		int yLocation = ((screenHeight - MyProps.DEFAULT_HEIGHT / 2) / 2 - 25);
		nvFrame.setLocation(xLocation, yLocation);

		nvFrame.setLayout(new GridLayout(1, 1));
		nvFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pnlTblNvTemp = new JPanel();
		pnlTblNvTemp.setLayout(new GridBagLayout());

		nvFrame.add(pnlTblNvTemp);

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
		ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoPhongBan(null);

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

		tblNvTemp.setModel(dtm);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblNvTemp);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;

		pnlTblNvTemp.add(scroll, cons);

		JButton btnChon = new JButton("Chọn");
		cons = myProps.MyGridBagConstraints(1, 2, 1, 1, false, true);
		pnlTblNvTemp.add(btnChon, cons);
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#e0e0e0"));
		btnChon.setForeground(Color.BLACK);
		btnChon.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNvTemp.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				} else {
					int maNv = (int) tblNvTemp.getValueAt(row, 0);

					HopDongLaoDongDTO hd = hdBUS.HopDongMoiNhat(maNv);

					hdBUS.ThemNhanVienVaoPhongBan(hd, maPb);

					JOptionPane.showMessageDialog(null, "Thêm thành công");

					ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienTheoPhongBan(maPb);
					setModelTableNV(lstNV);

					nvFrame.dispatchEvent(new WindowEvent(nvFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
	}
}
