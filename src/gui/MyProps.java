package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.RoundedCornerBorder;

public class MyProps {
	public static final int DEFAULT_WIDTH = 1280;
	public static final int DEFAULT_HEIGHT = 720;
	public final Font DEFAULT_FONT = new Font("Arial Nova", Font.PLAIN, 20);
	public final Font DEFAULT_FONT_MEDIUM = new Font("Arial Nova", Font.PLAIN, 16);
	public final Font DEFAULT_FONT_SMALL = new Font("Arial Nova", Font.PLAIN, 12);
	public final Font DEFAULT_FONT_SMALL_BOLD = new Font("Arial Nova", Font.BOLD, 12);
	public final String Color_Teal = "#00796b";
	public final String Color_Teal_Dark = "#004c40";
	public final String Color_Teal_Light = "#48a999";
	public static String CURRENT_USER = "";

	public void DEFAULT_BUTTON(JButton btn, String background, String foreground, Rectangle rec) {

	}

	public String currentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String currentDate = dtf.format(now);
		return currentDate;
	}

	public void BtnFlat(JButton btn) {
		// flat style
//		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
	}

	public void BtnHover(JButton btn) {
		Color originColor = btn.getBackground();

		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btn.setBackground(Color.GRAY);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn.setBackground(originColor);
			}
		});
	}

	public void BtnHover(JButton btn, Color hoverColor) {
		Color originColor = btn.getBackground();

		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				btn.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn.setBackground(originColor);
			}
		});
	}

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	public GridBagConstraints MyGridBagConstraints(int x, int y, int width, int height, boolean isFillWidth,
			boolean isFillHeight) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(10, 10, 10, 10);
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.gridheight = height;

		if (isFillWidth == true && isFillHeight == false) {
			cons.fill = GridBagConstraints.HORIZONTAL;
		} else if (isFillWidth == false && isFillHeight == true) {
			cons.fill = GridBagConstraints.VERTICAL;
		} else if (isFillWidth == true && isFillHeight == true) {
			cons.fill = GridBagConstraints.BOTH;
		}

		return cons;
	}

	public JTextField RoundedTextField(int columns) {
		JTextField txt = new JTextField(columns) {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};

		return txt;
	}

	public void exportDataToExcel(DefaultTableModel model) {
		if (model == null) {
			JOptionPane.showMessageDialog(null, "Không có dữ liệu");
			return;
		}
//		String excelImagePath = "";

		// First Download Apache POI Library For Dealing with excel files.
		// Then add the library to the current project
		FileOutputStream excelFos = null;
		XSSFWorkbook excelJTableExport = null;
//		BufferedOutputStream excelBos = null;
		try {
			// Choosing Saving Location
			// Set default location to C:\Users\Admin\Desktop or your preferred location
			JFileChooser excelFileChooser = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
			
			// Dialog box title
			excelFileChooser.setDialogTitle("Save As ..");
			
			// Filter only xls, xlsx, xlsm files
			FileNameExtensionFilter fnef = new FileNameExtensionFilter(".xls, .xlsx, .xlsm", "xls", "xlsx", "xlsm");
			
			// Setting extension for selected file names
			excelFileChooser.setFileFilter(fnef);
			int chooser = excelFileChooser.showSaveDialog(null);
			
			// Check if save button has been clicked
			if (chooser == JFileChooser.APPROVE_OPTION) {
				// If button is clicked execute this code
				excelJTableExport = new XSSFWorkbook();
				XSSFSheet excelSheet = excelJTableExport.createSheet("Jtable Export");
				XSSFRow excelRow;
				XSSFCell excelCell;
				
				XSSFFont font = excelJTableExport.createFont();
				font.setFontHeightInPoints((short) 12);
				font.setBold(true);

				XSSFCellStyle style = excelJTableExport.createCellStyle();
				style.setFont(font);
				
//				for (int i = 0; i < model.getRowCount(); i++) {
//					excelRow = excelSheet.createRow(i);
//					for (int j = 0; j < model.getColumnCount(); j++) {
//						excelCell = excelRow.createCell(j);
//
//						// Change the image column to output image path
//						// Fourth column contains images
//                        if (j == model.getColumnCount() - 1) {
//                            JLabel excelJL = new JLabel((String) model.getValueAt(i, j));
//                            ImageIcon excelImageIcon = (ImageIcon) excelJL.getIcon();
//                            // Image Name Is Stored In ImageIcons Description first set it when saving image in the jtable cell and then retrieve it.
//                            excelImagePath = excelImageIcon.getDescription();
//                        }
//
//						excelCell.setCellValue(model.getValueAt(i, j).toString());
//                        if (excelCell.getColumnIndex() == model.getColumnCount() - 1) {
//                            excelCell.setCellValue(excelImagePath);
//                        }
//					}
//				}
				
				// Loop through the jtable columns and rows to get its values
				for (int i = 0; i <= model.getRowCount(); i++) {
					excelRow = excelSheet.createRow(i);
					for (int j = 0; j < model.getColumnCount(); j++) {
						excelCell = excelRow.createCell(j);

						if (i == 0) {
							excelCell.setCellValue(model.getColumnName(j));
							excelCell.setCellStyle(style);
						} else {
							excelCell.setCellValue(model.getValueAt(i - 1, j).toString());
						}
					}
				}
				
				excelFos = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
//				excelBos = new BufferedOutputStream(excelFos);
				excelJTableExport.write(excelFos);
				JOptionPane.showMessageDialog(null, "Exported Successfully");
			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex);
		} finally {
			try {
				if (excelFos != null) {
					excelFos.close();
				}
//				excelBos.close();
				if (excelJTableExport != null) {
					excelJTableExport.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// hàm tham khảo, KHÔNG DÙNG HÀM NÀY
	public void ExportExcelTable(DefaultTableModel table) {
		XSSFWorkbook workbook = null;
		FileOutputStream out = null;
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("report");

			XSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setBold(true);

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFont(font);

			XSSFRow row = sheet.createRow(0);
			XSSFCell cell;

			// set cell value
			int rowCount = table.getRowCount();
			int colCount = table.getColumnCount();

//			JTableHeader header = table.getTableHeader();

			for (int i = 0; i <= rowCount; i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < colCount; j++) {
					cell = row.createCell(j);

					if (i == 0) {
						cell.setCellValue(table.getColumnName(j));
						cell.setCellStyle(style);
					} else {
						cell.setCellValue(table.getValueAt(i - 1, j).toString());
					}
				}
			}

			for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
				sheet.autoSizeColumn((short) (colNum));
			}

			out = new FileOutputStream(new File("./reports/report.xlsx"));
			workbook.write(out);
			System.out.println("Xuat file thanh cong");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
