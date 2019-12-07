package bus;

import dao.KhenThuongDAO;
import dto.KhenThuongDTO;
import java.util.ArrayList;

public class KhenThuongBUS {
	private KhenThuongDAO khen = new KhenThuongDAO();

	public ArrayList<KhenThuongDTO> KhenThuongAll() {
		return khen.KhenThuongAll();
	}
	
	public KhenThuongDTO KhenThuongGet(int maKt) {
		return khen.KhenThuongGet(maKt);
	}

	public void KhenThuongAdd(KhenThuongDTO kt) {
		khen.KhenThuongAdd(kt);
	}

	public void KhenThuongEdit(KhenThuongDTO kt) {
		khen.KhenThuongEdit(kt);
	}

	public void KhenThuongDelete(int makhenthuong) {
		khen.KhenThuongDelete(makhenthuong);
	}
}
