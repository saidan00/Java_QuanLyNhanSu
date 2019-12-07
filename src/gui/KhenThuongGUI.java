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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.KhenThuongBUS;
import bus.NhanVienBUS;
import dto.KhenThuongDTO;
import dto.NhanVienDTO;

public class KhenThuongGUI extends JPanel {
	MyProps myProps = new MyProps();
	NhanVienBUS nvBUS = new NhanVienBUS();
	KhenThuongBUS ktBUS = new KhenThuongBUS();
	
	JPanel pnlForm;
	
	JLabel lblTenNV, lblNgay, lblHinhThuc, lblLyDo, lblTienThuong;
	
	JTextField txtTenNV;
	JTextField txtLyDo;
	JTextField txtTienThuong;
	
	JComboBox<String> boxHinhThuc;
	
	JButton btnThem, btnXoa, btnSua;
	JButton btnChonNV;
	
	JTable tblKT;
	
	final String TEN_NV = "Nhân viên";
	final String HINH_THUC = "Hình thức";
	final String LY_DO = "Lý do";
	final String TIEN_THUONG = "Tiền thưởng";
	
	int maNv = 0, maKt = 0;
	
	public KhenThuongGUI() {
		this.setLayout(new GridBagLayout());
		
		initComponents();
	}

	private void initComponents() {
		initPanelForm();
		initForm();
		initButton();
		
		initTableKT();
		setModelTable();
		
		btnThemClicked();
		btnSuaClicked();
		btnXoaClicked();
		btnChonNvClicked();
		
		tableKTClicked();
	}
	
	private void initPanelForm() {
		pnlForm = new JPanel();
//		pnlForm.setLayout(new GridBagLayout());
		
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 5, 1, true, true);
	
		this.add(pnlForm, cons);
	}
	
	private void initForm() {
//		GridBagConstraints cons = new GridBagConstraints();
		
		lblTenNV = new JLabel(TEN_NV);
		lblTenNV.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
//		cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
//		pnlForm.add(lblTenNV, cons);
		pnlForm.add(lblTenNV);
		
		txtTenNV = new JTextField(10);
		txtTenNV.setEditable(false);
//		cons = myProps.MyGridBagConstraints(2, 1, 1, 1, true, true);
//		pnlForm.add(txtTenNV, cons);
		pnlForm.add(txtTenNV);
		
		btnChonNV = new JButton("...");
		myProps.BtnFlat(btnChonNV);
		btnChonNV.setBackground(Color.decode("#e0e0e0"));
		btnChonNV.setForeground(Color.BLACK);
//		cons = myProps.MyGridBagConstraints(3, 1, 1, 1, true, true);
//		pnlForm.add(btnChonNV, cons);
		pnlForm.add(btnChonNV);
		
		
		lblHinhThuc = new JLabel(HINH_THUC);
		lblHinhThuc.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		pnlForm.add(lblHinhThuc);
		
		ArrayList<String> lstHT = new ArrayList<String>();
		lstHT.add("CK");
		lstHT.add("TM");

		String[] arrayHT = lstHT.toArray(new String[0]);

		boxHinhThuc = new JComboBox<String>(arrayHT);
		boxHinhThuc.setFont(myProps.DEFAULT_FONT_SMALL);
		pnlForm.add(boxHinhThuc);
		
		lblTienThuong = new JLabel(TIEN_THUONG);
		lblTienThuong.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		pnlForm.add(lblTienThuong);
		
		txtTienThuong = new JTextField(8);
		pnlForm.add(txtTienThuong);
		
		lblLyDo = new JLabel(LY_DO);
		pnlForm.add(lblLyDo);
		
		txtLyDo = new JTextField(20);
		pnlForm.add(txtLyDo);
	}
	
	private void initTableKT() {
		tblKT = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
//		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// không cho phép di chuyển vị trí columns
		tblKT.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
//		tblKT.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblKT.setAutoCreateRowSorter(true);

//		tblKT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblKT, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 6, 5, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(scroll, cons);
	}
	
	private void setModelTable() {
		ArrayList<KhenThuongDTO> lstKT = new ArrayList<KhenThuongDTO>();

		lstKT = ktBUS.KhenThuongAll();

		// table header
		Vector<String> header = new Vector<String>();
		header.add("Mã khen thưởng");
//		header.add("Mã NV");
		header.add("Nhân viên");
		header.add("Ngày");
		header.add("Hình thức");
		header.add("Lý do");
		header.add("Tiền thưởng");

		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
				case 5:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};

		KhenThuongDTO kt = new KhenThuongDTO();

		for (int i = 0; i < lstKT.size(); i++) {
			kt = lstKT.get(i);
			NhanVienDTO nv = nvBUS.NhanVienGet(kt.getMaNV());

			ArrayList<Object> lstRow = new ArrayList<Object>();
			lstRow.add(kt.getMaKhenThuong());
//			lstRow.add(kt.getMaNV());
			lstRow.add(nv.getHoNV() + " " + nv.getTenNV());
			lstRow.add(kt.getNgayKhenThuong());
			lstRow.add(kt.getHinhThuc());
			lstRow.add(kt.getLyDo());
			lstRow.add(kt.getTienThuong());

			Object[] row = lstRow.toArray();

			dtm.addRow(row);
		}

		tblKT.setModel(dtm);
	}
	
	private void initButton() {
		btnThem = new JButton();
		myProps.BtnFlat(btnThem);
		btnThem.setBackground(Color.decode("#4caf50"));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		btnThem.setText("Thêm");
		btnThem.setSize(20, 10);

		btnXoa = new JButton();
		myProps.BtnFlat(btnXoa);
		btnXoa.setBackground(Color.decode("#e53935"));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		btnXoa.setText("Xóa");

		btnSua = new JButton();
		myProps.BtnFlat(btnSua);
		btnSua.setBackground(Color.decode("#e0e0e0"));
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Arial Nova", Font.PLAIN, 12));
		btnSua.setText("Sửa");

		pnlForm.add(btnThem);
		pnlForm.add(btnSua);
		pnlForm.add(btnXoa);
	}
	
	private void btnThemClicked() {
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KhenThuongDTO kt = new KhenThuongDTO();
				kt.setHinhThuc(boxHinhThuc.getSelectedItem().toString());
				kt.setMaNV(maNv);
				kt.setNgayKhenThuong(myProps.currentDate());
				kt.setLyDo(txtLyDo.getText().toString());
				kt.setTienThuong(Integer.valueOf(txtTienThuong.getText()));
				
				ktBUS.KhenThuongAdd(kt);
				
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				setModelTable();
			}
		});
	}
	
	private void btnXoaClicked() {
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (maKt == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn mục cần xóa");
				} else {
					ktBUS.KhenThuongDelete(maKt);
					JOptionPane.showMessageDialog(null, "Xóa thành công");
					setModelTable();
				}
			}
		});
	}

	private void btnSuaClicked() {
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (maKt == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn mục cần sửa");
				} else {
					KhenThuongDTO kt = ktBUS.KhenThuongGet(maKt);
					
					kt.setMaNV(maNv);
					kt.setHinhThuc(boxHinhThuc.getSelectedItem().toString());
					kt.setLyDo(txtLyDo.getText().toString());
					kt.setTienThuong(Integer.valueOf(txtTienThuong.getText()));
					
					ktBUS.KhenThuongEdit(kt);
					
					JOptionPane.showMessageDialog(null, "Sửa thành công");
					setModelTable();
				}
			}
		});
	}
	
	private void btnChonNvClicked() {
		btnChonNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameNV();
			}
		});
	}
	
	private void FrameNV() {
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
		ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienAll("");

		// table header
		Vector<String> header = new Vector<String>();
//		header.add("Mã khen thưởng");
//		header.add("Nhân viên");
//		header.add("Ngày");
//		header.add("Hình thức");
//		header.add("Lý do");
//		header.add("Tiền thưởng");
		
		header.add("Mã NV");
		header.add("Nhân viên");
		

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
					JOptionPane.showMessageDialog(null, "Vui lòng chọn NV");
				} else {
					maNv = (int) tblNvTemp.getValueAt(row, 0);
					NhanVienDTO nv = nvBUS.NhanVienGet(maNv);

					txtTenNV.setText(nv.getHoNV() + " " + nv.getTenNV());

					nvFrame.dispatchEvent(new WindowEvent(nvFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
	}
	
	private void tableKTClicked() {
		tblKT.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblKT.getSelectedRow();
				maKt = (int) tblKT.getValueAt(row, 0);
				
				KhenThuongDTO kt = ktBUS.KhenThuongGet(maKt);
				maNv = kt.getMaNV();

				txtTenNV.setText(tblKT.getValueAt(row, 1).toString());
				boxHinhThuc.setSelectedItem(tblKT.getValueAt(row, 3));
				txtLyDo.setText(tblKT.getValueAt(row, 4).toString());
				txtTienThuong.setText(tblKT.getValueAt(row, 5).toString());
			}
		});
	}
}
