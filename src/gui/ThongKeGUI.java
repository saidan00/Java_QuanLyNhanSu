package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bus.BangChamCongBUS;
import bus.ChamCongBUS;
import bus.HopDongLaoDongBUS;
import bus.LuongBUS;
import bus.NhanVienBUS;
import bus.PhongBanBUS;
import dto.NhanVienDTO;

public class ThongKeGUI extends JPanel {
	NhanVienBUS nvBUS = new NhanVienBUS();
	PhongBanBUS pbBUS = new PhongBanBUS();
	ChamCongBUS ccBUS = new ChamCongBUS();
	LuongBUS lgBUS = new LuongBUS();
	BangChamCongBUS bccBUS = new BangChamCongBUS();
	HopDongLaoDongBUS hdBUS = new HopDongLaoDongBUS();

	MyProps myProps = new MyProps();

	JTable tblTK;

	JLabel lblOption;
	JLabel lblThang;
	JLabel lblNam;
	JLabel lblTu, lblDen;
	JLabel lblNV;

	JPanel pnlForm;

	JComboBox<String> boxOption;
	JComboBox<Integer> boxThang1;
	JComboBox<Integer> boxNam1;
	JComboBox<Integer> boxThang2;
	JComboBox<Integer> boxNam2;

	JButton btnPrint;
	JButton btnChon;
	JButton btnChonNV;

	JTextField txtNV;

	String option = "1";
	int maNv = 0;

	final String LUA_CHON = "Chọn";
	final String THANG = "Tháng";
	final String NAM = "Năm";

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
		boxOptionListener();
		btnChonNvClicked();
		btnPrintClicked();
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
		lstOption.add("1. Lương NV theo tháng");
		lstOption.add("2. Quá trình công tác");
//		lstOption.add("3. Hello 3");

		String[] arrayOption = lstOption.toArray(new String[0]);

		boxOption = new JComboBox<String>(arrayOption);
		boxOption.setFont(myProps.DEFAULT_FONT_SMALL);

		lblNV = new JLabel("NV");
		lblNV.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		txtNV = new JTextField(10);
		txtNV.setEditable(false);

		btnChonNV = new JButton("...");
		myProps.BtnFlat(btnChonNV);
		btnChonNV.setBackground(Color.decode("#e0e0e0"));
		btnChonNV.setForeground(Color.BLACK);
		btnChonNV.setFont(myProps.DEFAULT_FONT_SMALL);

		lblThang = new JLabel(THANG);
		lblThang.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		lblNam = new JLabel(NAM);
		lblNam.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		lblTu = new JLabel("Từ");
		lblTu.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);
		lblDen = new JLabel("Đến");
		lblDen.setFont(myProps.DEFAULT_FONT_SMALL_BOLD);

		// combo box tháng
		ArrayList<Integer> lstThang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			lstThang.add(i);
		}

		Integer[] arrayThang = lstThang.toArray(new Integer[0]);

		boxThang1 = new JComboBox<Integer>(arrayThang);
		boxThang1.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		ArrayList<Integer> lstNam = new ArrayList<Integer>();
		for (int i = 2017; i <= 2019; i++) {
			lstNam.add(i);
		}

		Integer[] arrayNam = lstNam.toArray(new Integer[0]);

		boxNam1 = new JComboBox<Integer>(arrayNam);
		boxNam1.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box tháng
		boxThang2 = new JComboBox<Integer>(arrayThang);
		boxThang2.setFont(myProps.DEFAULT_FONT_SMALL);

		// combo box năm
		boxNam2 = new JComboBox<Integer>(arrayNam);
		boxNam2.setFont(myProps.DEFAULT_FONT_SMALL);

		pnlForm.add(lblOption);
		pnlForm.add(boxOption);

		pnlForm.add(lblNV);
		pnlForm.add(txtNV);
		pnlForm.add(btnChonNV);

		pnlForm.add(lblTu);
		lblTu.setVisible(false);

		pnlForm.add(lblThang);
		pnlForm.add(boxThang1);

		pnlForm.add(lblNam);
		pnlForm.add(boxNam1);

		pnlForm.add(lblDen);
		lblDen.setVisible(false);

		pnlForm.add(boxThang2);
		pnlForm.add(boxNam2);
		boxThang2.setVisible(false);
		boxNam2.setVisible(false);
	}

	private void initButton() {
		btnChon = new JButton("Thống kê");
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#4caf50"));
		btnChon.setForeground(Color.WHITE);
		btnChon.setFont(myProps.DEFAULT_FONT_SMALL);

		btnPrint = new JButton("In");
		myProps.BtnFlat(btnPrint);
		btnPrint.setBackground(Color.decode("#e0e0e0"));
		btnPrint.setForeground(Color.BLACK);
		btnPrint.setFont(myProps.DEFAULT_FONT_SMALL);

		pnlForm.add(btnChon);
		pnlForm.add(btnPrint);
	}

	private void initTableThongKe() {
		tblTK = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};

//		tblNV = new JTable();
		tblTK.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// không cho phép di chuyển vị trí columns
		tblTK.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
//		tblChamCong.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
//		tblChamCong.setAutoCreateRowSorter(true);

//		tblTK.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblTK, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 2, 6, 5, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(scroll, cons);
	}

	private void setModelTableOption1() {
		// table header

		DefaultTableModel dtm = lgBUS.LuongGet(maNv, Integer.valueOf(boxThang1.getSelectedItem().toString()),
				Integer.valueOf(boxNam1.getSelectedItem().toString()));
		if (dtm != null) {
			tblTK.setModel(dtm);
		} else {
			dtm = new DefaultTableModel();
			tblTK.setModel(dtm);
		}
	}

	private void setModelTableOption2() {
		// table header
		int thang1 = Integer.valueOf(boxThang1.getSelectedItem().toString());
		int thang2 = Integer.valueOf(boxThang2.getSelectedItem().toString());
		int nam1 = Integer.valueOf(boxNam1.getSelectedItem().toString());
		int nam2 = Integer.valueOf(boxNam2.getSelectedItem().toString());
		DefaultTableModel dtm = hdBUS.QuaTrinhCongTac(maNv, thang1, nam1, thang2, nam2);
		tblTK.setModel(dtm);
	}

	private void btnThongKeClicked() {
		ActionListener btnChonClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (option) {
				case "1":
					setModelTableOption1();
					break;
				case "2":
					setModelTableOption2();
					break;
				case "3":
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + option);
				}
			}
		};

		btnChon.addActionListener(btnChonClick);
	}

	private void boxOptionListener() {
		ActionListener boxOptionSelect = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option = (String) boxOption.getSelectedItem();
				option = option.substring(0, 1); // lấy số thống kê
//				System.out.print(option);
				switch (option) {
				case "1":
					option2Hide();

					option1Show();
					break;
				case "2":
					option1Hide();

					option2Show();
					break;
				case "3":
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + option);
				}
			}
		};

		boxOption.addActionListener(boxOptionSelect);
	}

	private void btnChonNvClicked() {
		btnChonNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameNV();
			}
		});
	}

	private void FrameNV() {
		JFrame nvFrame = new JFrame("Chọn nhân viên");

		nvFrame.setSize(MyProps.DEFAULT_WIDTH / 2, MyProps.DEFAULT_HEIGHT / 2);
		nvFrame.setResizable(false);
		nvFrame.setVisible(true);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		int xLocation = (screenWidth - MyProps.DEFAULT_WIDTH / 2) / 2;
		int yLocation = ((screenHeight - MyProps.DEFAULT_HEIGHT / 2) / 2 - 25);
		nvFrame.setLocation(xLocation, yLocation);

		nvFrame.setLayout(new GridLayout(1, 1));
		nvFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pnlTblNvTemp = new JPanel();
		pnlTblNvTemp.setLayout(new GridBagLayout());

		nvFrame.add(pnlTblNvTemp);

		JTable tblNvTemp = new JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}

			@Override
			public Dimension getPreferredScrollableViewportSize() {
				return new Dimension(100, 100);
			}
		};

		// không cho phép di chuyển vị trí columns
		tblNvTemp.getTableHeader().setReorderingAllowed(false);

		// không cho phép resize column
		tblNvTemp.getTableHeader().setResizingAllowed(false);

		// sắp xếp khi click header
		tblNvTemp.setAutoCreateRowSorter(true);

		// đọc dữ liệu
		ArrayList<NhanVienDTO> lstNV = nvBUS.NhanVienAll("");

		// table header
		Vector<String> header = new Vector<String>();
//		header.add("Mã khen thưởng");
//		header.add("Nhân viên");
//		header.add("Ngày");
//		header.add("Hình thức");
//		header.add("Lý do");
//		header.add("Tiền thưởng");

		header.add("Mã NV");
		header.add("Nhân viên");

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

		NhanVienDTO nv = new NhanVienDTO();

		for (int i = 0; i < lstNV.size(); i++) {
			nv = lstNV.get(i);
			Object[] row = { nv.getMaNV(), nv.getHoNV() + " " + nv.getTenNV() };
			dtm.addRow(row);
		}

		tblNvTemp.setModel(dtm);

		// scroll bar
		JScrollPane scroll = new JScrollPane(tblNvTemp);

		GridBagConstraints cons = myProps.MyGridBagConstraints(1, 1, 1, 1, true, true);
		cons.weightx = 1.0;
		cons.weighty = 1.0;

		pnlTblNvTemp.add(scroll, cons);

		JButton btnChon = new JButton("Chọn");
		cons = myProps.MyGridBagConstraints(1, 2, 1, 1, false, true);
		pnlTblNvTemp.add(btnChon, cons);
		myProps.BtnFlat(btnChon);
		btnChon.setBackground(Color.decode("#e0e0e0"));
		btnChon.setForeground(Color.BLACK);
		btnChon.setFont(new Font("Arial Nova", Font.PLAIN, 12));

		btnChon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblNvTemp.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn NV");
				} else {
					maNv = (int) tblNvTemp.getValueAt(row, 0);
					NhanVienDTO nv = nvBUS.NhanVienGet(maNv);

					txtNV.setText(nv.getHoNV() + " " + nv.getTenNV());

					nvFrame.dispatchEvent(new WindowEvent(nvFrame, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
	}

	private void option1Show() {
		lblNV.setVisible(true);
		txtNV.setVisible(true);
		btnChonNV.setVisible(true);
		lblThang.setVisible(true);
		lblNam.setVisible(true);
		boxThang1.setVisible(true);
		boxNam1.setVisible(true);
	}

	private void option1Hide() {
		lblNV.setVisible(false);
		txtNV.setVisible(false);
		btnChonNV.setVisible(false);
		lblThang.setVisible(false);
		lblNam.setVisible(false);
		boxThang1.setVisible(false);
		boxNam1.setVisible(false);
	}

	private void option2Show() {
		lblNV.setVisible(true);
		txtNV.setVisible(true);
		btnChonNV.setVisible(true);
		lblTu.setVisible(true);
		lblDen.setVisible(true);
		boxNam2.setVisible(true);
		boxThang2.setVisible(true);
		boxThang1.setVisible(true);
		boxNam1.setVisible(true);
	}

	private void option2Hide() {
		lblTu.setVisible(false);
		lblDen.setVisible(false);
		boxNam2.setVisible(false);
		boxThang2.setVisible(false);
		boxThang1.setVisible(false);
		boxNam1.setVisible(false);
	}

	private void btnPrintClicked() {
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				writeToPdf();
			}
		});
	}

	private void writeToPdf() {
		if (tblTK.getModel().getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Không có dữ liệu");
			return;
		}
		
		switch (option) {
			case "1":
				writeToPdfOption1();
				break;
			case "2":
				writeToPdfOption2();
				break;
		}
	}

	private void writeToPdfOption1() {
		Date date = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		String fileName = txtNV.getText().replaceAll("\\s+", "");
//		String fileName = "hello";
		
		if (fileName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
			return;
		}

		String filePath = "src\\pdf\\Luong_" + fileName + "_" + timeStamp + ".pdf";

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return;
		}

		document.open();
		com.itextpdf.text.Font font_medium;
		com.itextpdf.text.Font font_small;
		try {
			font_medium = new com.itextpdf.text.Font(
					BaseFont.createFont("src\\font\\UVNMayChuP.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			font_small = new com.itextpdf.text.Font(
					BaseFont.createFont("src\\font\\UVNMayChuP.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			font_medium.setSize(16);
			font_small.setSize(13);
			
			int lgCb = Integer.parseInt(tblTK.getValueAt(0, 0).toString());
			int khenThg = Integer.parseInt(tblTK.getValueAt(0, 1).toString());
			double phucap = Double.parseDouble(tblTK.getValueAt(0, 2).toString());
			double ngaynghi = Double.parseDouble(tblTK.getValueAt(0, 3).toString());
			int truluong = Integer.parseInt(tblTK.getValueAt(0, 4).toString());
			double tonglg = Double.parseDouble(tblTK.getValueAt(0, 5).toString());

			Paragraph header = new Paragraph("HR Manager: Lương " + txtNV.getText() + "\n", font_medium);
			Paragraph printDate = new Paragraph("Ngày in: " + date + "\n", font_small);
			Paragraph line = new Paragraph("--------------------\n", font_small);
			

			Paragraph luongCB = new Paragraph("Lương cơ bản: " + lgCb + "\n", font_small);
			Paragraph khenThuong = new Paragraph("Khen thưởng: " + khenThg + "\n", font_small);
			Paragraph phuCap = new Paragraph("Phụ cấp: " + phucap + "\n", font_small);
			Paragraph ngayNghi = new Paragraph("Ngày nghỉ: " + ngaynghi + "\n", font_small);
			Paragraph truLg = new Paragraph("Trừ lương: " + truluong + "\n", font_small);
			Paragraph tong = new Paragraph("Tổng: " + tonglg + "\n", font_small);

			try {
				header.setAlignment(Element.ALIGN_CENTER);
				printDate.setAlignment(Element.ALIGN_CENTER);
				line.setAlignment(Element.ALIGN_CENTER);
				document.add(header);
				document.add(printDate);
				document.add(line);
				document.add(luongCB);
				document.add(khenThuong);
				document.add(phuCap);
				document.add(ngayNghi);
				document.add(truLg);
				document.add(tong);
			} catch (DocumentException e) {
				e.printStackTrace();
				return;
			}
		} catch (DocumentException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		document.close();
		filePath = filePath.replace("\\", "/");
		JOptionPane.showMessageDialog(null, "File đã được in tại " + filePath);
	}
	
	private void writeToPdfOption2() {
		Date date = new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		String fileName = txtNV.getText().replaceAll("\\s+", "");
//		String fileName = "hello";
		
		if (fileName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên");
			return;
		}

		String filePath = "src\\pdf\\CongTac_" + fileName + "_" + timeStamp + ".pdf";

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return;
		}

		document.open();
		com.itextpdf.text.Font font_medium;
		com.itextpdf.text.Font font_small;
		try {
			font_medium = new com.itextpdf.text.Font(
					BaseFont.createFont("src\\font\\UVNMayChuP.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			font_small = new com.itextpdf.text.Font(
					BaseFont.createFont("src\\font\\UVNMayChuP.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
			font_medium.setSize(16);
			font_small.setSize(13);

			Paragraph header = new Paragraph("HR Manager: Quá trình công tác " + txtNV.getText() + "\n", font_medium);
			Paragraph printDate = new Paragraph("Ngày in: " + date + "\n", font_small);
			Paragraph line = new Paragraph("--------------------\n", font_small);
			
			PdfPTable pdfTable = new PdfPTable(tblTK.getColumnCount());
            //adding table headers
            for (int i = 0; i < tblTK.getColumnCount(); i++) {
                pdfTable.addCell(new PdfPCell(new Phrase(tblTK.getColumnName(i), font_small)));
            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < tblTK.getRowCount(); rows++) {
                for (int cols = 0; cols < tblTK.getColumnCount(); cols++) {
                    pdfTable.addCell(new PdfPCell(new Phrase(tblTK.getModel().getValueAt(rows, cols).toString(), font_small)));
                }
            }

			try {
				header.setAlignment(Element.ALIGN_CENTER);
				printDate.setAlignment(Element.ALIGN_CENTER);
				line.setAlignment(Element.ALIGN_CENTER);
				document.add(header);
				document.add(printDate);
				document.add(line);
				document.add(pdfTable);
			} catch (DocumentException e) {
				e.printStackTrace();
				return;
			}
		} catch (DocumentException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		document.close();
		filePath = filePath.replace("\\", "/");
		JOptionPane.showMessageDialog(null, "File đã được in tại " + filePath);
	}
}
