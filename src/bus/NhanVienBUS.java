package bus;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.HopDongLaoDongDAO;
import dao.NhanVienDAO;
import dto.HopDongLaoDongDTO;
import dto.NhanVienDTO;
import gui.MyProps;

public class NhanVienBUS {
	private NhanVienDAO nvDAO = new NhanVienDAO();
	private MyProps myProps = new MyProps();

	public ArrayList<NhanVienDTO> NhanVienAll(String tk) {
		return nvDAO.NhanVienAll(tk);
	}

	public ArrayList<NhanVienDTO> NhanVienTheoPhongBan(Integer maPb) {
		return nvDAO.NhanVienTheoPhongBan(maPb);
	}

	public boolean NhanVienAdd(NhanVienDTO nv) {
		if (nvDAO.NhanVienGetCMND(nv.getSoCMND()) != null) {
			JOptionPane.showMessageDialog(null, "Số CMND trùng");
			return false;
		}
		
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
		
		return true;
	}

	public boolean NhanVienEdit(NhanVienDTO nv) {
		if (nvDAO.NhanVienGetCMND(nv.getSoCMND()) != null) {
			JOptionPane.showMessageDialog(null, "Số CMND trùng");
			return false;
		}
		
		nvDAO.NhanVienEdit(nv);
		return true;
	}

	public void NhanVienDelete(int maNv) {
		nvDAO.NhanVienDelete(maNv);
	}
	
	public NhanVienDTO NhanVienGet(int maNv) {
		return nvDAO.NhanVienGet(maNv);
	}
	
	public ArrayList<NhanVienDTO> NhanVienTheoLuong(int maLg) {
		return nvDAO.NhanVienTheoLuong(maLg);
	}
}
