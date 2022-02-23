/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import java.util.ArrayList;

import dao.TaiKhoanDAO;
import dto.TaiKhoanDTO;
import gui.MyProps;
import util.BCrypt;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanBUS {
	private TaiKhoanDAO tkDAO = new TaiKhoanDAO();

	public ArrayList<TaiKhoanDTO> taiKhoanAll() {
		return tkDAO.TaiKhoanAll();
	}

	public void TaiKhoanAdd(TaiKhoanDTO tk) {
		tkDAO.TaiKhoanAdd(tk);
	}

	public void TaiKhoanEdit(TaiKhoanDTO tk) {
		tkDAO.TaiKhoanEdit(tk);
	}

	public void TaiKhoanDelete(String tendangnhap) {
		tkDAO.TaiKhoanDelete(tendangnhap);
	}

	public boolean Login(String userName, String password) {
		TaiKhoanDTO taiKhoan = tkDAO.TaiKhoanGet(userName);
		
//		String pwString = BCrypt.hashpw("123", BCrypt.gensalt(12));
//		System.out.println(pwString);
		
		// nếu sai tên đăng nhập
		if (taiKhoan != null) {
	        String hashedPassword = taiKhoan.getMatKhau();
	        boolean matched = BCrypt.checkpw(password, hashedPassword);
	        
	        if (matched) {
	        	MyProps.CURRENT_USER = taiKhoan.getTenDangNhap();
	        	return true;
	        }
		}
		
		return false;
	}
}
