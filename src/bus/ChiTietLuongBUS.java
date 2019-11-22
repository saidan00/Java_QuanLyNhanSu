package bus;

import dao.ChiTietLuongDAO;
import dto.ChiTietLuongDTO;
import java.util.ArrayList;

public class ChiTietLuongBUS {
	private ChiTietLuongDAO luongDAO = new ChiTietLuongDAO();

	public ArrayList<ChiTietLuongDTO> ChiTietLuongAll() {
		return luongDAO.ChiTietLuongAll();
	}

	public void ChiTietLuongAdd(ChiTietLuongDTO l) {
		luongDAO.ChiTietLuongAdd(l);
	}

	public void ChiTietLuongExit(ChiTietLuongDTO l) {
		luongDAO.ChiTietLuongEdit(l);
	}

	public void ChiTietLuongDelete(int maluong) {
		luongDAO.ChiTietLuongDelete(maluong);
	}
}