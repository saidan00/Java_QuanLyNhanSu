package bus;

import dao.ChucVuDAO;
import dao.HopDongLaoDongDAO;
import dao.PhongBanDAO;
import dto.HopDongLaoDongDTO;
import dto.PhongBanDTO;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HopDongLaoDongBUS {
	private HopDongLaoDongDAO hdldDAO = new HopDongLaoDongDAO();
	private ChucVuDAO cvDAO = new ChucVuDAO();
	private PhongBanDAO pbDAO = new PhongBanDAO();

	public ArrayList<HopDongLaoDongDTO> HopDongLaoDongAll() {
		return hdldDAO.HopDongLaoDongAll();
	}

	public void HpDongLaoDongAdd(HopDongLaoDongDTO hdld) {
		hdldDAO.HopDongLaoDongAdd(hdld);
	}

	public void HopDongLaoDongEdit(HopDongLaoDongDTO hdld) {
		hdldDAO.HopDongLaoDongEdit(hdld);
	}

	public void HopDongLaoDongDelete(int maHD) {
		hdldDAO.HopDongLaoDongDelete(maHD);
	}

	public String ChucVuCuaNhanVien(int maNv) {
		return hdldDAO.ChucVuCuaNhanVien(maNv);
	}

	public HopDongLaoDongDTO HopDongMoiNhat(int maNv) {
		return hdldDAO.HopDongMoiNhat(maNv);
	}

	public void CapNhatTruongPhong(int maPb, int maNv, HopDongLaoDongDTO hd) {
		hdldDAO.CapNhatTruongPhong(maPb, maNv, hd);
	}

	public void XoaNhanVienKhoiPhongBan(int maNv, int maPb) {
		hdldDAO.XoaNhanVienKhoiPhongBan(maNv, maPb);
	}

	public void ThemNhanVienVaoPhongBan(HopDongLaoDongDTO hd, int maPb) {
		hdldDAO.ThemNhanVienPhongBan(hd, maPb);
	}
	
	public DefaultTableModel QuaTrinhCongTac(int maNv, int thang1, int nam1, int thang2, int nam2) {
		if (maNv == 0) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn NV");
			return null;
		}
		Vector<String> header = new Vector<String>();
		header.add("Từ");
		header.add("Đến");
		header.add("Chức vụ");
		header.add("Phòng ban");

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
		
		String thang1String = nam1 + "-" + thang1 + "-1";
		String thang2String = nam2 + "-" + thang2 + "-1";
		
		ArrayList<HopDongLaoDongDTO> lstHDLD = hdldDAO.hoDongLaoDongGet(maNv, thang1String, thang2String);
		
		for (int i = 0; i < lstHDLD.size(); i++) {
			HopDongLaoDongDTO hd = lstHDLD.get(i);
			String chucVu = cvDAO.ChucVuGet(hd.getMaCV()).getTenCV();
			PhongBanDTO phongBanDTO = pbDAO.PhongBanGet(hd.getMaPhong());
			String phongBan = phongBanDTO == null ? "Không" : phongBanDTO.getTenPhong();
			Object[] rowData = {hd.getTuNGay(), hd.getDenNgay(), chucVu, phongBan};
			dtm.addRow(rowData);
		}
		
		return dtm;
	}
}
