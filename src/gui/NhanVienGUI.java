package gui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bus.NhanVienBUS;
import dto.NhanVienDTO;

public class NhanVienGUI extends JPanel {
	JTable tblNV;
	
	// table header title
	final String MA_NV = "Mã NV";
	final String HO_NV = "Họ NV";
	final String TEN_NV = "Tên NV";
	final String SO_CMND = "Số CMND";
	final String NGAY_SINH = "Ngày sinh";
	final String GIOI_TINH = "Giới tính";
	final String SDT = "SĐT";
	
	// hàm khởi tạo
	public NhanVienGUI() {
		initComponents();
	}
	
	// khởi tạo các component
	private void initComponents() {
		initTblNV();
	}
	
	// khởi tạo table Nhân viên
	private void initTblNV() {
		tblNV = new JTable();
		
        // đọc dữ liệu
        setModelTable();
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
		
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
        ArrayList<NhanVienDTO> lstNV = new ArrayList<NhanVienDTO>();
        lstNV = NhanVienBUS.NhanVienAll();
        
        for (int i = 0; i< lstNV.size(); i++) {
        	System.out.print(lstNV.get(i).getHoNV() + "\n");
        }
        
//        NhanVienDTO aNV = new NhanVienDTO();
//        
//        for (int i = 0; i < lstNV.size(); i++) {
//        	aNV = lstNV.get(i);
//        	Object[] row = {
//        			aNV.getMaNV(),
//        			aNV.getHoNV(),
//        			aNV.getTenNV(),
//        			aNV.getSoCMND(),
//        			aNV.getNgaySinh(),
//        			aNV.getGioiTinh(),
//        			aNV.getSDT()
//        	};
//        	dtm.addRow(row);
//        }
//        
//        tblNV.setModel(dtm);
	}
}
