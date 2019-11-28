package gui;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import bus.BangChamCongBUS;
import bus.ChamCongBUS;
import bus.NhanVienBUS;
import bus.PhongBanBUS;

public class ThongKeGUI extends JPanel {
	NhanVienBUS nvBUS = new NhanVienBUS();
	PhongBanBUS pbBUS = new PhongBanBUS();
	ChamCongBUS ccBUS = new ChamCongBUS();
	BangChamCongBUS bccBUS = new BangChamCongBUS();

	MyProps myProps = new MyProps();

	JTable tblTK;

	JLabel lblOption;
	JLabel lblThang;
	JLabel lblNam;

	JComboBox<Integer> boxOption;
	JComboBox<Integer> boxThang;
	JComboBox<Integer> boxNam;

	public ThongKeGUI() {
		this.setLayout(new GridBagLayout());

		initComponents();
	}

	private void initComponents() {
		initComboBox();
	}

	private void initComboBox() {
		ArrayList<String> lstOption = new ArrayList<String>();
		lstOption.add("1. Hello");

		Integer[] arrayThang = lstOption.toArray(new Integer[0]);

		boxOption = new JComboBox<Integer>(arrayThang);
		boxOption.setFont(myProps.DEFAULT_FONT_SMALL);

	}
}
