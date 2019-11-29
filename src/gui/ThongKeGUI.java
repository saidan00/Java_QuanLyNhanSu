package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	
	JPanel pnlForm;

	JComboBox<String> boxOption;
	JComboBox<Integer> boxThang;
	JComboBox<Integer> boxNam;
	
	JButton btnExcel;
	JButton btnChon;
	
	final String LUA_CHON = "Chọn";

	public ThongKeGUI() {
		this.setLayout(new GridBagLayout());

		initComponents();
	}

	private void initComponents() {
		initPnlForm();
		initForm();
		initTableThongKe();
		
		initButton();
		btnThongKeClicked();
	}
	
	private void initPnlForm() {
		pnlForm = new JPanel();
		
		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		this.add(pnlForm, cons);
	}

	private void initForm() {
		lblOption = new JLabel(LUA_CHON);
		lblOption.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		
		ArrayList<String> lstOption = new ArrayList<String>();
		lstOption.add("1. Hello");
		lstOption.add("2. Hello 2");
		lstOption.add("3. Hello 3");

		String[] arrayThang = lstOption.toArray(new String[0]);

		boxOption = new JComboBox<String>(arrayThang);
		boxOption.setFont(myProps.DEFAULT_FONT_SMALL);
		
		pnlForm.add(lblOption);
		pnlForm.add(boxOption);
	}
	
	private void initButton() {
		btnChon = new JButton("Thống kê");
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#4caf50"));
		btnChon.setForeground(Color.WHITE);
		btnChon.setFont(myProps.DEFAULT_FONT_SMALL);
		
		btnExcel = new JButton("Xuất excel");
		myProps.BtnFlat(btnExcel);
		btnExcel.setBackground(Color.decode("#e0e0e0"));
		btnExcel.setForeground(Color.BLACK);
		btnExcel.setFont(myProps.DEFAULT_FONT_SMALL);
		
		pnlForm.add(btnChon);
		pnlForm.add(btnExcel);
	}
	
	private void initTableThongKe() {
		tblTK = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
//		tblNV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// không cho phép di chuyển vị trí columns
		tblTK.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
//		tblChamCong.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
//		tblChamCong.setAutoCreateRowSorter(true);

		tblTK.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblTK, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 6, 5, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(scroll, cons);
	}
	
	private void btnThongKeClicked() {
		ActionListener btnChonClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String option = (String) boxOption.getSelectedItem();
				option = option.substring(0, 1);
//				System.out.print(option);
			}
		};
		
		btnChon.addActionListener(btnChonClick);
	}
}
