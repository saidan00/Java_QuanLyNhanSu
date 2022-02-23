package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.NhanVienBUS;
import dto.NhanVienDTO;

public class NhanVienGUI extends JPanel {
	NhanVienBUS nvBUS = new NhanVienBUS();
	MyProps myProps = new MyProps();

	JPanel pnlTable;
	JTable tblNV;

	JPanel pnlForm;
	// avatar
	JPanel pnlImg;
	// label
	JLabel lblTK_Ten;
	JLabel lblMaNV;
	JLabel lblHoNV;
	JLabel lblTenNV;
	JLabel lblSoCMND;
	JLabel lblNgaySinh;
	JLabel lblGioiTinh;
	JLabel lblSDT;
	JLabel lblDiaChi;
	JLabel lblAvatar;
	// text field
	JTextField txtTK_Ten;
	JTextField txtMaNV;
	JTextField txtHoNV;
	JTextField txtTenNV;
	JTextField txtSoCMND;
	JTextField txtNgaySinh;
	JTextField txtGioiTinh;
	JTextField txtSDT;
	JTextField txtDiaChi;
	// combo box
	JComboBox<String> boxGioiTinh;
	// button
	JButton btnThem;
	JButton btnXoa;
	JButton btnSua;
	JButton btnTK;

	final String TK_Ten = "Tìm kiếm";
	// table header title
	final String MA_NV = "Mã NV";
	final String HO_NV = "Họ NV";
	final String TEN_NV = "Tên NV";
	final String SO_CMND = "Số CMND";
	final String NGAY_SINH = "Ngày sinh";
	final String GIOI_TINH = "Giới tính";
	final String SDT = "SĐT";
	final String DIA_CHI = "Địa chỉ";

	// hàm khởi tạo
	public NhanVienGUI() {
		initComponents();
	}

	// khởi tạo các component
	private void initComponents() {
		this.setLayout(null);
		this.setBackground(Color.PINK);

		initPnlImg();

		initPnlForm();
		initForm();

		initPnlTable();
		initTblNV();
		tblNVMouseListener();

		initButton();
		btnThemClicked();
		btnXoaClicked();
		btnSuaClicked();
		btnTKClicked();
	}

	// khởi tạo Panel chứa form
	private void initPnlForm() {
		pnlForm = new JPanel();
		pnlForm.setBounds(pnlImg.getWidth(), 0, ContentPanel.WIDTH - pnlImg.getWidth(), ContentPanel.HEIGHT * 60 / 100);
		pnlForm.setLayout(new GridBagLayout());

		this.add(pnlForm);
	}

	private void initPnlImg() {
		pnlImg = new JPanel();
		pnlImg.setBounds(0, 0, ContentPanel.WIDTH * 30 / 100, ContentPanel.HEIGHT * 60 / 100);
		pnlImg.setLayout(new GridBagLayout());

		lblAvatar = new JLabel();
		lblAvatar.setIcon(new javax.swing.ImageIcon("src\\img\\blank.png"));

//		pnlImg.setBackground(Color.PINK);
		pnlImg.add(lblAvatar);

		this.add(pnlImg);
	}

	// khởi tạo form
	private void initForm() {
		GridBagConstraints cons = new GridBagConstraints();

		cons = myProps.MyGridBagConstraints(1, 1, 3, 7, true, true);
//		pnlForm.add(pnlImg, cons);
//		pnlForm.add(pnlImg);

		// tìm kiếm
		lblTK_Ten = new JLabel(TK_Ten);
		lblTK_Ten.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 0, 6, 1, true, true);
		pnlForm.add(lblTK_Ten, cons);

		txtTK_Ten = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(5, 0, 6, 1, true, true);
		pnlForm.add(txtTK_Ten, cons);

		// mã nhân viên
		lblMaNV = new JLabel(MA_NV);
		lblMaNV.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlForm.add(lblMaNV, cons);

		txtMaNV = myProps.RoundedTextField(5);
		txtMaNV.setEditable(false); // không cho sửa
		cons = myProps.MyGridBagConstraints(5, 1, 2, 1, true, true);
		pnlForm.add(txtMaNV, cons);

		// họ nhân viên
		lblHoNV = new JLabel(HO_NV);
		lblHoNV.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 2, 1, 1, true, true);
		pnlForm.add(lblHoNV, cons);

		txtHoNV = myProps.RoundedTextField(8);
		cons = myProps.MyGridBagConstraints(5, 2, 2, 1, true, true);
		pnlForm.add(txtHoNV, cons);

		// tên nhân viên
		lblTenNV = new JLabel(TEN_NV);
		lblTenNV.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(7, 2, 1, 1, true, true);
		pnlForm.add(lblTenNV, cons);

		txtTenNV = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(8, 2, 1, 1, true, true);
		pnlForm.add(txtTenNV, cons);

		// giới tính
		lblGioiTinh = new JLabel(GIOI_TINH);
		lblGioiTinh.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(9, 2, 1, 1, true, true);
		pnlForm.add(lblGioiTinh, cons);

//		txtGioiTinh = myProps.RoundedTextField(5);
//		cons = myProps.MyGridBagConstraints(10, 2, 1, 1, true, true);
//		pnlForm.add(txtGioiTinh, cons);

		String[] arrGioiTinh = { "Nam", "Nữ" };
		boxGioiTinh = new JComboBox<String>(arrGioiTinh);
		boxGioiTinh.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(10, 2, 1, 1, true, true);
		pnlForm.add(boxGioiTinh, cons);

		// số CMND
		lblSoCMND = new JLabel(SO_CMND);
		lblSoCMND.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 3, 1, 1, true, true);
		pnlForm.add(lblSoCMND, cons);

		txtSoCMND = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(5, 3, 3, 1, true, true);
		pnlForm.add(txtSoCMND, cons);

		// ngày sinh
		lblNgaySinh = new JLabel(NGAY_SINH);
		lblNgaySinh.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(8, 3, 1, 1, true, true);
		pnlForm.add(lblNgaySinh, cons);

		txtNgaySinh = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(9, 3, 2, 1, true, true);
		pnlForm.add(txtNgaySinh, cons);

		// sđt
		lblSDT = new JLabel(SDT);
		lblSDT.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 4, 1, 1, true, true);
		pnlForm.add(lblSDT, cons);

		txtSDT = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(5, 4, 6, 1, true, true);
		pnlForm.add(txtSDT, cons);

		// địa chỉ
		lblDiaChi = new JLabel(DIA_CHI);
		lblDiaChi.setFont(myProps.DEFAULT_FONT_SMALL);
		cons = myProps.MyGridBagConstraints(4, 5, 1, 1, true, true);
		pnlForm.add(lblDiaChi, cons);

		txtDiaChi = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(5, 5, 6, 1, true, true);
		pnlForm.add(txtDiaChi, cons);
	}

	// khởi tạo table Nhân viên
	private void initTblNV() {
		tblNV = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
//		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// đọc dữ liệu
		setModelTable();

		// không cho phép di chuyển vị trí columns
		tblNV.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblNV.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblNV.setAutoCreateRowSorter(true);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblNV);

		pnlTable.add(scroll);
	}

	// khởi tạo Panel chứa table
	private void initPnlTable() {
		pnlTable = new JPanel();
		pnlTable.setLayout(new GridLayout(1, 1));
		pnlTable.setBounds(0, pnlForm.getHeight(), ContentPanel.WIDTH, ContentPanel.HEIGHT - pnlForm.getHeight());

		this.add(pnlTable);
	}

	private void setModelTable() {
		// table header
		Vector<String> header = new Vector<String>();
		header.add(MA_NV);
		header.add(HO_NV);
		header.add(TEN_NV);
		header.add(SO_CMND);
		header.add(NGAY_SINH);
		header.add(GIOI_TINH);
		header.add(SDT);
		header.add(DIA_CHI);

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

		ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();
		lstNV = nvBUS.NhanVienAll(txtTK_Ten.getText());

//        test
//        for (int i = 0; i< lstNV.size(); i++) {
//        	System.out.print(lstNV.get(i).getHoNV() + "\n");
//        }

		NhanVienDTO aNV = new NhanVienDTO();

		for (int i = 0; i < lstNV.size(); i++) {
			aNV = lstNV.get(i);
			Object[] row = { aNV.getMaNV(), aNV.getHoNV(), aNV.getTenNV(), aNV.getSoCMND(), aNV.getNgaySinh(),
					aNV.getGioiTinh(), aNV.getSDT(), aNV.getDiaChi() };
			dtm.addRow(row);
		}

		tblNV.setModel(dtm);
	}

	private void tblNVMouseListener() {
		tblNV.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tblNV.getSelectedRow();

				txtMaNV.setText(tblNV.getValueAt(row, 0).toString());
				txtHoNV.setText(tblNV.getValueAt(row, 1).toString());
				txtTenNV.setText(tblNV.getValueAt(row, 2).toString());
//				txtGioiTinh.setText(tblNV.getValueAt(row, 5).toString());
				boxGioiTinh.setSelectedItem(tblNV.getValueAt(row, 5).toString());
				txtSoCMND.setText(tblNV.getValueAt(row, 3).toString());
				txtNgaySinh.setText(tblNV.getValueAt(row, 4).toString());
				txtSDT.setText(tblNV.getValueAt(row, 6).toString());
				txtDiaChi.setText(tblNV.getValueAt(row, 7).toString());

				if (tblNV.getValueAt(row, 5).toString().equals("Nam")) {
					lblAvatar.setIcon(new javax.swing.ImageIcon("src\\img\\avatar_male.png"));
				} else {
					lblAvatar.setIcon(new javax.swing.ImageIcon("src\\img\\avatar_female.png"));
				}
			}
		});
	}

	private void btnThemClicked() {
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NhanVienDTO nv = new NhanVienDTO();

				String hoNv = String.valueOf(txtHoNV.getText());
				String tennv = String.valueOf(txtTenNV.getText());
				String gt = String.valueOf(boxGioiTinh.getSelectedItem());
				String cmnd = String.valueOf(txtSoCMND.getText());
				String ngaysinh = String.valueOf(txtNgaySinh.getText());
				String sdt = String.valueOf(txtSDT.getText());
				String diachi = String.valueOf(txtDiaChi.getText());

				boolean hopLe = KiemTraForm(hoNv, tennv, cmnd, ngaysinh, sdt, diachi);

				if (hopLe == true) {
					nv.setHoNV(hoNv);
					nv.setTenNV(tennv);
					nv.setGioiTinh(gt);
					nv.setSoCMND(cmnd);
					nv.setNgaySinh(ngaysinh);
					nv.setSDT(sdt);
					nv.setDiaChi(diachi);

					if (nvBUS.NhanVienAdd(nv)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
					}

					setModelTable();
				}
			}
		});
	}

	private boolean KiemTraForm(String hoNv, String tennv, String cmnd, String ngaysinh, String sdt, String diachi) {
		boolean hopLe = true;

		if (hoNv.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập họ NV");
			hopLe = false;
			txtHoNV.requestFocus();
		} else if (tennv.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên NV");
			hopLe = false;
			txtTenNV.requestFocus();
		} else if (cmnd.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập CMND");
			hopLe = false;
			txtSoCMND.requestFocus();
		} else if (!KiemTraCMND(cmnd)) {
			JOptionPane.showMessageDialog(null, "CMND không hợp lệ (hợp lệ: 9 hoặc 12 số)");
			hopLe = false;
			txtSoCMND.requestFocus();
		} else if (ngaysinh.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày sinh");
			hopLe = false;
			txtNgaySinh.requestFocus();
		} else if (!KiemTraNgay(ngaysinh)) {
			JOptionPane.showMessageDialog(null, "Nhập ngày theo định dạng yyyy-mm-dd");
			hopLe = false;
			txtNgaySinh.requestFocus();
		} else if (!KiemTraTuoi(ngaysinh)) {
			JOptionPane.showMessageDialog(null, "Tuổi phải >= 18");
			hopLe = false;
			txtNgaySinh.requestFocus();
		} else if (sdt.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập SĐT");
			hopLe = false;
			txtSDT.requestFocus();
		} else if (!KiemTraSDT(sdt)) {
			JOptionPane.showMessageDialog(null, "SĐT không hợp lệ");
			hopLe = false;
			txtSDT.requestFocus();
		} else if (diachi.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ");
			hopLe = false;
			txtDiaChi.requestFocus();
		}

		return hopLe;
	}

	public boolean KiemTraTuoi(String ngaySinh) {
		String[] p = ngaySinh.split("-", 2);
		String p1 = p[0];
		int yearOfBirth = Integer.parseInt(p1);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int age = currentYear - yearOfBirth;

		return (age >= 18);
	}

	public boolean KiemTraNgay(String ns) {
		return Pattern.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
				+ "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
				+ "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$", ns);
	}

	public boolean KiemTraSDT(String SDT) {
		return Pattern.matches("[0]{1}\\d{9}", SDT);
	}

	public boolean KiemTraCMND(String cmnd) {
		return Pattern.matches("{0}\\d{9}", cmnd);
	}

	private void btnXoaClicked() {
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtMaNV.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
				} else {
					int maNv = Integer.valueOf(txtMaNV.getText());

					nvBUS.NhanVienDelete(maNv);

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
				NhanVienDTO nv = nvBUS.NhanVienGet(Integer.parseInt(txtMaNV.getText()));

				String hoNv = String.valueOf(txtHoNV.getText());
				String tennv = String.valueOf(txtTenNV.getText());
				String gt = String.valueOf(boxGioiTinh.getSelectedItem());
				String cmnd = String.valueOf(txtSoCMND.getText());
				String ngaysinh = String.valueOf(txtNgaySinh.getText());
				String sdt = String.valueOf(txtSDT.getText());
				String diachi = String.valueOf(txtDiaChi.getText());

				boolean hopLe = KiemTraForm(hoNv, tennv, cmnd, ngaysinh, sdt, diachi);

				if (hopLe == true) {
					nv.setHoNV(hoNv);
					nv.setTenNV(tennv);
					nv.setGioiTinh(gt);
					nv.setSoCMND(cmnd);
					nv.setNgaySinh(ngaysinh);
					nv.setSDT(sdt);
					nv.setDiaChi(diachi);

					if (nvBUS.NhanVienEdit(nv)) {
						JOptionPane.showMessageDialog(null, "Sửa thành công");
					}

					setModelTable();
				}
			}
		});
	}

	private void btnTKClicked() {
		btnTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setModelTable();
			}
		});
	}

	private void initButton() {
		GridBagConstraints cons = new GridBagConstraints();
		JPanel pnlButton = new JPanel();

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

		btnTK = new JButton(TK_Ten);
		myProps.BtnFlat(btnTK);
		btnTK.setBackground(Color.decode("#e0e0e0"));
		btnTK.setForeground(Color.RED);
		btnTK.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		pnlButton.add(btnThem);
		pnlButton.add(btnSua);
		pnlButton.add(btnXoa);
		pnlButton.add(btnTK);

		cons = myProps.MyGridBagConstraints(4, 8, 9, 1, true, true);
		pnlForm.add(pnlButton, cons);
	}
}
