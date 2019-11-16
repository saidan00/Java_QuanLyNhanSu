package bus;

import dao.HopDongLaoDongDAO;
import dto.HopDongLaoDongDTO;
import java.util.ArrayList;

public class HopDongLaoDongBUS {
    private HopDongLaoDongDAO hdldDAO = new HopDongLaoDongDAO();
	
	public ArrayList<HopDongLaoDongDTO> NhanVienAll() {
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
}
