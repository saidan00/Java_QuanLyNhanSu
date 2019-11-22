package bus;

import dao.NhanVienDAO;
import dao.PhongBanDAO;
import dto.PhongBanDTO;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PhongBanBUS {
	private PhongBanDAO PhongBanDAO = new PhongBanDAO();
	private NhanVienDAO nvDAO = new NhanVienDAO();

	public ArrayList<PhongBanDTO> PhongBanAll() {
		return PhongBanDAO.PhongBanAll();
	}

	public void PhongBanAdd(PhongBanDTO pb) {
		PhongBanDAO.PhongBanAdd(pb);
	}

	public void PhongBanEdit(PhongBanDTO pb) {
		PhongBanDAO.PhongBanEdit(pb);
	}

	public void PhongBanDelete(int maPhongBan) {
		if (nvDAO.NhanVienTheoPhongBan(maPhongBan).size() != 0) {
			JOptionPane.showMessageDialog(null, "Không thể xóa phòng có nhân viên");
		} else {
			PhongBanDAO.PhongBanDelete(maPhongBan);

			JOptionPane.showMessageDialog(null, "Xóa thành công");
		}
	}

	public void CapNhatTruongPhong(int maPhongBan, int maTruongPhong) {
		PhongBanDAO.CapNhatTruongPhong(maPhongBan, maTruongPhong);
	}
}
