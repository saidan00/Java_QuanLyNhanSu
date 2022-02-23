/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dao.BangChamCongDAO;
import dto.BangChamCongDTO;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BangChamCongBUS {
	private BangChamCongDAO bccDAO = new BangChamCongDAO();

	public ArrayList<BangChamCongDTO> BangChamCongAll(String maChamCong) {
		return bccDAO.BangChamCongAll(maChamCong);
	}

	public void BangChamCongAdd(BangChamCongDTO bcc) {
		bccDAO.BangChamCongAdd(bcc);
	}

	public void BangChamCongEdit(BangChamCongDTO bcc) {
		bccDAO.BangChamCongEdit(bcc);
	}

	public void BangChamCongDelete(int machamcong) {
		bccDAO.BangChamCongDelete(machamcong);
	}

	public BangChamCongDTO BangChamCongGet(int machamcong, int ngay) {
		return bccDAO.BangChamCongGet(machamcong, ngay);
	}
}
