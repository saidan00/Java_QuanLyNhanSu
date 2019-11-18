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
import java.util.Vector;

import javax.swing.JButton;
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
	JLabel lblMaNV;
	JLabel lblHoNV;
	JLabel lblTenNV;
	JLabel lblSoCMND;
	JLabel lblNgaySinh;
	JLabel lblGioiTinh;
	JLabel lblSDT;
	JLabel lblDiaChi;
	// text field
	JTextField txtMaNV;
	JTextField txtHoNV;
	JTextField txtTenNV;
	JTextField txtSoCMND;
	JTextField txtNgaySinh;
	JTextField txtGioiTinh;
	JTextField txtSDT;
	JTextField txtDiaChi;
	// button
	JButton btnThem;
	JButton btnXoa;
	JButton btnSua;
	
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
		
		initPnlForm();
		initForm();
		
		initPnlTable();
		initTblNV();
		tblNVMouseListener();
		
		initButton();
		btnThemClicked();
		btnXoaClicked();
		btnSuaClicked();
	}
	
	// khởi tạo Panel chứa form
	private void initPnlForm() {
		pnlForm = new JPanel();
		pnlForm.setBounds(0, 0, ContentPanel.WIDTH, ContentPanel.HEIGHT*60/100);
		pnlForm.setLayout(new GridBagLayout());
		
		this.add(pnlForm);
	}
	
	// khởi tạo  form
	private void initForm() {
		GridBagConstraints cons = new GridBagConstraints();
		
		pnlImg = new JPanel();
		pnlImg.setBackground(Color.PINK);
		cons = myProps.MyGridBagConstraints(1, 2, 3, 4, true, true);
		pnlForm.add(pnlImg, cons);
		
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
		
		txtGioiTinh = myProps.RoundedTextField(5);
		cons = myProps.MyGridBagConstraints(10, 2, 1, 1, true, true);
		pnlForm.add(txtGioiTinh, cons);
		
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
				return false; //Disallow the editing of any cell
			}
		};
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
        lstNV = nvBUS.NhanVienAll();
        
//        test
//        for (int i = 0; i< lstNV.size(); i++) {
//        	System.out.print(lstNV.get(i).getHoNV() + "\n");
//        }
        
        NhanVienDTO aNV = new NhanVienDTO();
        
        for (int i = 0; i < lstNV.size(); i++) {
        	aNV = lstNV.get(i);
        	Object[] row = {
        			aNV.getMaNV(),
        			aNV.getHoNV(),
        			aNV.getTenNV(),
        			aNV.getSoCMND(),
        			aNV.getNgaySinh(),
        			aNV.getGioiTinh(),
        			aNV.getSDT(),
        			aNV.getDiaChi()
        	};
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
				txtGioiTinh.setText(tblNV.getValueAt(row, 5).toString());
				txtSoCMND.setText(tblNV.getValueAt(row, 3).toString());
				txtNgaySinh.setText(tblNV.getValueAt(row, 4).toString());
				txtSDT.setText(tblNV.getValueAt(row, 6).toString());
				txtDiaChi.setText(tblNV.getValueAt(row, 7).toString());
			}
		});
	}
	
	private void btnThemClicked() {
		btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	NhanVienDTO nv = new NhanVienDTO();
            	
            	nv.setHoNV(String.valueOf(txtHoNV.getText()));
            	nv.setTenNV(String.valueOf(txtTenNV.getText()));
            	nv.setGioiTinh(String.valueOf(txtGioiTinh.getText()));
            	nv.setSoCMND(String.valueOf(txtSoCMND.getText()));
            	nv.setNgaySinh(String.valueOf(txtNgaySinh.getText()));
            	nv.setSDT(String.valueOf(txtSDT.getText()));
            	nv.setDiaChi(String.valueOf(txtDiaChi.getText()));
            	
                nvBUS.NhanVienAdd(nv);
                
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                
                setModelTable();
            }
        });
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
            	NhanVienDTO nv = new NhanVienDTO();
            	
            	nv.setMaNV(Integer.valueOf(txtMaNV.getText()));
            	nv.setHoNV(String.valueOf(txtHoNV.getText()));
            	nv.setTenNV(String.valueOf(txtTenNV.getText()));
            	nv.setGioiTinh(String.valueOf(txtGioiTinh.getText()));
            	nv.setSoCMND(String.valueOf(txtSoCMND.getText()));
            	nv.setNgaySinh(String.valueOf(txtNgaySinh.getText()));
            	nv.setSDT(String.valueOf(txtSDT.getText()));
            	nv.setDiaChi(String.valueOf(txtDiaChi.getText()));
            	
            	nvBUS.NhanVienEdit(nv);
                
                JOptionPane.showMessageDialog(null, "Sửa thành công");
                
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
		btnThem.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnThem.setText("Thêm");
		btnThem.setSize(20, 10);
		
		btnXoa = new JButton();
		myProps.BtnFlat(btnXoa);
		btnXoa.setBackground(Color.decode("#e53935"));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnXoa.setText("Xóa");
		
		btnSua = new JButton();
		myProps.BtnFlat(btnSua);
		btnSua.setBackground(Color.decode("#e0e0e0"));
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnSua.setText("Sửa");
		
		pnlButton.add(btnThem);
		pnlButton.add(btnXoa);
		pnlButton.add(btnSua);

		cons = myProps.MyGridBagConstraints(4, 8, 9, 1, true, true);
		pnlForm.add(pnlButton, cons);
	}
}
