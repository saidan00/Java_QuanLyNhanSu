package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.NhanVienBUS;
import dto.NhanVienDTO;
import util.RoundedCornerBorder;

public class NhanVienGUI extends JPanel {
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
	}
	
	// khởi tạo Panel chứa form
	private void initPnlForm() {
		pnlForm = new JPanel();
		pnlForm.setBounds(0, 0, ContentPanel.WIDTH, ContentPanel.HEIGHT*60/100);
		pnlForm.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		
		pnlImg = new JPanel();
		pnlImg.setBackground(Color.PINK);
		cons = MyProps.MyGridBagConstraints(1, 1, 3, 4, true, true);
		pnlForm.add(pnlImg, cons);
		
		txtMaNV = MyProps.RoundedTextField(5);
		cons = MyProps.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlForm.add(txtMaNV, cons);
		
		txtHoNV = MyProps.RoundedTextField(5);
		cons = MyProps.MyGridBagConstraints(4, 2, 1, 1, true, true);
		pnlForm.add(txtHoNV, cons);
		
		txtTenNV = MyProps.RoundedTextField(5);
		cons = MyProps.MyGridBagConstraints(4, 3, 1, 1, true, true);
		pnlForm.add(txtTenNV, cons);
		
		txtSoCMND = MyProps.RoundedTextField(5);
		cons = MyProps.MyGridBagConstraints(4, 4, 1, 1, true, true);
		pnlForm.add(txtSoCMND, cons);
		
		this.add(pnlForm); 
	}
	
	// khởi tạo  form
	private void initForm() {
		
	}

	// khởi tạo table Nhân viên
	private void initTblNV() {
		tblNV = new JTable();
		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // đọc dữ liệu
        setModelTable();
        
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
		
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
        ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();
        lstNV = NhanVienBUS.NhanVienAll();
        
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
}
