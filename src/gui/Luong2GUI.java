package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import bus.LuongBUS;
import dto.LuongDTO;

public class Luong2GUI extends JPanel {

	LuongBUS luongBUS = new LuongBUS();
	MyProps myprops = new MyProps();

	JPanel pnlTabel;
	JTable tbLuong;

	JPanel pnlForm, pnlButton;
	// label
	JLabel lblMaluong, lblHSluong, lblLuongcb, lblHSpc;
	// textfield
	JTextField txtMaluong, txtHSluong, txtLuongcb, txtHSpc;
	// button
	JButton btThem, btSua, btXoa;
	// tabel header title
	final String MA_LUONG = "Mã Lương";
	// final String HESO_LUONG = "Hệ Số Lương";
	final String LUONG_COBAN = "Lương Cơ Bản";
	// final String HESO_PHUCAP = "Hệ Số Phụ Cấp";

	// ham khoi tao
	public Luong2GUI() {
		initCompenents();
	}

	// tao cac compenents
	public void initCompenents() {
		this.setLayout(null);
		this.setBackground(Color.red);

		initPnlForm();
		initForm();
		initPnlTabel();
		initTbluong();

		tblLuongMouseListener();

		initButton();
		btThemClicked();
		btSuaClicked();
		btXoaClicked();
	}

	// tao panel chua form
	public void initPnlForm() {
		pnlForm = new JPanel();
		pnlForm.setBounds(0, 0, ContentPanel.WIDTH, ContentPanel.HEIGHT * 60 / 100);
		pnlForm.setLayout(new GridBagLayout());
		this.add(pnlForm);
	}

	// tao form
	public void initForm() {
		GridBagConstraints cons = new GridBagConstraints();

		// maluong
		lblMaluong = new JLabel(MA_LUONG);
		lblMaluong.setFont(myprops.DEFAULT_FONT_SMALL);
		cons = myprops.MyGridBagConstraints(3, 1, 1, 1, true, true);
		pnlForm.add(lblMaluong, cons);

		txtMaluong = myprops.RoundedTextField(2);
		txtMaluong.setEditable(false);
		cons = myprops.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlForm.add(txtMaluong, cons);

//        //he so luong
//        lblHSluong = new JLabel(HESO_LUONG);
//        lblHSluong.setFont(myprops.DEFAULT_FONT_SMALL);
//        cons = myprops.MyGridBagConstraints(4, 2, 1, 1, true, true);
//        pnlForm.add(lblHSluong, cons);
//        
//        txtHSluong = myprops.RoundedTextField(3);
//        txtHSluong.setEditable(false);
//        cons = myprops.MyGridBagConstraints(5, 2, 2, 1, true, true);
//        pnlForm.add(txtHSluong, cons);
		// luong co ban
		lblLuongcb = new JLabel(LUONG_COBAN);
		lblLuongcb.setFont(myprops.DEFAULT_FONT_SMALL);
		cons = myprops.MyGridBagConstraints(4, 2, 1, 1, true, true);
		pnlForm.add(lblLuongcb, cons);

		txtLuongcb = myprops.RoundedTextField(5);
		txtLuongcb.setEditable(false);
		cons = myprops.MyGridBagConstraints(5, 2, 2, 1, true, true);
		pnlForm.add(txtLuongcb, cons);

//        //he so phu cap
//        lblHSpc = new JLabel(HESO_PHUCAP);
//        lblHSpc.setFont(myprops.DEFAULT_FONT_SMALL);
//        cons = myprops.MyGridBagConstraints(6, 4, 1, 1, true, true);
//        pnlForm.add(lblHSpc, cons);
//        
//        txtHSpc = myprops.RoundedTextField(7);
//        txtHSpc.setEditable(false);
//        cons = myprops.MyGridBagConstraints(7, 4, 2, 1, true, true);
//        pnlForm.add(txtHSpc, cons);
	}

	// tao table luong
	public void initTbluong() {
		tbLuong = new JTable();
		// doc du lieu
		setModelTable();

		// k cho di chuyen vi tri column
		tbLuong.getTableHeader().setReorderingAllowed(false);

		// k cho resize column
		tbLuong.getTableHeader().setResizingAllowed(false);

		// sap xep khi click header
		tbLuong.setAutoCreateRowSorter(true);

		// thanh cuon
		JScrollPane scroll = new JScrollPane(tbLuong);
		pnlTabel.add(scroll);
	}

	// tao panel chua table
	public void initPnlTabel() {
		pnlTabel = new JPanel();
		pnlTabel.setBounds(0, pnlForm.getHeight(), ContentPanel.WIDTH, ContentPanel.HEIGHT - pnlForm.getHeight());
		this.add(pnlTabel);
	}

	public void setModelTable() {
		Vector<String> header = new Vector<String>();
		header.add(MA_LUONG);
		// header.add(HESO_LUONG);
		header.add(LUONG_COBAN);
		// header.add(HESO_PHUCAP);

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

		ArrayList<LuongDTO> lst = new ArrayList<LuongDTO>();
		lst = luongBUS.LuongAll();

		LuongDTO l = new LuongDTO();
		for (int i = 0; i < lst.size(); i++) {
			l = lst.get(i);
			Object[] row = { l.getMaLuong(),
					// l.getHeSoLuong(),
					l.getLuongCB(), // l.getHeSoPhuCap()
			};
			dtm.addRow(row);
		}
		tbLuong.setModel(dtm);
	}

	private void tblLuongMouseListener() {
		tbLuong.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tbLuong.getSelectedRow();

				txtMaluong.setText(tbLuong.getValueAt(row, 0).toString());
				txtHSluong.setText(tbLuong.getValueAt(row, 1).toString());
				txtLuongcb.setText(tbLuong.getValueAt(row, 2).toString());
				txtHSpc.setText(tbLuong.getValueAt(row, 3).toString());
			}
		});
	}

	private void btThemClicked() {
		btThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LuongDTO luongdto = new LuongDTO();

				// luongdto.setHeSoLuong(Float.valueOf(txtHSluong.getText()));
				luongdto.setLuongCB(Integer.valueOf(txtLuongcb.getText()));
				// luongdto.setHeSoPhuCap(Float.valueOf(txtHSpc.getText()));

				luongBUS.LuongAdd(luongdto);

				JOptionPane.showMessageDialog(null, "Thêm thành công");

				setModelTable();
			}
		});
	}

	private void btSuaClicked() {
		btSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LuongDTO luongdto = new LuongDTO();

				luongdto.setMaLuong(Integer.valueOf(txtMaluong.getText()));
				// luongdto.setHeSoLuong(Float.valueOf(txtHSluong.getText()));
				luongdto.setLuongCB(Integer.valueOf(txtLuongcb.getText()));
				// luongdto.setHeSoPhuCap(Float.valueOf(txtHSpc.getText()));

				luongBUS.LuongEdit(luongdto);

				JOptionPane.showMessageDialog(null, "Sửa thành công");

				setModelTable();
			}
		});
	}

	private void btXoaClicked() {
		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int maluong = Integer.valueOf(txtMaluong.getText());

				luongBUS.LuongDelete(maluong);

				JOptionPane.showMessageDialog(null, "Xóa thành công");
				setModelTable();
			}
		});
	}

	private void initButton() {
		GridBagConstraints cons = new GridBagConstraints();
		pnlButton = new JPanel();

		// them
		btThem = new JButton();
		myprops.BtnFlat(btThem);
		btThem.setBackground(Color.decode("#43ecdb"));
		btThem.setForeground(Color.red);
		btThem.setFont(new Font("Verdana", Font.PLAIN, 12));
		btThem.setText("Thêm");
		btThem.setSize(20, 10);

		// xoa
		btXoa = new JButton();
		myprops.BtnFlat(btXoa);
		btXoa.setBackground(Color.decode("#43d1ec"));
		btXoa.setForeground(Color.red);
		btXoa.setFont(new Font("Verdana", Font.PLAIN, 12));
		btXoa.setText("Xóa");

		// sua
		btSua = new JButton();
		myprops.BtnFlat(btSua);
		// btSua.setBackground(Color.decode("5fc5e1"));
		btSua.setForeground(Color.red);
		btSua.setFont(new Font("Verdana", Font.PLAIN, 12));
		btSua.setText("Sửa");

		pnlButton.add(btThem);
		pnlButton.add(btSua);
		pnlButton.add(btXoa);

		cons = myprops.MyGridBagConstraints(4, 1, 1, 1, true, true);
		pnlForm.add(pnlButton, cons);
	}

}
