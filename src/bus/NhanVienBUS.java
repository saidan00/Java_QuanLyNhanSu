package bus;

import java.util.ArrayList;

import dao.NhanVienDAO;
import dto.NhanVienDTO;

public class NhanVienBUS {
	private NhanVienDAO nvDAO = new NhanVienDAO();
	
	public ArrayList<NhanVienDTO> NhanVienAll() {
        return nvDAO.NhanVienAll();
    }
}
