package bus;

import dao.HopDongLaoDongDAO;
import dto.HopDongLaoDongDTO;
import java.util.ArrayList;

public class HopDongLaoDongBUS {
    private HopDongLaoDongDAO hdldDAO = new HopDongLaoDongDAO();
	
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
}
