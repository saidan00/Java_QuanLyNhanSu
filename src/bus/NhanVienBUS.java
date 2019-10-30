package bus;

import java.util.ArrayList;

import dao.NhanVienDAO;
import dto.NhanVienDTO;

public class NhanVienBUS {
	public static ArrayList<NhanVienDTO> NhanVienAll() {
        return NhanVienDAO.NhanVienAll();
    }
}
