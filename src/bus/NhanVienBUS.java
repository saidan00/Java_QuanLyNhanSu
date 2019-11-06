package bus;

import java.util.ArrayList;

import dao.NhanVienDAO;
import dto.NhanVienDTO;

public class NhanVienBUS {
	private NhanVienDAO nvDAO = new NhanVienDAO();
	
	public ArrayList<NhanVienDTO> NhanVienAll() {
        return nvDAO.NhanVienAll();
    }
	
	public void NhanVienAdd(NhanVienDTO nv) {
		nvDAO.NhanVienAdd(nv);
	}
	
	public void NhanVienEdit(NhanVienDTO nv) {
		nvDAO.NhanVienEdit(nv);
	}
	
	public void NhanVienDelete(int maNv) {
		nvDAO.NhanVienDelete(maNv);
	}
}
