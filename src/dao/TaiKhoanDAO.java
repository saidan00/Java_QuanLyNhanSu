/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.TaiKhoanDTO;
import util.MySqlDataAccessHelper;

/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {
	// lấy danh sách tài khoản
	public ArrayList<TaiKhoanDTO> TaiKhoanAll() {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<TaiKhoanDTO> arr = new ArrayList<TaiKhoanDTO>();

		String query = "SELECT * FROM taikhoan";
		try {
			ResultSet rs = conn.executeQuery(query);

			while (rs.next()) {
				// khởi tạo
				TaiKhoanDTO aTaiKhoan = new TaiKhoanDTO();
				// gán giá trị
				aTaiKhoan.setTenDangNhap(rs.getString("tendangnhap"));
				aTaiKhoan.setMatKhau(rs.getString("matkhau"));

				// thêm vào arraylist
				arr.add(aTaiKhoan);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		return arr;
	}
	
	public TaiKhoanDTO TaiKhoanGet(String userName) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		ArrayList<TaiKhoanDTO> arr = new ArrayList<TaiKhoanDTO>();

		String query = "SELECT * FROM taikhoan WHERE tendangnhap = ?";
		
		conn.prepare(query);
		conn.bind(1, userName);
		
		try {
			ResultSet rs = conn.executeQueryPre();

			while (rs.next()) {
				// khởi tạo
				TaiKhoanDTO aTaiKhoan = new TaiKhoanDTO();
				// gán giá trị
				aTaiKhoan.setTenDangNhap(rs.getString("tendangnhap"));
				aTaiKhoan.setMatKhau(rs.getString("matkhau"));

				// thêm vào arraylist
				arr.add(aTaiKhoan);
			}
		} catch (SQLException ex) {
			conn.displayError(ex);
		}
		conn.Close();
		
		if (arr.size() == 0) {
			return null;
		}
		
		return arr.get(0);
	}

	// thêm tài khoản
	public void TaiKhoanAdd(TaiKhoanDTO aTK) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "INSERT INTO taikhoan( matkhau)" + "VALUE(?)";
		// prepare statement
		conn.prepare(sql);

		// bind value
		// conn.bind(1, aTK.getTenDangNhap());
		conn.bind(1, aTK.getMatKhau());
		conn.executeUpdatePre();

		conn.Close();
	}

	// sửa tài khoản
	public void TaiKhoanEdit(TaiKhoanDTO aTK) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "UPDATE taikhoan SET matkhau = ? WHERE tendangnhap = ?";
		
		// prepare statement
		conn.prepare(sql);

		// bind value
		conn.bind(1, aTK.getMatKhau());
		conn.bind(2, aTK.getTenDangNhap());
		
		conn.executeUpdatePre();

		conn.Close();
	}

	// xóa tài khoản
	public void TaiKhoanDelete(String Tendangnhap) {
		MySqlDataAccessHelper conn = new MySqlDataAccessHelper();

		String sql = "DELETE FROM taikhoan WHERE tendangnhap = ?";

		// prepare statement
		conn.prepare(sql);

		// bind values
		conn.bind(1, Tendangnhap);

		conn.executeUpdatePre();

		conn.Close();
	}
}
