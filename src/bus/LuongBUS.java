package bus;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.BangChamCongDAO;
import dao.ChamCongDAO;
import dao.ChucVuDAO;
import dao.HopDongLaoDongDAO;
import dao.KhenThuongDAO;
import dao.LuongDAO;
import dao.NhanVienDAO;
import dto.BangChamCongDTO;
import dto.ChamCongDTO;
import dto.ChucVuDTO;
import dto.KhenThuongDTO;
import dto.LuongDTO;

public class LuongBUS {
	private LuongDAO luongDAO = new LuongDAO();
	private NhanVienDAO nvDAO = new NhanVienDAO();
	private ChamCongDAO ccDAO = new ChamCongDAO();
	private BangChamCongDAO bccDAO = new BangChamCongDAO();
	private KhenThuongDAO ktDAO = new KhenThuongDAO();
	private HopDongLaoDongDAO hdDAO = new HopDongLaoDongDAO();
	private ChucVuDAO cvDAO = new ChucVuDAO();

	public ArrayList<LuongDTO> LuongAll() {
		return luongDAO.LuongAll();
	}

	public DefaultTableModel LuongGet(int maNv, int thang, int nam) {
		if (maNv == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn NV");
			return null;
		}
		Vector<String> header = new Vector<String>();
		header.add("Lương CB");
		header.add("Khen thưởng");
		header.add("Phụ cấp");
		header.add("Số ngày nghỉ");
		header.add("Trừ lương");
		header.add("Tổng");

		DefaultTableModel dtm = new DefaultTableModel(header, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				default:
					return Integer.class;
				}
			}
		};
		
		String thangString = nam + "-" + thang + "-1";

		int luongCb = luongDAO.LuongGet(maNv);
		
		int luongTru = 0;
		
		int khenThuong = 0;
		
		double phuCap = 0;
		
		double tongLuong = 0;

		ChamCongDTO chamCong = ccDAO.ChamCongGet(maNv, thangString);

		ArrayList<BangChamCongDTO> bangChamCong = bccDAO.BangChamCongGet(chamCong.getMaChamCong());
		int dateCount = 0;

		switch (thang) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dateCount = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				dateCount = 30;
				break;
			case 2:
				if (nam % 4 == 0) {
					dateCount = 29;
				} else {
					dateCount = 28;
				}
				break;
		}
		
		// chấm công
		double soNgayNghi = 0;
		if (bangChamCong.size() < dateCount) {
			JOptionPane.showMessageDialog(null, "Chưa đủ chấm công");
			return null;
		} else {
			for (int i = 0; i < bangChamCong.size(); i++) {
				BangChamCongDTO bccDto = bangChamCong.get(i);
				String trangThai = bccDto.getTrangThai();
				switch (trangThai) {
				case "x":
					break;
				case "n":
					luongTru += luongCb/dateCount;
					soNgayNghi += 1;
					break;
				case "null":
					break;
				case "1/2":
					luongTru += (luongCb/dateCount)/2;
					soNgayNghi += 0.5;
					break;
				}
			}
		}
		
		// khen thưởng
		ArrayList<KhenThuongDTO> khenThuongDTOs = ktDAO.KhenThuongGet(maNv, thang, nam);
		for (int i = 0; i < khenThuongDTOs.size(); i++) {
			khenThuong += khenThuongDTOs.get(i).getTienThuong();
		}
		
		// phụ cấp
		int maChucVu = hdDAO.HopDongMoiNhat(maNv).getMaCV();
		
		ChucVuDTO cvDTO = cvDAO.ChucVuGet(maChucVu);
		
		phuCap = cvDTO.getPhuCap();
		
		tongLuong = luongCb + khenThuong + luongCb * phuCap - luongTru;
		
		Object[] rowData = {luongCb, khenThuong, phuCap, soNgayNghi, luongTru, tongLuong};
		dtm.addRow(rowData);
		
		return dtm;
	}

	public void LuongAdd(LuongDTO l) {
		luongDAO.LuongAdd(l);
	}

	public void LuongEdit(LuongDTO l) {
		luongDAO.LuongEdit(l);
	}

	public void LuongDelete(int maluong) {
		if (nvDAO.NhanVienTheoLuong(maluong).size() != 0) {
			JOptionPane.showMessageDialog(null, "Không thể xóa lương có nhân viên");
		} else {
			luongDAO.LuongDelete(maluong);

			JOptionPane.showMessageDialog(null, "Xóa thành công");
		}
	}
}
