package bus;

import dao.LuongDAO;
import dto.LuongDTO;
import java.util.ArrayList;

public class LuongBUS {
	private LuongDAO luongDAO = new LuongDAO();

	public ArrayList<LuongDTO> LuongAll() {
		return luongDAO.LuongAll();
	}

	public void LuongAdd(LuongDTO l) {
		luongDAO.LuongAdd(l);
	}

	public void LuongExit(LuongDTO l) {
		luongDAO.LuongEdit(l);
	}

	public void LuongDelete(int maluong) {
		luongDAO.LuongDelete(maluong);
	}
}
