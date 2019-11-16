package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bus.PhongBanBUS;
import dto.PhongBanDTO;

public class PhongBanGUI extends JPanel {
	PhongBanBUS pbBUS = new PhongBanBUS();
	MyProps myProps = new MyProps();
	
	JLabel lbl = new JLabel("Phong ban");
	
	JPanel pnlPB;
	JPanel pnlNV;

	JTable tblPB;
	JTable tblNV;
	
	JLabel lblPB;
	JLabel lblNV;
	
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
		initPanelNhanVien();
	}
	
	private void initPanelPhongBan() {
		pnlPB = new JPanel();
		pnlPB.setLayout(new GridBagLayout());
		pnlPB.setBackground(Color.BLUE);
		
		this.add(pnlPB);
	}
	
	private void initTablePhongBan() {
		tblPB = new JTable();
		
		setModelTablePB();
		
		lblPB = new JLabel("PHONG BAN");
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 4, 1, true, true);
		pnlPB.add(lblPB, cons);
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
	
	private void initPanelNhanVien() {
		pnlNV = new JPanel();
		pnlNV.setLayout(new GridBagLayout());
		pnlNV.setBackground(Color.YELLOW);
		
		this.add(pnlNV);
	}
}
