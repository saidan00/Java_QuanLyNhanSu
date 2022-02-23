package bus;

import dao.NhanVienDAO;
import dao.PhongBanDAO;
import dto.PhongBanDTO;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PhongBanBUS {
	private PhongBanDAO phongBanDAO = new PhongBanDAO();
	private NhanVienDAO nvDAO = new NhanVienDAO();

	public ArrayList<PhongBanDTO> PhongBanAll() {
		return phongBanDAO.PhongBanAll();
	}

	public void PhongBanAdd(PhongBanDTO pb) {
		phongBanDAO.PhongBanAdd(pb);
	}

	public void PhongBanEdit(int maPb, String tenPb) {
		phongBanDAO.DoiTenPhong(maPb, tenPb);
	}

	public void PhongBanDelete(int maPhongBan) {
		if (nvDAO.NhanVienTheoPhongBan(maPhongBan).size() != 0) {
			JOptionPane.showMessageDialog(null, "Không thể xóa phòng có nhân viên");
		} else {
			phongBanDAO.PhongBanDelete(maPhongBan);

			JOptionPane.showMessageDialog(null, "Xóa thành công");
		}
	}

	public void CapNhatTruongPhong(int maPhongBan, int maTruongPhong) {
		phongBanDAO.CapNhatTruongPhong(maPhongBan, maTruongPhong);
	}
	
	public PhongBanDTO PhongBanGet(int maPb) {
		return phongBanDAO.PhongBanGet(maPb);
	}
}
