package bus;

import java.util.ArrayList;

import dao.HopDongLaoDongDAO;
import dao.NhanVienDAO;
import dto.HopDongLaoDongDTO;
import dto.NhanVienDTO;
import gui.MyProps;

public class NhanVienBUS {
	private NhanVienDAO nvDAO = new NhanVienDAO();
	private MyProps myProps = new MyProps();
	
	public ArrayList<NhanVienDTO> NhanVienAll() {
        return nvDAO.NhanVienAll(null);
    }
	
	public ArrayList<NhanVienDTO> NhanVienTheoPhongBan(int maPb) {
		return nvDAO.NhanVienAll(maPb);
	}
	
	public void NhanVienAdd(NhanVienDTO nv) {
		nvDAO.NhanVienAdd(nv);
		
		nv = nvDAO.NhanVienMoiNhat();
		
		// them hdld
		HopDongLaoDongDAO hdDAO = new HopDongLaoDongDAO();
		HopDongLaoDongDTO hd = new HopDongLaoDongDTO();
		
		String currentDate = myProps.currentDate();
		
        hd.setMaNV(nv.getMaNV());
        hd.setTuNgay(currentDate);
        hd.setDenNgay("2099-12-31");
        hd.setDiaDiemLamViec("Tp HCM");
        hd.setThoiGianLamViec(8);
        hd.setHeSoLuong(1.0);
        hd.setMaCV(1);
        hd.setMaPhong(null);
		
        hdDAO.HopDongLaoDongAdd(hd);
	}
	
	public void NhanVienEdit(NhanVienDTO nv) {
		nvDAO.NhanVienEdit(nv);
	}
	
	public void NhanVienDelete(int maNv) {
		nvDAO.NhanVienDelete(maNv);
	}
}
