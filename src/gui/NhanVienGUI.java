package gui;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;

public class NhanVienGUI extends JPanel {
	JTable tblNV;
	
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
		
		// table header
		Vector<String> header = new Vector<String>();
        header.add("Mã NV");
        header.add("Họ NV");
        header.add("Tên NV");
        header.add("Số CMND");
        header.add("Ngày sinh");
        header.add("Giới tính");
        header.add("SĐT");
	}
}
