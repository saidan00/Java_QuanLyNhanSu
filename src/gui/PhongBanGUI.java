package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bus.NhanVienBUS;
import bus.PhongBanBUS;
import dto.NhanVienDTO;
import dto.PhongBanDTO;

public class PhongBanGUI extends JPanel {
	PhongBanBUS pbBUS = new PhongBanBUS();
	NhanVienBUS nvBUS = new NhanVienBUS();
	MyProps myProps = new MyProps();
	
	JPanel pnlPB;
	JPanel pnlNV;

	JTable tblPB;
	JTable tblNV;
	
	final String lblPB = "PHÒNG BAN";
	final String lblNV = "NHÂN VIÊN";
	
	JLabel lblPbMaPhong, lblPbTenPhong;
	JLabel lblNvMaNV, lblNvHoNV, lblNvTenNV, lblNvChucVu;
	
	JButton btnPbThem, btnPbSua;
	JButton btnNvThem, btnNvXoa;
	
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
		this.setLayout(new GridLayout(1, 1));
//		this.setBackground(Color.PINK);
		
		initPanelPhongBan();
		initTablePhongBan();
		initButtonPhongBan();
		
		initPanelNhanVien();
		initTableNhanVien();
		initButtonNhanVien();
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
	
	private void initTablePhongBan() {
		tblPB = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
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
	        	Object[] row = {
        			pb.getMaPhong(),
        			pb.getTenPhong()
	        	};
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
		btnPbThem.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		btnPbSua = new JButton("Sửa");
		cons = myProps.MyGridBagConstraints(3, 1, 2, 1, true, true);
		pnlBtn.add(btnPbSua, cons);
		myProps.BtnFlat(btnPbSua);
		btnPbSua.setBackground(Color.decode("#e0e0e0"));
		btnPbSua.setForeground(Color.BLACK);
		btnPbSua.setFont(new Font("Verdana", Font.PLAIN, 12));
		btnPbSua.setText("Sửa");
		
		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlPB.add(pnlBtn, cons);
	}
	
	private void initPanelNhanVien() {
		pnlNV = new JPanel();
		pnlNV.setLayout(new GridBagLayout());
		
		TitledBorder border = new TitledBorder(lblNV);
	    border.setTitleJustification(TitledBorder.LEFT);
	    border.setTitlePosition(TitledBorder.TOP);

	    pnlNV.setBorder(border);
		
		this.add(pnlNV);
	}
	
	private void initTableNhanVien() {
		tblNV = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};
        
        // đọc dữ liệu
        setModelTableNV();
        
        // không cho phép di chuyển vị trí columns
        tblNV.getTableHeader().setReorderingAllowed(false);
        
        // không cho phép resize column
        tblNV.getTableHeader().setResizingAllowed(false);
        
        // sắp xếp khi click header
        tblNV.setAutoCreateRowSorter(true);
        
        GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 4, 5, true, true);
        
        // scroll bar
        JScrollPane scroll = new JScrollPane(tblNV);
        
        pnlNV.add(scroll, cons);
	}
	
	private void setModelTableNV() {
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
			
	        ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();
	        lstNV = nvBUS.NhanVienAll();
	        
	        NhanVienDTO nv = new NhanVienDTO();
	        
	        for (int i = 0; i < lstNV.size(); i++) {
	        	nv = lstNV.get(i);
	        	Object[] row = {
        			nv.getMaNV(),
        			nv.getHoNV() + " " + nv.getTenNV()
	        	};
	        	dtm.addRow(row);
	        }
	        
	        tblNV.setModel(dtm);
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
		btnNvThem.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		btnNvXoa = new JButton("Xóa khỏi phòng");
		cons = myProps.MyGridBagConstraints(3, 1, 2, 1, true, true);
		pnlBtn.add(btnNvXoa, cons);
		myProps.BtnFlat(btnNvXoa);
		btnNvXoa.setBackground(Color.decode("#e53935"));
		btnNvXoa.setForeground(Color.WHITE);
		btnNvXoa.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		cons = myProps.MyGridBagConstraints(1, 7, 4, 1, true, true);
		pnlNV.add(pnlBtn, cons);
	}
}
