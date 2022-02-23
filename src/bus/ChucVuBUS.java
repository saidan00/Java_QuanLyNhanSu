package bus;

import dao.ChucVuDAO;
import dto.ChucVuDTO;
import java.util.ArrayList;

public class ChucVuBUS {
	private ChucVuDAO cvDAO = new ChucVuDAO();

	public ArrayList<ChucVuDTO> ChucVuAll() {
		return cvDAO.ChucVuAll();
	}

	public void ChucVuAdd(ChucVuDTO cv) {
		cvDAO.ChucVuAdd(cv);
	}

	public void ChucVuEdit(ChucVuDTO cv) {
		cvDAO.ChucVuEdit(cv);
	}

	public void ChucVuDelete(int macv) {
		cvDAO.ChucVuDelete(macv);
	}
}
