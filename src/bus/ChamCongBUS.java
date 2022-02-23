
package bus;

import dao.ChamCongDAO;
import dto.ChamCongDTO;
import java.util.ArrayList;

public class ChamCongBUS {
	private ChamCongDAO chamcongDAO = new ChamCongDAO();

	public ArrayList<ChamCongDTO> ChamCongAll() {
		return chamcongDAO.ChamCongAll();
	}

	public void ChamCongAdd(ChamCongDTO chamcong) {
		chamcongDAO.ChamCongAdd(chamcong);
	}

	public void ChamCongEdit(ChamCongDTO chamcong) {
		chamcongDAO.ChamCongEdit(chamcong);
	}

	public void ChamCongDelete(int machamcong) {
		chamcongDAO.ChamCongDelete(machamcong);
	}
	
	public ChamCongDTO ChamCongGet(int maNv, String thang) {
		return chamcongDAO.ChamCongGet(maNv, thang);
	}
}
