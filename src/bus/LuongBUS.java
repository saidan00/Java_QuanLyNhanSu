package bus;

import dao.LuongDAO;
import dao.NhanVienDAO;
import dto.LuongDTO;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LuongBUS {
	private LuongDAO luongDAO = new LuongDAO();
	private NhanVienDAO nvDAO = new NhanVienDAO();

	public ArrayList<LuongDTO> LuongAll() {
		return luongDAO.LuongAll();
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
